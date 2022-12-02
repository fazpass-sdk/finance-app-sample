package com.fazpass.finance.ui.confidence;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.fazpass.finance.helper.Storage;
import com.fazpass.finance.object.User;
import com.fazpass.trusted_device.Fazpass;
import com.fazpass.trusted_device.FazpassTd;
import com.fazpass.trusted_device.TrustedDeviceListener;
import com.fazpass.trusted_device.ValidateStatus;

import java.util.function.Function;

public class ConfidenceViewModel extends ViewModel {

    private ConfidenceFragment fragment;
    private User user;

    public void initialize(ConfidenceFragment confidenceFragment) {
        fragment = confidenceFragment;
    }

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

    public void awaitConfidenceRate(Function<Double, Void> callback) {
        Fazpass.check(fragment.requireContext(), user.getEmail(), user.getPhone(), user.getPin(), new TrustedDeviceListener<FazpassTd>() {
            @Override
            public void onSuccess(FazpassTd o) {
                o.validateUser(fragment.requireContext(), user.getPin(), new TrustedDeviceListener<ValidateStatus>() {
                    @Override
                    public void onSuccess(ValidateStatus o) {
                        callback.apply(o.getConfidenceRate().getConfidence());
                    }

                    @Override
                    public void onFailure(Throwable err) {
                        err.printStackTrace();
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