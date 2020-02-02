package com.njm.weatherapp;


import android.location.Location;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.njm.weatherapp.response.WeatherResponse;
import com.njm.weatherapp.viewmodel.WeatherViewModel;

import java.text.DecimalFormat;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainFragment extends Fragment {

    private WeatherViewModel weatherViewModel;
    private TextView tvCityName, tvDescription, tvTemperatura, tvHumedad, tvTitleTemp, tvTitleHum;
    private LottieAnimationView av01D,av01N, av02D, av02N ,av03D, av03N,
            av04D, av04N, av09D, av09N, av10D, av10N, av11D, av11N;
    private String option ="";
    private View view;
    private double tempCelsius=0;
    private FusedLocationProviderClient fusedLocationClient;
    private FusedLocationProviderClient client;
    private double longitude;
    private double latitude;



    public MainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestPermission();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_main, container, false);

        loadViews();
        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        getCoordinates();


        return view;
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(getActivity(), new String[]{ACCESS_FINE_LOCATION},1);
    }
    private void getCoordinates(){
        client = LocationServices.getFusedLocationProviderClient(getActivity());
        client.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if(location!=null){
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    loadCurrentWeather(latitude, longitude);
                }

            }
        });
    }


    public void loadCurrentWeather(double lat, double longi) {
        Toast.makeText(getActivity(), "CORDENADAS ----->"+lat+" ---"+longi, Toast.LENGTH_LONG).show();

        hideViews();

        weatherViewModel.getCurrentWeather(lat, longi).observe(this, new Observer<WeatherResponse>() {
            @Override
            public void onChanged(WeatherResponse weatherResponse) {
                tempCelsius = weatherResponse.getMain().getTemp() - 273.15;
                DecimalFormat format = new DecimalFormat("#.0");
                tvCityName.setText(weatherResponse.getName());
                tvTemperatura.setText(format.format(tempCelsius)+"°");
                tvHumedad.setText(weatherResponse.getMain().getHumidity()+"%");
                tvDescription.setText((weatherResponse.getWeather().get(0).getDescription()));

                option = weatherResponse.getWeather().get(0).getIcon();
                switch (option){
                    case "01d":
                        av01D.setVisibility(View.VISIBLE);
                        break;
                    case "01n":
                        av01N.setVisibility(View.VISIBLE);
                        break;
                    case "02d":
                        av02D.setVisibility(View.VISIBLE);
                        break;
                    case "02n":
                        av02N.setVisibility(View.VISIBLE);
                        break;
                    case "03d":
                        av03D.setVisibility(View.VISIBLE);
                        break;
                    case "03n":
                        av03N.setVisibility(View.VISIBLE);
                        break;
                    case "04d":
                        av04D.setVisibility(View.VISIBLE);
                        break;
                    case "04n":
                        av04N.setVisibility(View.VISIBLE);
                        break;
                    case "09d":
                        av09D.setVisibility(View.VISIBLE);
                        break;
                    case "09n":
                        av09N.setVisibility(View.VISIBLE);
                        break;
                    case "10d":
                        av10D.setVisibility(View.VISIBLE);
                        break;
                    case "10n":
                        av10N.setVisibility(View.VISIBLE);
                        break;
                    case "11d":
                        av11D.setVisibility(View.VISIBLE);
                        break;
                    case "11n":
                        av11N.setVisibility(View.VISIBLE);
                        break;

                }


            }
        });
    }

    public void loadViews(){
        tvCityName = view.findViewById(R.id.textViewCityName);
        tvTemperatura = view.findViewById(R.id.textViewTemperatura);
        tvHumedad = view.findViewById(R.id.textViewHumedad);
        tvDescription = view.findViewById(R.id.textViewDescription);
        av01D = view.findViewById(R.id.animation_view_01d);
        av01N = view.findViewById(R.id.animation_view_01n);
        av02D = view.findViewById(R.id.animation_view_02d);
        av02N = view.findViewById(R.id.animation_view_02n);
        av03D = view.findViewById(R.id.animation_view_03d);
        av03N = view.findViewById(R.id.animation_view_03n);
        av04D = view.findViewById(R.id.animation_view_04d);
        av04N = view.findViewById(R.id.animation_view_04n);
        av09D = view.findViewById(R.id.animation_view_09d);
        av09N = view.findViewById(R.id.animation_view_09n);
        av10D = view.findViewById(R.id.animation_view_10d);
        av10N = view.findViewById(R.id.animation_view_10n);
        av11D = view.findViewById(R.id.animation_view_11d);
        av11N = view.findViewById(R.id.animation_view_11n);
        tvTitleTemp = view.findViewById(R.id.textViewTitleTemp);
        tvTitleHum = view.findViewById(R.id.textViewTitleHum);
    }

    public void hideViews(){
        av01D.setVisibility(View.GONE);
        av01N.setVisibility(View.GONE);
        av02D.setVisibility(View.GONE);
        av02N.setVisibility(View.GONE);
        av03D.setVisibility(View.GONE);
        av03N.setVisibility(View.GONE);
        av04D.setVisibility(View.GONE);
        av04N.setVisibility(View.GONE);
        av09D.setVisibility(View.GONE);
        av09N.setVisibility(View.GONE);
        av10D.setVisibility(View.GONE);
        av10N.setVisibility(View.GONE);
        av11D.setVisibility(View.GONE);
        av11N.setVisibility(View.GONE);
    }


}
