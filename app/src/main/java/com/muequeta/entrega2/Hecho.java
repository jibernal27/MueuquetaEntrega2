package com.muequeta.entrega2;



        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;


public class Hecho {

    private String descripcion;
    private List<Hora> horas = new ArrayList<Hora>();
    private Integer id;
    private List<Imagene> imagenes = new ArrayList<Imagene>();
    private String nombre;
    private List<Video> videos = new ArrayList<Video>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
     * The horas
     */
    public List<Hora> getHoras() {
        return horas;
    }

    /**
     *
     * @param horas
     * The horas
     */
    public void setHoras(List<Hora> horas) {
        this.horas = horas;
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
