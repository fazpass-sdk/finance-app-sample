package com.fazpass.finance.ui.splash;

import android.content.Intent;
import android.os.Handler;

import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.fazpass.finance.MainActivity;
import com.fazpass.finance.R;
import com.fazpass.finance.helper.Storage;
import com.fazpass.finance.object.User;

public class SplashViewModel extends ViewModel {

    public void startCountdown(SplashFragment fragment) {
        new Handler().postDelayed(afterCountdown(fragment), 3000L);
    }

    private Runnable afterCountdown(SplashFragment fragment) {
        return () -> {
            final User user = Storage.getUser(fragment.getContext());

            if (user != null) {
                Intent intent = new Intent(fragment.requireActivity(), MainActivity.class);
                fragment.startActivity(intent);
                fragment.requireActivity().finish();
            }
            else {
                Navigation.findNavController(fragment.requireView())
                        .navigate(R.id.action_splashFragment_to_loginFragment);
            }
        };
    }
}