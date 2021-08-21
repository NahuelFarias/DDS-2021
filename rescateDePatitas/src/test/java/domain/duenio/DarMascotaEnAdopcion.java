package domain.duenio;

import domain.controllers.CaracteristicasController;
import domain.models.entities.mascotas.*;
import domain.models.entities.notificaciones.estrategias.Estrategia;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;
import domain.models.entities.personas.TipoDeDocumento;
import domain.models.entities.publicaciones.GestorDePublicaciones;
import domain.models.entities.publicaciones.Pregunta;
import domain.models.entities.publicaciones.RespuestaSobrePregunta;
import domain.models.entities.rol.Duenio;
import domain.models.entities.rol.Rol;
import domain.models.repositories.RepositorioDeCaracteristicas;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import services.EditorDeFotos;

import java.util.ArrayList;
import java.util.List;

public class DarMascotaEnAdopcion {
    Persona persona;
    Rol duenio;
    RepositorioDeCaracteristicas repoCaracteristicas;
    CaracteristicasController controller;
    CaracteristicaConRta caracteristicaConRta1, caracteristicaConRta2, caracteristicaConRta3, caracteristicaConRta4;
    ArrayList<CaracteristicaConRta> caracteristicasConRtas, caracteristicasConRtas2;
    List<Caracteristica> caracteristicas;
    List<Contacto> contactos;
    List<Foto> fotos;
    Contacto contacto1,contacto2;
    EditorDeFotos editor;
    Organizacion organizacion;
    RespuestaSobrePregunta rt1, rt2;
    List<RespuestaSobrePregunta> respuestasOrganizacion;
    List<RespuestaSobrePregunta> respuestasGenerales;
    Pregunta preguntaTieneGatos;
    Pregunta preguntaTienePatio;


    @Before
    public void Instanciar() {
        persona = new Persona();
        duenio = new Duenio();
        repoCaracteristicas = new RepositorioDeCaracteristicas();
        contactos = new ArrayList<>();

        //Cargo caracteristicas al repositorio con el controller
        controller = CaracteristicasController.getInstancia();
        ArrayList<String> rtas = new ArrayList<>();
        rtas.add("Si");
        rtas.add("No");
        controller.crearCaracteristica("Esta castrado", rtas);

        ArrayList<String> rtas2 = new ArrayList<>();
        rtas2.add("Negro");
        rtas2.add("Marron");
        rtas2.add("Rubio");
        rtas2.add("Ninguno de estos");
        controller.crearCaracteristica("Color principal", rtas2);
        //Termino de cargar caracteristicas al repositorio

        contacto1 = new Contacto("Maria Victoria","Sanchez","1155555555","mvicsanchez@gmail.com", Estrategia.SMS);
        contacto2 = new Contacto("Agustin","Greco","1166666666","agugreco@gmail.com", Estrategia.SMS);
        contactos.add(contacto1);
        contactos.add(contacto2);

        persona.inicializar("Maria Victoria","Sanchez","Peru 1212,CABA", TipoDeDocumento.DNI,3333333,27081996,contactos);

        persona.setRol(duenio);

        List<Caracteristica> caracteristicas = controller.getRepositorio().caracteristicas;

        caracteristicaConRta1 = new CaracteristicaConRta(caracteristicas.get(0).getDescripcion(),"Si");
        caracteristicaConRta2 = new CaracteristicaConRta(caracteristicas.get(1).getDescripcion(),"Negro");

        caracteristicasConRtas = new ArrayList<>();
        caracteristicasConRtas.add(caracteristicaConRta1);
        caracteristicasConRtas.add(caracteristicaConRta2);

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

        caracteristicaConRta3 = new CaracteristicaConRta(caracteristicas.get(0).getDescripcion(),"No");
        caracteristicaConRta4 = new CaracteristicaConRta(caracteristicas.get(1).getDescripcion(),"Marron");

        caracteristicasConRtas2 = new ArrayList<>();
        caracteristicasConRtas2.add(caracteristicaConRta3);
        caracteristicasConRtas2.add(caracteristicaConRta4);

        ArrayList<Foto> fotos2 = new ArrayList<>();
        Foto foto2 = new Foto();
        foto2.setURLfoto("src/main/resources/FotoDePrueba.jpg");
        fotos2.add(foto2);

        fotos= editor.redimensionarFotos(fotos);

        mascotaDTO.inicializar(persona,"Susana","Susi",2,"tiene una mancha blanca en una pata.",
                "gato", "hembra", caracteristicasConRtas, fotos);
        persona.registrarMascota(mascotaDTO);

        organizacion = new Organizacion();

        preguntaTieneGatos = new Pregunta();
        preguntaTieneGatos.setPregunta("Tiene gatos?");
        preguntaTienePatio = new Pregunta();
        preguntaTienePatio.setPregunta("Tiene patio en su casa?");

        respuestasOrganizacion = new ArrayList<>();
        rt1 = new RespuestaSobrePregunta();
        rt1.setPregunta(preguntaTieneGatos);
        rt1.setRespuesta("Si");

        rt2 = new RespuestaSobrePregunta();
        rt2.setPregunta(preguntaTienePatio);
        rt2.setRespuesta("No");

        respuestasOrganizacion.add(rt1);
        respuestasOrganizacion.add(rt2);

        preguntaTieneGatos = new Pregunta();
        preguntaTieneGatos.setPregunta("Tiene gatos?");
        preguntaTienePatio = new Pregunta();
        preguntaTienePatio.setPregunta("Tiene patio en su casa?");

        respuestasGenerales = new ArrayList<>();
        rt1 = new RespuestaSobrePregunta();
        rt1.setPregunta(preguntaTieneGatos);
        rt1.setRespuesta("Si");

        rt2 = new RespuestaSobrePregunta();
        rt2.setPregunta(preguntaTienePatio);
        rt2.setRespuesta("No");

        respuestasGenerales.add(rt1);
        respuestasGenerales.add(rt2);

    }

    @Test
    public void darEnAdopcionMiMascota() {
        persona.getRol().darEnAdopcion(persona.getRol().getMascotas().get(0),organizacion,respuestasOrganizacion,respuestasGenerales);
        GestorDePublicaciones gestor = GestorDePublicaciones.getInstancia();
        Assert.assertEquals("Mascota dada en adopcion",gestor.getPublicaciones().get(0).getTipoPublicacion());
        Assert.assertEquals(persona.getRol().getMascotas().get(0),gestor.getPublicaciones().get(0).getMascota());
    //TODO
    }

}
