package domain.models.repositories;

import domain.models.entities.mascotas.Caracteristica;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeCaracteristicas {
    private static RepositorioDeCaracteristicas instancia;
    public List<Caracteristica> caracteristicas = new ArrayList<Caracteristica>();


    public static RepositorioDeCaracteristicas getInstancia() {
        if (instancia == null) {
            instancia=new RepositorioDeCaracteristicas();
        }
        return instancia;
    }

    public void agregar(Caracteristica caracteristica){
        this.caracteristicas.add(caracteristica);
    }

    public List<Caracteristica> buscarTodos(){
        return caracteristicas;

    }

    public void eliminar(Caracteristica caracteristica){
        //TODO
    }

    public Caracteristica buscar(String descripcion){
        //TODO caracteristicas.stream().filter(d -> d.getDescripcion() == descripcion);
        Caracteristica caracteristica = new Caracteristica();
        return caracteristica;
    }
}
