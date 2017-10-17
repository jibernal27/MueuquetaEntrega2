package com.muequeta.entrega2;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;

public class MapsFragment extends Fragment  implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener {

    MapView mMapView;
    private GoogleMap googleMap;
    private HashMap<Marker, Integer> mHashMap ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_maps, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }


        mMapView.getMapAsync(this);
        mHashMap= new HashMap<Marker, Integer>();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }


    @Override
    public boolean onMarkerClick(Marker marker)
    {
        if(mHashMap.get(marker)!=-1)

        {
            Intent myIntent = new Intent(getActivity(), ActivityLugar.class);
            myIntent.putExtra("idLugar", mHashMap.get(marker)); //Optional parameters
            getActivity().startActivity(myIntent);

            Log.d("SI ENTRA",mHashMap.get(marker)+"");
        }


        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
         this.googleMap=googleMap;
        LocalizationManager ubi=new LocalizationManager(getActivity().getBaseContext());
        double lati=ubi.getLatitud();
                double longi=ubi.getLongitud();
        LatLng actual = new LatLng(lati,longi );

        Marker ubic=googleMap.addMarker(new MarkerOptions().position(actual).title("Ubicación").snippet("Ubicación actual"));

        // For zooming automatically to the location of the marker
        CameraPosition cameraPosition = new CameraPosition.Builder().target(actual).zoom(16).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        MuequetaLogica mundo=MuequetaLogica.getInstance();
        ArrayList<Lugar> cercanos=mundo.darLugaresCerca(lati,longi);

        mHashMap.put(ubic,-1);

        for (Lugar cercano:cercanos
             ) {
            String[] coor=cercano.getCoordenadas().split(",");
            LatLng lug = new LatLng(Double.parseDouble(coor[0]),Double.parseDouble(coor[1]) );
            Marker ac=googleMap.addMarker(new MarkerOptions().position(lug).title("Lugar").snippet(cercano.getNombre()));
            mHashMap.put(ac,cercano.getId());
        }
        googleMap.setOnMarkerClickListener(this);

    }



}
