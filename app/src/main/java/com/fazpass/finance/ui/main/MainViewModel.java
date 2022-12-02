package com.fazpass.finance.ui.main;

import android.content.Intent;

import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.fazpass.finance.LoginActivity;
import com.fazpass.finance.R;
import com.fazpass.finance.data.SampleData;
import com.fazpass.finance.helper.Storage;
import com.fazpass.finance.object.User;

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

    public void logout() {
        Storage.logout(fragment.requireContext());

        Intent intent = new Intent(fragment.requireActivity(), LoginActivity.class);
        fragment.startActivity(intent);
        fragment.requireActivity().finish();
    }
}