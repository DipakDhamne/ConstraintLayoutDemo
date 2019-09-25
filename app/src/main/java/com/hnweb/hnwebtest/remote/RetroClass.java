package com.hnweb.hnwebtest.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClass {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://tech599.com/tech599.com/johngov/partyquest/services/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
