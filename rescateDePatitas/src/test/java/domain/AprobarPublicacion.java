package domain;

import com.sun.codemodel.internal.JForEach;
import com.sun.tools.javac.code.Attribute;
import domain.models.entities.mascotas.Organizacion;
import domain.models.entities.mascotas.Publicacion;
import domain.models.entities.personas.Persona;
import domain.models.entities.personas.TipoDeDocumento;
import domain.models.entities.rol.Voluntario;
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

        System.out.println(veteCan.getPublicacionesAceptadas().size());

        veteCan.generarVoluntario(persona);
        veteCan.getPublicacionesAceptadas();
        veteCan.getPublicacionesARevisar().add(publicacion);
        veteCan.getPublicacionesARevisar().add(publicacion2);
        veteCan.getPublicacionesARevisar().add(publicacion3);

        System.out.println(veteCan.getPublicacionesARevisar().size());


        veteCan.controlarPublicaciones(persona);

        System.out.println(veteCan.getPublicacionesAceptadas().size());
        System.out.println(veteCan.getPublicacionesARevisar().size());

    }

}
