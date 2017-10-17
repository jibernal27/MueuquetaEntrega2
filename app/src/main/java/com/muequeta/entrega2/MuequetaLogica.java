package com.muequeta.entrega2;


import android.content.ContextWrapper;

import android.content.Intent;
import android.os.Environment;

import android.text.Editable;
import android.util.JsonWriter;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by jairo on 29/10/2016.
 */

public class MuequetaLogica
{
    private static MuequetaLogica instance = null;

    private ArrayList<Ubicacion> recientes;
    private JsonWriter writer;
    private OutputStream out;
    public static final String UBICACIONES="ubicaciones.json";
    private boolean guardarUbicaciones;
    private  ContextWrapper cw ;

    private File fileUbi;

    private HashMap<Integer,Lugar> mapLugares ;
    private HashMap< Integer,Hecho> mapHechos ;
    protected MuequetaLogica()
    {

        recientes= new ArrayList<Ubicacion>();
        guardarUbicaciones=true;
        mapLugares= new  HashMap< Integer,Lugar>();
        mapHechos= new HashMap<Integer,Hecho>();


    }

    public void agregarUbicacion(double latitud, double longitud, double altura, Date fecha)
    {
        Ubicacion nueva= new Ubicacion(latitud,longitud,altura,fecha);
        recientes.add(nueva);
    }

    public static MuequetaLogica getInstance() {
        if(instance == null) {
            instance = new MuequetaLogica();
        }
        return instance;
    }


    public void persistirJSONUbicaciones()
    {

        try {

            File sdCard = Environment.getExternalStorageDirectory();
            File dir = new File (sdCard.getAbsolutePath() + "/ubicacion");
            dir.mkdirs();
             fileUbi = new File(dir, UBICACIONES);



            if(!fileUbi.exists())

            {
                fileUbi.createNewFile();
            }

            out = new FileOutputStream(fileUbi,true);
            JsonWriter writer  = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.setIndent("");

            writer.beginArray();

            for (Ubicacion actual:recientes
                    ) {

                writer.beginObject();
                writer.name("latitud").value(actual.getLatitud());
                writer.name("longitud").value(actual.getLongitud());
                writer.name("altura").value(actual.getAltura());
                writer.name("fecha").value(actual.getFecha().toString());
                writer.endObject();
                Log.d("Guardo",actual.getFecha().toString());


            }

            writer.endArray();


            writer.close();
            out.close();
            Log.d("TERMINO","termino");
            //MediaScannerConnection.scanFile(con, new String[] {dir.toString()}, null, null);



        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    public void enviarAlServidorUbicaciones()
    {
        File sdCard = Environment.getExternalStorageDirectory();
        File dir = new File (sdCard.getAbsolutePath() + "/ubicacion");
        dir.mkdirs();
        fileUbi = new File(dir, UBICACIONES);
            ConsumidorAPI con=new ConsumidorAPI();
            con.uploadFile(fileUbi);
            recientes= new ArrayList<Ubicacion>();




    }

    private void cerrar()

    {
        try {

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    public static String getStringFromFile (String filePath) throws Exception {
        File fl = new File(filePath);
        FileInputStream fin = new FileInputStream(fl);
        String ret = convertStreamToString(fin);
        //Make sure you close all streams.
        fin.close();
        return ret;
    }

    public boolean consumirDatos()
    {
        return guardarUbicaciones;
    }

    public void noGuardarUbicaciones()
    {
        guardarUbicaciones=false;
    }

    public void guardarUbicaciones()
    {
        guardarUbicaciones=true;
    }

    public ArrayList<Lugar> darLugaresCerca(double lati, double longi)
    {

        ArrayList<Lugar> aResponder= new ArrayList<Lugar> ();


        for(int i=0;i<10;i++)

        {
            Lugar un=darLugarPorID(i);
            aResponder.add(un);
        }


        return aResponder;

    }

    public Lugar darLugarPorID(Integer id)
    {
        Lugar bus=mapLugares.get(id);
        if (bus==null)
        {
            ConsumidorAPI con=new ConsumidorAPI();
            bus=con.getLugar(id+"");
            mapLugares.put(id,bus);
        }

        return bus;
    }

    public void enviarImagen(File ultimaGuardada, String text)
    {
        ConsumidorAPI con=new ConsumidorAPI();
        con.uploadFile(ultimaGuardada);

    }

    public Hecho darHechoPorID(int idHecho)
    {
        Hecho bus=mapHechos.get(idHecho);
        if (bus==null)
        {
            ConsumidorAPI con=new ConsumidorAPI();
            bus=con.getHecho(idHecho+"");
            mapHechos.put(idHecho,bus);
        }

        return bus;

    }

    public int actualizarHechos(int idLugar)
    {
            int aRet=0;
        ConsumidorAPI con=new ConsumidorAPI();
        HechoLugar hechos= con.getHechoLugar(idLugar);
        for (Integer actual:hechos.getHechos()
                ) {

            darHechoPorID(actual);
            aRet++;

        }
        return aRet;
    }
    public Hecho darHechoPorIDAHora(int idLugar)
    {


        Date date = new Date();   // given date
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        int horaActual=calendar.get(Calendar.HOUR_OF_DAY);
        int minutoActual=calendar.get(Calendar.MINUTE);


        Iterator it = mapHechos.entrySet().iterator();
        while (it.hasNext()) {

            Map.Entry pair = (Map.Entry)it.next();
            Hecho   pairi= (Hecho) pair.getValue();
            String horaIni=pairi.getHoras().get(0).getInicio();
            int minutoIni= Integer.parseInt(horaIni.split(":")[1]);
            int horaInic= Integer.parseInt(horaIni.split(":")[0]);

            String horaFin=pairi.getHoras().get(0).getFinal();
            int minutoFin= Integer.parseInt(horaFin.split(":")[1]);
            int horaFini= Integer.parseInt(horaFin.split(":")[0]);

            if (horaInic<=horaActual&&horaFini>=horaActual)
            {

                return pairi;
            }

        }

        return null;

    }
}
