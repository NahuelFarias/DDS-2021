package domain.models.repositories;

import domain.models.entities.mascotas.Mascota;
import domain.models.repositories.daos.DAO;

public class RepositorioDeMascotas extends RepositorioGenerico<Mascota>{

    public RepositorioDeMascotas(DAO<Mascota> dao) {
        super(dao);
    }
}
