package com.hnweb.hnwebtest.remote;

import com.hnweb.hnwebtest.model.AllEvent;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIServices {

    @GET("api_get_all_events.php")
    Call<AllEvent> getAllEvents();

}
