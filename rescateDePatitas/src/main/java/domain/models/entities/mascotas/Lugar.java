package domain.models.entities.mascotas;

public class Lugar {
    private String latitud;
    private String longitud;

    public Lugar(String latitud, String longitud) {
        setLatitud(latitud);
        setLongitud(longitud);
    }

    public String getLatitud() {
        return this.latitud;
    }

    public String getLongitud() {
        return this.longitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}
