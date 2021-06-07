package domain.models.entities.hogares;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ubicacion {
    public String direccion;
    @JsonProperty("lat")
    public int latitud;
    @JsonProperty("long")
    public int longitud;
}
