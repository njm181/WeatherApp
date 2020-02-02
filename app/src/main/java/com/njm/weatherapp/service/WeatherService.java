package com.njm.weatherapp.service;

import com.njm.weatherapp.Utils.Constants;
import com.njm.weatherapp.response.WeatherExtendedResponse;
import com.njm.weatherapp.response.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherService {

    @GET("weather?")//lat={latitude}&lon={longitude}
    Call<WeatherResponse> loadWeather(@Query("lat")double latitude, @Query("lon")double longitude);

    @GET("forecast?")
    Call<WeatherExtendedResponse> loadExtendedForecast(@Query("lat")double latitude, @Query("lon")double longitude);
}
