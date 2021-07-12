package domain.models.entities.mascotas;

public class Lugar {
    private double latitud;
    private double longitud;

    public Lugar(double latitud, double longitud) {
        setLatitud(latitud);
        setLongitud(longitud);
    }

    public double getLatitud() {
        return this.latitud;
    }

    public double getLongitud() {
        return this.longitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}
