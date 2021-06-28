package domain.models.repositories;


import domain.models.entities.personas.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositorioDeUsuarios {
    public List<Usuario> usuarios;


    public Optional<Usuario> buscar(String nombreDeUsuario){
       return this.usuarios.stream()
                                    .filter(n -> n.getNombreDeUsuario() == nombreDeUsuario)
                                    .findFirst();
    }

    public void aniadir(Usuario usuario){
        usuarios.add(usuario);
    }
}
