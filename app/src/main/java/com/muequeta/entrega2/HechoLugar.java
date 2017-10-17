package com.muequeta.entrega2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jairo on 31/10/2016.
 */

public class HechoLugar {

    private List<Integer> hechos = new ArrayList<Integer>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The hechos
     */
    public List<Integer> getHechos() {
        return hechos;
    }

    /**
     *
     * @param hechos
     * The hechos
     */
    public void setHechos(List<Integer> hechos) {
        this.hechos = hechos;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
