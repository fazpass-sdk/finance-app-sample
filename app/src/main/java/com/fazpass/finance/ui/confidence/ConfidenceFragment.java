package com.fazpass.finance.ui.confidence;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.fazpass.finance.R;
import com.fazpass.finance.helper.Storage;

public class ConfidenceFragment extends Fragment {

    private ConfidenceViewModel mViewModel;
    private TextView confidenceTxt;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ConfidenceViewModel.class);
        mViewModel.setUser(Storage.getUser(requireContext()));
        mViewModel.getConfidenceRate().observe(this, s -> confidenceTxt.setText(s));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_confidence, container, false);

        View backBtn = v.findViewById(R.id.back_btn2);
        backBtn.setOnClickListener(mViewModel::onNavigateUp);

        confidenceTxt = v.findViewById(R.id.confidence_text);
        confidenceTxt.setText("Loading...");

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        mViewModel.requestConfidence(this);
    }
}