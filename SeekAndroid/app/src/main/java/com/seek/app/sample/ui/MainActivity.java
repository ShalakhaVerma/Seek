package com.seek.app.sample.ui;

import android.os.Bundle;

import com.seek.app.sample.R;
import com.seek.app.sample.di.components.MainActivityComponent;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    MainActivityComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getComponent().getNavigationController().navigateToHomeScreen();
    }

    public MainActivityComponent getComponent() {
        if (component == null) {
            component = MainActivityComponent.Initializer.init(this);
        }
        return component;
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }
}
