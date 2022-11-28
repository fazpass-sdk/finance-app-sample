package com.fazpass.finance.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fazpass.finance.R;
import com.fazpass.finance.data.SampleData;
import com.fazpass.finance.helper.Storage;
import com.google.android.material.navigation.NavigationView;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mViewModel.setUser(Storage.getUser(requireContext()));
        mViewModel.setSampleData(new SampleData());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        RecyclerView feeds = v.findViewById(R.id.feed_rv);
        feeds.setLayoutManager(new LinearLayoutManager(requireContext()));
        MainRVOverviewAdapter mainRVOverviewAdapter = new MainRVOverviewAdapter(mViewModel.getSampleData().getOverview1());
        feeds.setAdapter(mainRVOverviewAdapter);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        NavigationView n = view.getRootView().findViewById(R.id.nav_view_main);
        n.setNavigationItemSelectedListener(this::onMenuItemClick);

        NavController navController = Navigation.findNavController(view);
        DrawerLayout drawerLayout = view.getRootView().findViewById(R.id.container_main);
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph())
                        .setOpenableLayout(drawerLayout)
                        .build();
        Toolbar toolbar = view.findViewById(R.id.toolbar);

        NavigationUI.setupWithNavController(
                toolbar, navController, appBarConfiguration);
    }

    private boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.menu_logout) {
            mViewModel.logout();
            return true;
        }
        return false;
    }

    @Override
    public void onStart() {
        super.onStart();

        mViewModel.initialize(this);
    }
}