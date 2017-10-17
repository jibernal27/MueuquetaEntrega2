package com.muequeta.entrega2;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by jairo on 30/10/2016.
 */

public interface  EndPoints
{
    @GET("lugar/{idLugar}")
    Call<Lugar> getLugar(@Path("idLugar") String idLugar);

    @GET("hechoLugar/{idLugar}")
    Call<HechoLugar> getHechoLugar(@Path("idLugar") String idLugar);

    @GET("hecho/{idHecho}")
    Call<Hecho> getHecho(@Path("idHecho") String idHecho);

    @Multipart
    @POST("subirJSON")
    Call<ResponseBody> upload(@Part("description") RequestBody description,
                              @Part MultipartBody.Part file);


}
