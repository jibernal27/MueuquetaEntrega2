package com.muequeta.entrega2;

import java.util.Date;

/**
 * Created by jairo on 29/10/2016.
 */

public class Ubicacion
{
    private double latitud;
    private double longitud;
    private double altura;
    private Date fecha;

    public Ubicacion(double latitud, double longitud, double altura, Date fecha) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.altura = altura;
        this.fecha = fecha;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}

