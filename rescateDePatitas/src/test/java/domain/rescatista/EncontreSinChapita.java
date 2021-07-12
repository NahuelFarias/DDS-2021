package domain.rescatista;

import domain.models.entities.mascotas.*;
import domain.models.entities.notificaciones.estrategias.Estrategia;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;
import domain.models.entities.personas.TipoDeDocumento;
import domain.models.entities.publicaciones.GestorDePublicaciones;
import domain.models.entities.rol.Rescatista;
import org.junit.Before;
import org.junit.Test;
import services.EditorDeFotos;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EncontreSinChapita {

    Persona personaDuenio;
    Persona personaRescatista;
    Rescatista rescatista;

    Contacto contacto1,contacto2;
    List<Contacto> contactos;
    Foto foto;
    List<Foto> fotos;
    EditorDeFotos editor;
    GestorDePublicaciones gestor;
    Lugar lugar;
    String descripcion;
    DatosMascotaPerdida datosMascota;

    @Before
    public void instanciar() throws IOException {
        personaRescatista = new Persona();
        contacto1 = new Contacto("Soledad", "Grilleta", "+541122222242", "sole.012@gmail.com", Estrategia.EMAIL);
        contacto2 = new Contacto("Nahuel", "Farias", "+541138338092", "nfarias@frba.utn.edu.ar", Estrategia.SMS);
        contactos = new ArrayList<>();
        contactos.add(contacto1);
        contactos.add(contacto2);
        personaRescatista.inicializar("Maria Victoria", "Sanchez", "Peru 1212,CABA", TipoDeDocumento.DNI, 3333333, 27081996,contactos);

        rescatista = new Rescatista();
        personaRescatista.setRol(rescatista);
        //Hasta aca la creacion de un rescatista

        fotos = new ArrayList<>();
        foto = new Foto();
        foto.setURLfoto("src/main/resources/FotoDePrueba2.jpg");
        fotos.add(foto);
        editor = new EditorDeFotos();
        fotos= editor.redimensionarFotos(fotos);
        lugar = new Lugar("1231231", "4342342");
        descripcion = "La encontre el dia 7/7 en buen estado.";

        datosMascota = new DatosMascotaPerdida(fotos,descripcion,lugar);

    }

    @Test
    public void elRescatistaEncuentraUnaMascotaSinQR() {

        personaRescatista.encontreUnaMascotaPerdidaSinChapita(personaRescatista,datosMascota);
        personaRescatista.encontreUnaMascotaPerdidaSinChapita(personaRescatista,datosMascota);
        personaRescatista.encontreUnaMascotaPerdidaSinChapita(personaRescatista,datosMascota);

        gestor = GestorDePublicaciones.getInstancia();

        System.out.println(gestor.getPublicaciones().get(0).getDescripcion());
        assertEquals(gestor.getPublicaciones().size(),3);

    }
}
