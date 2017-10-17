package com.muequeta.entrega2;


        import android.os.Parcel;
        import android.os.Parcelable;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;



public class Lugar   {

    private String coordenadas;
    private String descripcion;
    private Integer id;
    private List<Imagene> imagenes = new ArrayList<Imagene>();
    private String nombre;
    private List<Video> videos = new ArrayList<Video>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The coordenadas
     */
    public String getCoordenadas() {
        return coordenadas;
    }

    /**
     *
     * @param coordenadas
     * The coordenadas
     */
    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    /**
     *
     * @return
     * The descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     *
     * @param descripcion
     * The descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The imagenes
     */
    public List<Imagene> getImagenes() {
        return imagenes;
    }

    /**
     *
     * @param imagenes
     * The imagenes
     */
    public void setImagenes(List<Imagene> imagenes) {
        this.imagenes = imagenes;
    }

    /**
     *
     * @return
     * The nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     * The nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     * The videos
     */
    public List<Video> getVideos() {
        return videos;
    }

    /**
     *
     * @param videos
     * The videos
     */
    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }


}




