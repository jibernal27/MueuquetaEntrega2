package com.muequeta.entrega2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by jairo on 30/10/2016.
 */

public class ActivityLugar extends Activity
{

    private TextView texTtulo;
    private TextView texImagen;
     private ImageView imageActual;
    private TextView textDescripcion;
    private Button btnHechos;
    private Lugar lugar;
    private  int maXimagenes;
    private  int actual;
    private int maxVideos;
    private List<Imagene> imagenes;
    private VideoView video;
    private List<Video> videos;
    private MediaController mediaController;
    private  int videoActual;
    private FloatingActionButton camera;
    private int idLugar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugar);

        Intent intent = getIntent();
        idLugar = intent.getIntExtra("idLugar",0);

        MuequetaLogica mundo= MuequetaLogica.getInstance();
         lugar=mundo.darLugarPorID(idLugar);


        texTtulo= (TextView) findViewById(R.id.textLugar);
        imageActual= (ImageView) findViewById(R.id.vistaLugar);
        textDescripcion= (TextView) findViewById(R.id.textDescripcionLUGAR);
        btnHechos= (Button) findViewById(R.id.botonHechos);

        texTtulo.setText(lugar.getNombre());
        textDescripcion.setText(lugar.getDescripcion());

        imagenes=lugar.getImagenes();
        actual=0;
        videoActual=0;

        maXimagenes=imagenes.size();
        texImagen= (TextView) findViewById(R.id.textDescripcionLugarImagen);

        video= (VideoView) findViewById(R.id.videoLugar);
        mediaController = new MediaController(this);
        mediaController.show();
        videos=lugar.getVideos();
        maxVideos=videos.size();
        camera= (FloatingActionButton) findViewById(R.id.capturarMultimedia);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                inicarCaptura();

            }
        });

        btnHechos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iniciarHecho();

            }
        });



        video.setOnTouchListener(new OnFlingGestureListener() {

            @Override
            public void onTopToBottom() {
                //Your code here
            }

            @Override
            public void onRightToLeft() {
                if(videoActual==maxVideos-1&&maXimagenes>0)
                {

                    actual=0;
                    video.stopPlayback();
                    actualizarImagen();
                }
                else if(maXimagenes==0)
                {
                    videoActual=0;
                    video.stopPlayback();
                    visualizarVideo();
                }
                else
                {
                    videoActual++;
                    video.stopPlayback();
                    visualizarVideo();
                }

            }

            @Override
            public void onLeftToRight() {
                if(videoActual==0&&maXimagenes>0)
                {
                    actual=maXimagenes-1;
                    video.stopPlayback();
                    actualizarImagen();

                }
                else if(maXimagenes==0)
                {
                    videoActual=maxVideos-1;
                    video.stopPlayback();
                    visualizarVideo();
                }
                else
                {
                    videoActual--;
                    video.stopPlayback();
                    visualizarVideo();
                }

            }

            @Override
            public void onBottomToTop() {
                //Your code here
            }


        });


        imageActual.setOnTouchListener(new OnFlingGestureListener() {

            @Override
            public void onTopToBottom() {
                //Your code here
            }

            @Override
            public void onRightToLeft() {
                if(actual==maXimagenes-1&&maxVideos>0)
                {
                    videoActual=0;
                    visualizarVideo();

                }
                else if(maxVideos==0&&actual==maXimagenes-1)
                {
                    actual=0;
                    actualizarImagen();

                }
                else
                {
                    actual++;
                    actualizarImagen();
                }

            }

            @Override
            public void onLeftToRight() {
                if(actual==0&&maxVideos>0)
                {
                    videoActual=maxVideos-1;
                    visualizarVideo();
                }
                else if(maxVideos==0&&actual==0)
                {
                    actual=maXimagenes-1;
                    actualizarImagen();
                }
                else
                {
                    actual--;
                    actualizarImagen();
                }

            }

            @Override
            public void onBottomToTop() {
                //Your code here
            }
        });


        if(maxVideos!=0)
        {
            visualizarVideo();
        }
        else if(maXimagenes!=0)
        {
            actualizarImagen();
        }
        else
        {

        }





    }

    public void actualizarImagen()
    {
       String ima=imagenes.get(actual).getDireccion();
        texImagen.setText("Descripción: \n"+imagenes.get(actual).getDescripcion());
        new ImageLoadTask(ConsumidorAPI.BASE_URL+"imagen/"+imagenes.get(actual).getDireccion(), imageActual).execute();
    }

    public void inicarCaptura()
    {
        Intent myIntent = new Intent(this, ActivityCapturarMultimedia.class);
        this.startActivity(myIntent);
    }

    public void iniciarHecho()
    {
        Intent myIntent = new Intent(this, ActivityHecho.class);
        myIntent.putExtra("idLugar",idLugar); //Optional parameters
        this.startActivity(myIntent);
    }


    public void visualizarVideo()
    {
        findViewById(R.id.vistaLugar).setVisibility(View.INVISIBLE);
        findViewById(R.id.loadingPanel).setVisibility(View.INVISIBLE);
        findViewById(R.id.videoLugar).setVisibility(View.VISIBLE);

        String ima=videos.get(videoActual).getDireccion();
        texImagen.setText("Descripción: \n"+videos.get(videoActual).getDescripcion());
        mediaController.setAnchorView(video);
        Uri vid = Uri.parse(ConsumidorAPI.BASE_URL+"video/"+videos.get(videoActual).getDireccion());
        video.setMediaController(mediaController);
        video.setVideoURI(vid);
        video.start();

    }





    private class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

        private String url;
        private ImageView imageView;


        public ImageLoadTask(String url, ImageView imageView) {
            findViewById(R.id.videoLugar).setVisibility(View.INVISIBLE);
            findViewById(R.id.vistaLugar).setVisibility(View.INVISIBLE);
            findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
            this.url = url;
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {

            try {
                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            imageView.setImageBitmap(result);
            findViewById(R.id.loadingPanel).setVisibility(View.INVISIBLE);
            findViewById(R.id.vistaLugar).setVisibility(View.VISIBLE);


        }



    }



}
