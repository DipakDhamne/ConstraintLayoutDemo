package com.hnweb.hnwebtest.viewmodel;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.hnweb.hnwebtest.model.AllEvent;
import com.hnweb.hnwebtest.model.Response;
import com.hnweb.hnwebtest.remote.GlobalRepository;

import java.util.List;

public class ListingActivityViewModel extends AndroidViewModel {

    private GlobalRepository globalRepository;
    private MutableLiveData<AllEvent> requestEventData;
    private Context context;
    public MutableLiveData<List<Response>> lstEvent;

    public ListingActivityViewModel(@NonNull Application application) {
        super(application);
        context = application;
    }

    public void init() {
        globalRepository = GlobalRepository.getInstance();
        requestEventData = new MutableLiveData<>();
        lstEvent = new MutableLiveData<>();
    }

    public void getEventData() {
        requestEventData = globalRepository.requestAllEvent();

        requestEventData.observeForever(new Observer<AllEvent>() {
            @Override
            public void onChanged(AllEvent allEvent) {
                if (allEvent != null) {
                    //Toast.makeText(context, allEvent.getMessage(), Toast.LENGTH_SHORT).show();
                    if(allEvent.getMessageCode()==1){
                        lstEvent.setValue(allEvent.getResponse());
                    }
                } else {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                    lstEvent.setValue(null);
                }
            }
        });
    }

}
