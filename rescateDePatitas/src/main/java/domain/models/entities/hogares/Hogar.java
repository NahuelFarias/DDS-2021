package domain.models.entities.hogares;

import java.util.List;

public class Hogar {
    public String id;
    public String nombre;
    public Ubicacion direccion;
    public String telefono;
    public Admision admisiones;
    public int capacidad;
    public int lugares_disponibles;
    public boolean patio;
    public List<String> caracteristicas;
}
