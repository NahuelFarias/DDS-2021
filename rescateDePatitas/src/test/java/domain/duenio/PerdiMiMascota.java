package domain.duenio;

import domain.controllers.CaracteristicasController;
import domain.models.entities.mascotas.Caracteristica;
import domain.models.entities.mascotas.CaracteristicaConRta;
import domain.models.entities.mascotas.Foto;
import domain.models.entities.mascotas.Mascota;
import domain.models.entities.notificaciones.estrategias.Estrategia;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;
import domain.models.entities.personas.TipoDeDocumento;
import domain.models.entities.rol.Duenio;
import domain.models.entities.rol.Rol;
import domain.models.repositories.RepositorioDeCaracteristicas;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import services.EditorDeFotos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PerdiMiMascota {
    Persona persona;
    Rol duenio;
    RepositorioDeCaracteristicas repoCaracteristicas;
    CaracteristicasController controller;
    CaracteristicaConRta caracteristicaConRta1;
    CaracteristicaConRta caracteristicaConRta2;
    ArrayList<CaracteristicaConRta> caracteristicasConRtas;
    List<Contacto> contactos;
    Contacto contacto1, contacto2;
    EditorDeFotos editor;


    @Before
    public void Instanciar() {
        persona = new Persona();
        duenio = new Duenio();
        repoCaracteristicas = new RepositorioDeCaracteristicas();
        contactos = new ArrayList<>();

        contacto1 = new Contacto("Maria Victoria", "Sanchez", "1155555555", "mvicsanchez@gmail.com", Estrategia.SMS);
        contacto2 = new Contacto("Agustin", "Greco", "1166666666", "agugreco@gmail.com", Estrategia.SMS);
    }

    @Test
    public void crearPersonaConMascotasTest() {
        contactos.add(contacto1);
        contactos.add(contacto2);

        persona.inicializar("Maria Victoria", "Sanchez", "Peru 1212,CABA", TipoDeDocumento.DNI, 3333333, 27081996, contactos);

        //Agrego el rol duenio a la persona para que pueda registrar sus mascotas
        persona.setRol(duenio);

        //Cargo caracteristicas al repositorio con el controller
        controller = CaracteristicasController.getInstancia();
        ArrayList<String> rtas = new ArrayList<String>();
        rtas.add("Si");
        rtas.add("No");
        controller.crearCaracteristica("Esta castrado", rtas);

        ArrayList<String> rtas2 = new ArrayList<String>();
        rtas2.add("Negro");
        rtas2.add("Marron");
        rtas2.add("Rubio");
        rtas2.add("Ninguno de estos");
        controller.crearCaracteristica("Color principal", rtas2);
        //Termino de cargar caracteristicas al repositorio

        //Registro de 1 mascota
        List<Caracteristica> caracteristicas = controller.getRepositorio().caracteristicas;

        caracteristicaConRta1 = new CaracteristicaConRta(caracteristicas.get(0).getDescripcion(), "Si");
        caracteristicaConRta2 = new CaracteristicaConRta(caracteristicas.get(1).getDescripcion(), "Negro");

        //Armo la lista de caracteristicas para agregar a la mascota
        caracteristicasConRtas = new ArrayList<CaracteristicaConRta>();
        caracteristicasConRtas.add(caracteristicaConRta1);
        caracteristicasConRtas.add(caracteristicaConRta2);

        //Redimensiono las fotos para agregar a la mascota
        List<Foto> fotos = new ArrayList<>();
        Foto foto = new Foto();
        foto.setURLfoto("src/main/resources/FotoDePrueba2.jpg");
        fotos.add(foto);
        editor = new EditorDeFotos();
        fotos= editor.redimensionarFotos(fotos);

        Mascota.MascotaDTO mascotaDTO = new Mascota.MascotaDTO();
        mascotaDTO.inicializar(persona,"Susana","Susi",2,"tiene una mancha blanca en una pata.",
                "gato", "hembra", caracteristicasConRtas, fotos);
        persona.registrarMascota(mascotaDTO);

        Date fechaActual = new Date();
        persona.getRol().perdiUnaMascota(persona.getRol().getMascotas().get(0));

        Assert.assertEquals(fechaActual, persona.getRol().getMascotas().get(0).getFechaDesdeQueEstaPerdida());
    }
}
