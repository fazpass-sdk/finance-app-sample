package com.fazpass.finance.ui.main;

import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.fazpass.finance.LoginActivity;
import com.fazpass.finance.R;
import com.fazpass.finance.component.DialogInputNumber;
import com.fazpass.finance.data.SampleData;
import com.fazpass.finance.helper.Storage;
import com.fazpass.trusted_device.Fazpass;
import com.fazpass.trusted_device.TrustedDeviceListener;
import com.fazpass.trusted_device.User;

public class MainViewModel extends ViewModel {
    private MainFragment fragment;
    private User user;
    private SampleData sampleData;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SampleData getSampleData() {
        return sampleData;
    }

    public void setSampleData(SampleData sampleData) {
        this.sampleData = sampleData;
    }

    public void initialize(MainFragment fragment) {
        this.fragment = fragment;
    }

    public void toConfidence() {
        Navigation.findNavController(fragment.requireView())
                .navigate(R.id.action_mainFragment_to_confidenceFragment);
    }

    public void askLogout() {
        new DialogInputNumber(
                fragment,
                "Input PIN",
                "Input your pin to logout.",
                4,
                (alertDialog, s) -> {
                    alertDialog.dismiss();
                    logout(s);
                    return null;
                },
                alertDialog -> {
                    alertDialog.dismiss();
                    return null;
                }
        ).getInstance().show();
    }

    private void logout(String pin) {
        Fazpass.removeDevice(fragment.requireContext(), pin, new TrustedDeviceListener<Boolean>() {
            @Override
            public void onSuccess(Boolean o) {
                Storage.logout(fragment.requireContext());

                Intent intent = new Intent(fragment.requireActivity(), LoginActivity.class);
                fragment.startActivity(intent);
                fragment.requireActivity().finish();
            }

            @Override
            public void onFailure(Throwable err) {
                Toast.makeText(fragment.requireContext(), err.getMessage(), Toast.LENGTH_SHORT).show();
                askLogout();
            }
        });
    }
}