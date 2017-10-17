package com.muequeta.entrega2;

/**
 * Created by jairo on 30/10/2016.
 */


import java.util.HashMap;
import java.util.Map;



import java.util.HashMap;
import java.util.Map;


public class Hora {

    private String fini;
    private String inicio;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The fini
     */
    public String getFinal() {
        return fini;
    }

    /**
     *
     * @param fini
     * The final
     */
    public void setFinal(String fini) {
        this.fini = fini;
    }

    /**
     *
     * @return
     * The inicio
     */
    public String getInicio() {
        return inicio;
    }

    /**
     *
     * @param inicio
     * The inicio
     */
    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}