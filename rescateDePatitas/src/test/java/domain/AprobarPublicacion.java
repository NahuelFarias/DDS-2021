package domain;

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
    Voluntario voluntario;

    @Before

    public void Instanciar(){
        persona = new Persona();
        veteCan = new Organizacion();
        publicacion = new Publicacion();
        voluntario = new Voluntario();
    }

    @Test
    public void aprobarPublicacionTest(){

        veteCan.generarVoluntario(persona);

        veteCan.controlarPublicaciones(persona);

    }

}
