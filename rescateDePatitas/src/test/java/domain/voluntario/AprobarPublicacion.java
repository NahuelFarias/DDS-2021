package domain.voluntario;

import domain.models.entities.mascotas.Organizacion;
import domain.models.entities.publicaciones.Publicacion;
import domain.models.entities.personas.Persona;
import domain.models.entities.rol.Voluntario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AprobarPublicacion {
    Persona persona;
    Organizacion veteCan;
    Publicacion publicacion;
    Publicacion publicacion2;
    Publicacion publicacion3;
    Voluntario voluntario;

    @Before

    public void Instanciar(){
        persona = new Persona();
        veteCan = new Organizacion();
        publicacion = new Publicacion();
        publicacion2 = new Publicacion();
        publicacion3 = new Publicacion();
        voluntario = new Voluntario();

        publicacion.setDescripcion("mascota1");
        publicacion2.setDescripcion("mascota2");
        publicacion3.setDescripcion("mascota3");
    }

    @Test
    public void aprobarPublicacionTest(){

        veteCan.generarVoluntario(persona);
        //veteCan.getPublicaciones();

        veteCan.getPublicaciones().add(publicacion);
        System.out.println(veteCan.getPublicaciones().size());
        veteCan.getPublicaciones().add(publicacion2);
        System.out.println(veteCan.getPublicaciones().size());
        veteCan.getPublicaciones().add(publicacion3);
        System.out.println(veteCan.getPublicaciones().size());

        veteCan.controlarPublicaciones(persona);
        System.out.println(veteCan.getPublicaciones().size());

        Assert.assertTrue(veteCan.getPublicaciones().size() == 0);
    }

}
