package com.fazpass.finance.ui.confidence;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.fazpass.finance.component.DialogInputNumber;
import com.fazpass.trusted_device.Fazpass;
import com.fazpass.trusted_device.FazpassTd;
import com.fazpass.trusted_device.TrustedDeviceListener;
import com.fazpass.trusted_device.User;
import com.fazpass.trusted_device.ValidateStatus;

public class ConfidenceViewModel extends ViewModel {

    private User user;
    private final MutableLiveData<String> confidenceRate = new MutableLiveData<>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void onNavigateUp(View view) {
        Navigation.findNavController(view)
                .navigateUp();
    }

    public MutableLiveData<String> getConfidenceRate() {
        return confidenceRate;
    }

    public void requestConfidence(Fragment fragment) {
        String title = "Input PIN";
        String message = "Please input your pin to calculate confidence rate.";
        AlertDialog inputPinDialog = new DialogInputNumber(
                fragment, title, message, 4,
                (alertDialog, input) -> {
                    alertDialog.dismiss();
                    calculateConfidenceRate(fragment.requireContext(), input);
                    return null;
                },
                alertDialog -> {
                    alertDialog.dismiss();
                    onNavigateUp(fragment.requireView());
                    return null;
                }).getInstance();
        inputPinDialog.show();
    }

    private void calculateConfidenceRate(Context context, String pin) {
        Fazpass.check(context, user.getEmail(), user.getPhone(), new TrustedDeviceListener<FazpassTd>() {
            @Override
            public void onSuccess(FazpassTd o) {
                o.validateUser(context, pin, new TrustedDeviceListener<ValidateStatus>() {
                    @Override
                    public void onSuccess(ValidateStatus o) {
                        String val = String.format("%s%%", o.getConfidenceRate().getSummary()*100);
                        confidenceRate.postValue(val);
                    }

                    @Override
                    public void onFailure(Throwable err) {
                        Toast.makeText(context, err.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(Throwable err) {
                err.printStackTrace();
            }
        });
    }
}