package com.hnweb.hnwebtest.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.hnweb.hnwebtest.R;
import com.hnweb.hnwebtest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(getResources().getColor(R.color.colorOrange));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        activityMainBinding.setActivityView(this);
    }

    public void ivUpClick(){
        nextActivity("Charities");
    }

    public void ivDownClick(){
        nextActivity("Ideas");
    }
    public void ivLeftClick(){
        nextActivity("Organization");
    }

    public void ivRightClick(){
        nextActivity("Candidatess");
    }

    private void nextActivity(String title){
        Intent intent = new Intent(MainActivity.this, ListingActivity.class);
        intent.putExtra("title",title);
        startActivity(intent);
    }
}
