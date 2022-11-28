package com.fazpass.finance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.fazpass.finance.helper.Storage;
import com.fazpass.finance.object.User;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // check logged in user
        User user = Storage.getUser(this);
        if (user == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        // setup navigation view
        NavigationView navView = findViewById(R.id.nav_view_main);
        View header = navView.getHeaderView(0);
        ((TextView) header.findViewById(R.id.drawer_email)).setText(user.getEmail());
        ((TextView) header.findViewById(R.id.drawer_phone)).setText(user.getPhone());

        // attach navigation view to nav fragment controller
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_main);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(navView, navController);
    }
}