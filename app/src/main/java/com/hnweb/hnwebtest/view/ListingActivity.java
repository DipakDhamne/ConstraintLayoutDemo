package com.hnweb.hnwebtest.view;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hnweb.hnwebtest.R;
import com.hnweb.hnwebtest.adapter.ListEventAdapter;
import com.hnweb.hnwebtest.databinding.ActivityListingBinding;
import com.hnweb.hnwebtest.model.Response;
import com.hnweb.hnwebtest.viewmodel.ListingActivityViewModel;

import java.util.List;

public class ListingActivity extends AppCompatActivity {

    private  ActivityListingBinding activityListingBinding;
    private ListingActivityViewModel viewModel;
    private RecyclerView rvList;
    //public boolean progressVisible;//,rvVisible;
    public String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(getResources().getColor(R.color.colorOrange));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        activityListingBinding = DataBindingUtil.setContentView(this,R.layout.activity_listing);
        activityListingBinding.setAdapterData(this);
        viewModel = ViewModelProviders.of(this).get(ListingActivityViewModel.class);
        viewModel.init();
        activityListingBinding.setProgressVisible(true);
        viewModel.getEventData();
        rvList = findViewById(R.id.rvList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListingActivity.this, RecyclerView.VERTICAL, false);
        rvList.setLayoutManager(linearLayoutManager);

        title = getIntent().getExtras().getString("title");
        activityListingBinding.setTitle(title);

        viewModel.lstEvent.observe(this, new Observer<List<Response>>() {
            @Override
            public void onChanged(List<Response> responses) {
                activityListingBinding.setProgressVisible(false);
                if(responses!=null){
                    ListEventAdapter myEventAdapter = new ListEventAdapter(ListingActivity.this, responses);
                    rvList.setAdapter(myEventAdapter);
                }
            }
        });
    }

    public void onFinishClick(){
        finish();
    }
}
