package com.hnweb.hnwebtest.remote;

import androidx.lifecycle.MutableLiveData;

import com.hnweb.hnwebtest.model.AllEvent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GlobalRepository {

    public static GlobalRepository globalRepository;

    public static GlobalRepository getInstance() {
        if (globalRepository == null) {
            globalRepository = new GlobalRepository();
        }
        return globalRepository;
    }

    private APIServices allApi;

    public GlobalRepository() {
        allApi = RetroClass.createService(APIServices.class);
    }

    public MutableLiveData<AllEvent> requestAllEvent() {
        final MutableLiveData<AllEvent> responseMutableLiveData = new MutableLiveData<>();
        allApi.getAllEvents().enqueue(new Callback<AllEvent>() {
            @Override
            public void onResponse(Call<AllEvent> call, Response<AllEvent> response) {
                AllEvent res = response.body();
                if (res != null) {
                    responseMutableLiveData.setValue(res);
                } else {
                    responseMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<AllEvent> call, Throwable t) {
                responseMutableLiveData.setValue(null);
            }
        });

        return responseMutableLiveData;
    }
}
