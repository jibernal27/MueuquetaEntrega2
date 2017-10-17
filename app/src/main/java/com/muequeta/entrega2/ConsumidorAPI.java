package com.muequeta.entrega2;

import android.util.Log;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jairo on 30/10/2016.
 */

public class ConsumidorAPI
{

    public static final String BASE_URL = "http://157.253.220.20:5000/";
    private Retrofit retrofit;
    private EndPoints apiService;


    public ConsumidorAPI()

    {
        retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
         apiService =
                retrofit.create(EndPoints.class);
    }


    public Lugar getLugar(String idLugar) {

        Call<Lugar> call = apiService.getLugar(idLugar);
        Lugar lug =null;
        try {
            lug=call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

            return lug;

    }

    public HechoLugar getHechoLugar(int idLugar )
    {
        Call<HechoLugar> call = apiService.getHechoLugar(idLugar+"");
        HechoLugar lug =null;
        try {
            lug=call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lug;
    }

    public Hecho getHecho(String hechos)
    {
        Call<Hecho> hecho = apiService.getHecho(hechos);
        Hecho user =null;
        try {
            user=hecho.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return user;


    }

    public void uploadFile(File file) {

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part is used to send also the actual file name

        String archivo=file.getName();
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        // add another part within the multipart request
        String descriptionString = "hello, this is description speaking";
        RequestBody description =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), descriptionString);

        // finally, execute the request
        Call<ResponseBody> call = apiService.upload(description, body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                Log.v("Upload", "success");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
            }
        });
    }





}
