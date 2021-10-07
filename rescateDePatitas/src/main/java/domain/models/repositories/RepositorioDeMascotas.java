package domain.models.repositories;

import domain.models.entities.mascotas.Mascota;
import domain.models.repositories.daos.DAO;
import java.util.List;
import java.util.Optional;

public class RepositorioDeMascotas extends RepositorioGenerico<Mascota>{
    public List<Mascota> mascotas;

    public RepositorioDeMascotas(DAO<Mascota> dao) {
        super(dao);
    }

//    public Optional<Mascota> buscar(Integer idMascota) {
//        return this.mascotas.stream()
//                .filter(n -> n.getId() == idMascota)
//                .findFirst();
//    }
}
