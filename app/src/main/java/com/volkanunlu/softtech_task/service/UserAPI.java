package com.volkanunlu.softtech_task.service;

import com.volkanunlu.softtech_task.model.UserResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserAPI {

    //Verilerimi alacağım alan
    //https://raw.githubusercontent.com/
    //volkanunlu/sampleapi/main/retrofitvalues.json

    @GET("volkanunlu/userActivity/main/useractivityvalues.json")
    Call<UserResponseModel> getData();   //Gözlemlenebilir bir yapı inşası.

    //https://raw.githubusercontent.com/
    // volkanunlu/userActivity/main/useractivityvalues.json
}
