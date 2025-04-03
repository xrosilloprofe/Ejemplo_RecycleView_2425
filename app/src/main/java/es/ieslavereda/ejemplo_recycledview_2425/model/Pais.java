package es.ieslavereda.ejemplo_recycledview_2425.model;

public class Pais {
    private String nombre;
    private String banderaURL;

    public Pais(String nombre, String banderaURL) {
        this.nombre = nombre;
        this.banderaURL = banderaURL;
    }
    public String getNombre() {
        return nombre;
    }

    public String getBanderaURL() {
        return banderaURL;
    }
}
