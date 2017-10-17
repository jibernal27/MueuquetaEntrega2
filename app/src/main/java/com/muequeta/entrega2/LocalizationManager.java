package com.muequeta.entrega2;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by jairo on 30/10/2016.
 */

public class LocalizationManager extends Service implements LocationListener
{



    LocationManager locationmanager;
    private double  latitud;
    private double longitud;
    private double altura;
    private Context context;

    public LocationManager getLocationmanager() {
        return locationmanager;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public double getAltura() {
        return altura;
    }




    public LocalizationManager(Context context)
    {
            locationmanager=(LocationManager)context.getSystemService(Context.LOCATION_SERVICE);

            Criteria cri=new Criteria();

            String provider=locationmanager.getBestProvider(cri,false);
            if(provider!=null & !provider.equals(""))

            {

                if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    Location location = locationmanager.getLastKnownLocation(provider);

                    locationmanager.requestLocationUpdates(provider, 1000, 1, this);

                    if (location != null)

                    {

                        onLocationChanged(location);

                    } else {

                        Toast.makeText(context.getApplicationContext(), "location not found", Toast.LENGTH_LONG).show();

                    }

                } else

                {

                    Toast.makeText(context.getApplicationContext(), "Provider is null", Toast.LENGTH_LONG).show();

                }

            }
            else


            {
                Toast.makeText(context.getApplicationContext(), "No se tienen permisos", Toast.LENGTH_LONG).show();
            }

        }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }





    @Override
    public void onLocationChanged(Location location) {

        latitud=location.getLatitude();
        longitud=location.getLongitude();
        altura=location.getAltitude();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
