package com.njm.weatherapp.model;

public class ExtendedForecast {
    private double temperatura;
    private String descripcion;
    private String fecha;
    private String hora;
    private String icon;

    public ExtendedForecast(double temperatura, String descripcion, String fecha, String hora, String icon) {
        this.temperatura = temperatura;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = hora;
        this.icon = icon;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
