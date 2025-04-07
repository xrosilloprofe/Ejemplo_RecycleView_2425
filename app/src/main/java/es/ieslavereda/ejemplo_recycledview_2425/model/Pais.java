package es.ieslavereda.ejemplo_recycledview_2425.model;

import java.io.Serializable;

public class Pais implements Serializable, Comparable<Pais> {
    private String nombre;
    private String banderaURL;
    private String detalle;

    public Pais(String nombre, String banderaURL, String detalle) {
        this.nombre = nombre;
        this.banderaURL = banderaURL;
        this.detalle = detalle;
    }
    public String getNombre() {
        return nombre;
    }

    public String getBanderaURL() {
        return banderaURL;
    }

    public String getDetalle() { return detalle;}

    @Override
    public int compareTo(Pais pais) {
        return nombre.compareToIgnoreCase(pais.getNombre());
    }
}
