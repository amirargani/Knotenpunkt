package com.knotenpunkt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideNavigation();
        checkConnection();
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.white));
        tabLayout = findViewById(R.id.tabLayout_main);
        viewPager = findViewById(R.id.viewPager_main);
        tabLayout.setupWithViewPager(viewPager);
        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new NewspaperFragment(), "Mitteilung");
        vpAdapter.addFragment(new CalendarFragment(), "Kalender");
        vpAdapter.addFragment(new ExcelFragment(), "Godiplan");
        vpAdapter.addFragment(new ExcelTableFragment(), "GP-Details");
        viewPager.setAdapter(vpAdapter);
        // Icon
        // youtube.com/watch?v=ziJ6-AT3ymg
        tabLayout.getTabAt(0).setIcon(R.drawable.bubble2x);
        tabLayout.getTabAt(1).setIcon(R.drawable.calendar2x);
        tabLayout.getTabAt(2).setIcon(R.drawable.tablecells2x);
        tabLayout.getTabAt(3).setIcon(R.drawable.lock2x);
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideNavigation();
        checkConnection();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // call method
        checkConnection();
    }

    private void hideNavigation() {
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }

    // Check if network is connected
    private boolean checkInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    private void checkConnection() {
        if (!checkInternet()) {
            Intent intent = new Intent(MainActivity.this,NoInternetActivity.class);
            startActivity(intent);
            finish();
            // or
            //Intent intent = new Intent(MainActivity.this,NoInternetActivity.class);
            //MainActivity.this.startActivity(intent);
            //MainActivity.this.finish();
        }
    }
}
