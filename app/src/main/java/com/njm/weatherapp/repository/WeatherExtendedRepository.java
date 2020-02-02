package com.njm.weatherapp.repository;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.njm.weatherapp.Utils.Constants;
import com.njm.weatherapp.Utils.MyApp;
import com.njm.weatherapp.model.RequestInterceptor;
import com.njm.weatherapp.response.WeatherExtendedResponse;
import com.njm.weatherapp.service.WeatherService;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherExtendedRepository {

    private WeatherService weatherService;
    private MutableLiveData<WeatherExtendedResponse> weatherExtendedResponse;

    public WeatherExtendedRepository() {

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.addInterceptor(new RequestInterceptor());
        OkHttpClient client = okHttpClientBuilder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherService = retrofit.create(WeatherService.class);
    }

    public MutableLiveData<WeatherExtendedResponse> getExtendedForecast(double latitude, double longitude){
        if(weatherExtendedResponse == null){
            weatherExtendedResponse = new MutableLiveData<>();
        }

        Call<WeatherExtendedResponse> call = weatherService.loadExtendedForecast(latitude, longitude);
        call.enqueue(new Callback<WeatherExtendedResponse>() {
            @Override
            public void onResponse(Call<WeatherExtendedResponse> call, Response<WeatherExtendedResponse> response) {
                if(response.isSuccessful()){
                    weatherExtendedResponse.setValue(response.body());
                }else{
                    Toast.makeText(MyApp.getContext(), "Algo salio mal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherExtendedResponse> call, Throwable t) {
                Toast.makeText(MyApp.getContext(), "Error en la conexion", Toast.LENGTH_SHORT).show();
            }
        });
        return weatherExtendedResponse;
    }
}
