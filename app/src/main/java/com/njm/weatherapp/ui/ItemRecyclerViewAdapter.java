package com.njm.weatherapp.ui;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.njm.weatherapp.R;
import com.njm.weatherapp.model.ExtendedForecast;

import java.text.DecimalFormat;
import java.util.List;


public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder> {

    private double tempCelsius = 0;
    private List<ExtendedForecast> extendedForecastList;
    private Context ctx;
    private String option="";

    public ItemRecyclerViewAdapter(List<ExtendedForecast> items, Context context) {
        extendedForecastList = items;
        ctx = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.extendedForecast = extendedForecastList.get(position);

        tempCelsius = holder.extendedForecast.getTemperatura() - 273.15;
        DecimalFormat format = new DecimalFormat("#.0");
        String horaCompleta = holder.extendedForecast.getHora();
        String hora = horaCompleta.substring(0,5)+"Hs";

        option = holder.extendedForecast.getIcon();

        switch (option){
            case "01d":
                holder.av01D.setVisibility(View.VISIBLE);
                break;
            case "01n":
                holder.av01N.setVisibility(View.VISIBLE);
                break;
            case "02d":
                holder.av02D.setVisibility(View.VISIBLE);
                break;
            case "02n":
                holder.av02N.setVisibility(View.VISIBLE);
                break;
            case "03d":
                holder.av03D.setVisibility(View.VISIBLE);
                break;
            case "03n":
                holder.av03N.setVisibility(View.VISIBLE);
                break;
            case "04d":
                holder.av04D.setVisibility(View.VISIBLE);
                break;
            case "04n":
                holder.av04N.setVisibility(View.VISIBLE);
                break;
            case "09d":
                holder.av09D.setVisibility(View.VISIBLE);
                break;
            case "09n":
                holder.av09N.setVisibility(View.VISIBLE);
                break;
            case "10d":
                holder.av10D.setVisibility(View.VISIBLE);
                break;
            case "10n":
                holder.av10N.setVisibility(View.VISIBLE);
                break;
            case "11d":
                holder.av11D.setVisibility(View.VISIBLE);
                break;
            case "11n":
                holder.av11N.setVisibility(View.VISIBLE);
                break;

        }

        holder.txtTemperatura.setText(format.format(tempCelsius)+"°");
       //holder.txtDescripcion.setText(holder.extendedForecast.getDescripcion());
        holder.txtFecha.setText(holder.extendedForecast.getFecha());
        holder.txtHora.setText(hora);

    }


    @Override
    public int getItemCount() {
        if(extendedForecastList != null){
            return extendedForecastList.size();
        }else{
            return 0;
        }

    }

    public void setData(List<ExtendedForecast> extendedForecastList) {
        this.extendedForecastList = extendedForecastList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView txtFecha;
        //public final TextView txtDescripcion;
        public final TextView txtTemperatura;
        public final TextView txtHora;
        public ExtendedForecast extendedForecast;
        public final LottieAnimationView av01D,av01N, av02D, av02N ,av03D, av03N,
                av04D, av04N, av09D, av09N, av10D, av10N, av11D, av11N;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            txtFecha = view.findViewById(R.id.textViewFecha);
            //txtDescripcion = view.findViewById(R.id.textViewDescripcion);
            txtTemperatura = view.findViewById(R.id.textViewTemp);
            txtHora = view.findViewById(R.id.textViewHora);
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
            hideViews();
        }
        public void hideViews() {
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
        @Override
        public String toString() {
            return super.toString() + " '" + txtFecha.getText() + "'";
        }
    }
}
