package com.fazpass.finance.component;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.fazpass.finance.R;

import java.util.function.Function;

import kotlin.jvm.functions.Function2;

public class DialogInputNumber {

    private final AlertDialog instance;
    private boolean isEnabled = false;

    public DialogInputNumber(Fragment fragment,
                             String title,
                             String message,
                             int requiredLength,
                             Function2<AlertDialog, String, Void> onOKListener,
                             Function<AlertDialog, Void> onCancelListener) {
        View dialogView = fragment.getLayoutInflater().inflate(R.layout.dialog_input_number, null);

        EditText input = dialogView.findViewById(R.id.dialog_input_number_edt);
        Button okBtn = dialogView.findViewById(R.id.dialog_input_number_ok_btn);
        Button cancelBtn = dialogView.findViewById(R.id.dialog_input_number_cancel_btn);

        okBtn.setEnabled(false);
        input.addTextChangedListener(new CTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= requiredLength) {
                    if (!isEnabled) {
                        isEnabled = true;
                        okBtn.setEnabled(true);
                    }
                } else {
                    if (isEnabled) {
                        isEnabled = false;
                        okBtn.setEnabled(false);
                    }
                }
            }
        });

        AlertDialog dialog = new AlertDialog.Builder(fragment.requireContext())
                .setTitle(title)
                .setMessage(message)
                .setView(dialogView)
                .setCancelable(false)
                .create();

        okBtn.setOnClickListener(view -> onOKListener.invoke(dialog, input.getText().toString()));
        cancelBtn.setOnClickListener(view -> onCancelListener.apply(dialog));

        this.instance = dialog;
    }

    public AlertDialog getInstance() {
        return instance;
    }

    private static abstract class CTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        abstract public void onTextChanged(CharSequence s, int start, int before, int count);

        @Override
        public void afterTextChanged(Editable s) {}
    }
}
