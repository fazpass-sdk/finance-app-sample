package com.fazpass.finance.ui.login;

import android.os.Bundle;

import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.fazpass.finance.R;
import com.fazpass.finance.object.User;

import java.util.ArrayList;

public class LoginViewModel extends ViewModel {

    private LoginFragment fragment;

    public void init(LoginFragment fragment) {
        this.fragment = fragment;
    }

    public void login(String email, String phone, String pin) {
        if (email.isEmpty() || phone.isEmpty() || pin.isEmpty()) {
            failedLogin("Please fill in all of this form.");
            return;
        }

        User u = new User(email, phone, email.split("@")[0],"","", pin);
        User.setUseFinger(false);
        successLogin(u);
    }

    private void successLogin(User u) {
        ArrayList<String> list = new ArrayList<>();
        list.add(u.getEmail());
        list.add(u.getPhone());
        list.add(u.getName());
        list.add(u.getIdCard());
        list.add(u.getAddress());
        list.add(u.getPin());

        Bundle args = new Bundle();
        args.putStringArrayList("ARGS_USER", list);
        args.putBoolean("ARGS_CD_IS_AVAILABLE", true);
        Navigation.findNavController(fragment.requireView())
                .navigate(R.id.action_loginFragment_to_confirmLoginFragment, args);
    }

    private void failedLogin(String errorMessage) {
        fragment.showErrorMessage(errorMessage);
    }
}