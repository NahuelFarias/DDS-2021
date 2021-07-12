package domain.rescatista;

import domain.controllers.CaracteristicasController;
import domain.models.entities.mascotas.*;
import domain.models.entities.notificaciones.estrategias.Estrategia;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;
import domain.models.entities.personas.TipoDeDocumento;
import domain.models.entities.rol.Duenio;
import domain.models.entities.rol.Rescatista;
import org.junit.Before;
import org.junit.Test;
import services.EditorDeFotos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RescatistaNotifica {
    Persona personaDuenio;
    Persona personaRescatista;
    Duenio duenio;
    Rescatista rescatista;

    CaracteristicasController controller;
    CaracteristicaConRta caracteristicaConRta1;
    CaracteristicaConRta caracteristicaConRta2;
    ArrayList<CaracteristicaConRta> caracteristicasConRtas;
    List<Caracteristica> caracteristicas;
    ArrayList<String> rtas, rtas2;

    Contacto contacto1,contacto2,contacto3;
    List<Contacto> contactos, contactosRescatista;
    Foto foto;
    List<Foto> fotos;
    EditorDeFotos editor;

    @Before
    public void instanciar() throws IOException {
        personaDuenio = new Persona();

        contacto1 = new Contacto("Soledad", "Grilleta", "+541122222242", "sole.012@gmail.com", Estrategia.EMAIL);
        contacto2 = new Contacto("Nahuel", "Farias", "+541138338092", "nfarias@frba.utn.edu.ar", Estrategia.SMS);

        contactos = new ArrayList<>();
        contactos.add(contacto1);
        contactos.add(contacto2);
        personaDuenio.inicializar("Maria Victoria", "Sanchez", "Peru 1212,CABA", TipoDeDocumento.DNI, 3333333, 27081996,contactos);

        duenio = new Duenio();

        personaDuenio.setRol(duenio);

        //Empiezo a cargar caracteristicas al repositorio

        controller = CaracteristicasController.getInstancia();
        rtas = new ArrayList<>();
        rtas.add("Si");
        rtas.add("No");
        controller.crearCaracteristica("Esta castrado", rtas);

        rtas2 = new ArrayList<>();
        rtas2.add("Negro");
        rtas2.add("Marron");
        rtas2.add("Rubio");
        rtas2.add("Ninguno de estos");
        controller.crearCaracteristica("Color principal", rtas2);
        //Termino de cargar caracteristicas al repositorio

        caracteristicas = controller.getRepositorio().caracteristicas;

        caracteristicaConRta1 = new CaracteristicaConRta(caracteristicas.get(0).getDescripcion(),"Si");
        caracteristicaConRta2 = new CaracteristicaConRta(caracteristicas.get(1).getDescripcion(),"Negro");

        caracteristicasConRtas = new ArrayList<>();
        caracteristicasConRtas.add(caracteristicaConRta1);
        caracteristicasConRtas.add(caracteristicaConRta2);

        fotos = new ArrayList<>();
        foto = new Foto();
        foto.setURLfoto("src/main/resources/FotoDePrueba2.jpg");
        fotos.add(foto);
        editor = new EditorDeFotos();
        fotos= editor.redimensionarFotos(fotos);

        //mascota
        Mascota.MascotaDTO mascotaDTO = new Mascota.MascotaDTO();
        mascotaDTO.inicializar(personaDuenio,"Susana","Susi",2,"tiene una mancha blanca en una pata.",
                "gato", "hembra", caracteristicasConRtas, fotos);

        personaDuenio.registrarMascota(mascotaDTO);
        //Termina de crear una mascota con duenio//

        personaRescatista = new Persona();

        contacto3 = new Contacto("Roberto Francisco", "Ginez", "+541138138227", "rginez@gmail.com", Estrategia.SMS);

        contactosRescatista = new ArrayList<>();
        contactosRescatista.add(contacto3);

        personaRescatista.inicializar("Roberto Francisco", "Ginez", "Brasil 1112,CABA", TipoDeDocumento.DNI, 33311111, 30031998, contactosRescatista);

        rescatista = new Rescatista();

        personaRescatista.setRol(rescatista);

        rescatista.agregarMascota(mascotaDTO);
    }

    @Test
    public void elRescatistaEncuentraUnaMascotaConQR() {
        Lugar lugar = new Lugar(-34.6692966,-58.4766928);
        Mascota mascotaPerdida = personaDuenio.getRol().getMascotas().get(0);

        personaRescatista.encontreUnaMascotaPerdida(mascotaPerdida, personaRescatista.getContactos().get(0), fotos, "En una plaza toda sucia", lugar);
    }
}