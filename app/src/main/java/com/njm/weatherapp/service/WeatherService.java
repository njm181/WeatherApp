package com.njm.weatherapp.service;

import com.njm.weatherapp.Utils.Constants;
import com.njm.weatherapp.response.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WeatherService {

    @GET("weather"+ Constants.ID_CITY)
    Call<WeatherResponse> loadWeather();
}
