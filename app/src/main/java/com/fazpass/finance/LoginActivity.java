package com.fazpass.finance;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.fazpass.trusted_device.Fazpass;
import com.fazpass.trusted_device.MODE;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Fazpass.initialize(this, getResources().getString(R.string.merchant_key), MODE.STAGING);
        Fazpass.launchedFromNotificationRequirePin(this, getIntent());
        Fazpass.requestPermission(this);
    }
}