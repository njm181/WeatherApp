package com.njm.weatherapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.njm.weatherapp.repository.WeatherRepository;
import com.njm.weatherapp.response.WeatherResponse;

public class WeatherViewModel extends AndroidViewModel {

    private WeatherRepository weatherRepository;
    private MutableLiveData<WeatherResponse> currentWeather;

    public WeatherViewModel(@NonNull Application application) {
        super(application);
        weatherRepository = new WeatherRepository();
        currentWeather = weatherRepository.getCurrentWeather();
    }

    public LiveData<WeatherResponse> getCurrentWeather(){
        return currentWeather;
    }
}
