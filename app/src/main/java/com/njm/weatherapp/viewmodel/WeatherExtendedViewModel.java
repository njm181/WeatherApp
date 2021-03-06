package com.njm.weatherapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.njm.weatherapp.repository.WeatherExtendedRepository;
import com.njm.weatherapp.response.WeatherExtendedResponse;

public class WeatherExtendedViewModel extends AndroidViewModel {

    private WeatherExtendedRepository weatherExtendedRepository;
    private MutableLiveData<WeatherExtendedResponse> extendedForecastList;


    public WeatherExtendedViewModel(@NonNull Application application) {
        super(application);
        weatherExtendedRepository = new WeatherExtendedRepository();
    }

    public LiveData<WeatherExtendedResponse> getExtendedForecastList(double latitute, double longitude){
        extendedForecastList = weatherExtendedRepository.getExtendedForecast(latitute, longitude);
        return extendedForecastList;
    }
}
