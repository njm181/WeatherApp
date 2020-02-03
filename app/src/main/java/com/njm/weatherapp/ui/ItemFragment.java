package com.njm.weatherapp.ui;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.njm.weatherapp.R;
import com.njm.weatherapp.model.ExtendedForecast;
import com.njm.weatherapp.model.Weather;
import com.njm.weatherapp.response.WeatherExtendedResponse;
import com.njm.weatherapp.viewmodel.WeatherExtendedViewModel;

import java.util.ArrayList;
import java.util.List;


public class ItemFragment extends Fragment {

    private List<ExtendedForecast> extendedForecastList;
    private ItemRecyclerViewAdapter adapter;
    private WeatherExtendedViewModel weatherExtendedViewModel;
    private ExtendedForecast extendedForecast;
    private List<Weather> weatherList;
    private String icon ="";
    private FusedLocationProviderClient client;
    private double longitude;
    private double latitude;



    public ItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //instanciar el viewModel para realizar la carga de la lista
        weatherExtendedViewModel = ViewModelProviders.of(getActivity()).get(WeatherExtendedViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));


            adapter = new ItemRecyclerViewAdapter(extendedForecastList, getActivity());
            recyclerView.setAdapter(adapter);
            getCoordinates();

        }
        return view;
    }

    private void getCoordinates(){
        client = LocationServices.getFusedLocationProviderClient(getActivity());
        client.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if(location!=null){
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    loadExtendedForecast(latitude, longitude);
                }

            }
        });
    }

    private void loadExtendedForecast(double lat, double longi){
        extendedForecastList = new ArrayList<>();
        weatherExtendedViewModel.getExtendedForecastList(lat, longi).observe(getActivity(), new Observer<WeatherExtendedResponse>() {
            String fecha = "";
            String hora = "";
            String descripcion="";
            @Override
            public void onChanged(WeatherExtendedResponse weatherExtendedResponse) {
                for (com.njm.weatherapp.model.List item: weatherExtendedResponse.getList()) {//recorro dentro de List cada elemento que tiene (5 de dias previstos)
                    weatherList = item.getWeather();//getDescripcion
                    String[] parts = item.getDtTxt().split(" ");
                    fecha = parts[0];
                    hora = parts[1];

                    for (Weather itemW: weatherList) {
                        descripcion = itemW.getDescription();
                        //El icono necesito agregarlo a mi Objeto Modelo para luego implementar la animacion
                        icon = itemW.getIcon();
                    }
                    extendedForecast = new ExtendedForecast(item.getMain().getTemp(), descripcion, fecha, hora, icon);
                    extendedForecastList.add(extendedForecast);
                }
                adapter.setData(extendedForecastList);
            }
        });
    }


}
