package com.muequeta.entrega2;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jairo on 30/10/2016.
 */


    public class Video {

        private String descripcion;
        private String direccion;
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
         * The direccion
         */
        public String getDireccion() {
            return direccion;
        }

        /**
         *
         * @param direccion
         * The direccion
         */
        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }

