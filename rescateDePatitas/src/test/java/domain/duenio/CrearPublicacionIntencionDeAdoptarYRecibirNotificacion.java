package domain.duenio;

import domain.controllers.CaracteristicasController;
import domain.models.entities.mascotas.CaracteristicaConRta;
import domain.models.entities.notificaciones.estrategias.Estrategia;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;
import domain.models.entities.personas.TipoDeDocumento;
import domain.models.repositories.RepositorioDeCaracteristicas;
import org.junit.Before;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CrearPublicacionIntencionDeAdoptarYRecibirNotificacion {
    Persona persona;
    RepositorioDeCaracteristicas repoCaracteristicas;
    CaracteristicasController controller;
    CaracteristicaConRta caracteristicaConRta1, getCaracteristicaConRta2, getGetCaracteristicaConRta3;
    List<Contacto> contactos;
    Contacto contacto;


    @Before
    public void Instanciar() {
        persona = new Persona();
        repoCaracteristicas = new RepositorioDeCaracteristicas();
        contactos = new ArrayList<>();

        controller = CaracteristicasController.getInstancia();
        ArrayList<String> rtas1 = new ArrayList<>();
        rtas1.add("Sí");
        rtas1.add("No");
        controller.crearCaracteristica("Esta castrado", rtas1);

        ArrayList<String> rtas2 = new ArrayList<>();
        rtas2.add("Negro");
        rtas2.add("Marron");
        rtas2.add("Rubio");
        rtas2.add("Ninguno de estos");

        controller.crearCaracteristica("Color principal", rtas2);

        contacto = new Contacto("Pedro Elias", "Kuljich", "1112345678", "pedrokuljich@gmail.com", Estrategia.EMAIL);
        contactos.add(contacto);

        persona.inicializar("Pedro Elias", "Kuljich", "Calle Falsa 123, CABA", TipoDeDocumento.DNI, 3141592, LocalDate.of(2000, 3, 3), contactos);
    }
}
