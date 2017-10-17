package com.muequeta.entrega2;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.Calendar;

/**
 * Created by jairo on 29/10/2016.
 */


public class GPSService extends Service
{

    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;
    LocalizationManager localizacion;

    @Override
    public void onCreate() {


        // To avoid cpu-blocking, we create a background handler to run our service
        HandlerThread thread = new HandlerThread("ServiceStartArguments",
                Process.THREAD_PRIORITY_BACKGROUND);
        // start the new handler thread
        thread.start();

        mServiceLooper = thread.getLooper();
        // start the service using the background handler
        mServiceHandler = new ServiceHandler(mServiceLooper);

        localizacion=  new LocalizationManager(getApplicationContext());

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Inicando captura de datos", Toast.LENGTH_SHORT).show();

        // For each start request, send a message to start a job and deliver the
        // start ID so we know which request we're stopping when we finish the job


        Message msg = mServiceHandler.obtainMessage();
        msg.arg1 = startId;
        mServiceHandler.sendMessage(msg);

        // If we get killed, after returning from here, restart
        Log.d("Proveedor","Se envia mensaje");
        return START_STICKY;
    }


    public void onDestroy()
    {
        MuequetaLogica mundo= MuequetaLogica.getInstance();
        mundo.persistirJSONUbicaciones();

    }



    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }






    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }
        @Override
        public void handleMessage(Message msg) {
            // Normally we would do some work here, like download a file.
            // For our sample, we just sleep for 5 seconds.
        MuequetaLogica mundo= MuequetaLogica.getInstance();
        int count=0;
            while(mundo.consumirDatos()) {
                try {


                    Calendar c = Calendar.getInstance();
                    count++;
                    mundo.agregarUbicacion(localizacion.getLatitud(),localizacion.getLongitud(),localizacion.getAltura(),c.getTime());

                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    // Restore interrupt status.
                    Thread.currentThread().interrupt();
                    stopSelf(msg.arg1);
                }
                // Stop the service using the startId, so that we don't stop
                // the service in the middle of handling another job
            }

        }
    }
}
