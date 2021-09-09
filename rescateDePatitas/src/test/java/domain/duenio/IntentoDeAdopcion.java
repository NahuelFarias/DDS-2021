package domain.duenio;

import domain.models.entities.mascotas.Lugar;
import domain.models.entities.mascotas.Organizacion;
import domain.models.entities.notificaciones.estrategias.Estrategia;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;
import domain.models.entities.personas.TipoDeDocumento;
import domain.models.entities.publicaciones.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IntentoDeAdopcion {

    Persona persona,persona2;
    Contacto contacto1;
    List<Contacto> contactos;

    Lugar ubicacion;
    GestorDePublicaciones gestor;
    Organizacion org1,org2,org3,org4;
    List<Organizacion> organizaciones;
    RespuestaSobrePregunta rt1, rt2;
    List<RespuestaSobrePregunta> respuestas;

    Pregunta preguntaTieneGatos;
    Pregunta preguntaTienePatio;
    PublicacionIntencionAdopcion publicacion;
    Cuestionario cuestionarioPref;
    Cuestionario cuestionarioCom;
    List<RespuestaSobrePregunta> respuestasCuestionarioPref;
    List<Pregunta> preguntasCuestionarioPref;
    RespuestaSobrePregunta r1,r2,r3,r4;
    Pregunta preg1,preg2,preg3,preg4;
    List<RespuestaSobrePregunta> respuestasCuestionarioCom;
    List<Pregunta> preguntasCuestionarioCom;


    @Before
    public void instanciar() throws IOException {
        //persona
        persona = new Persona();
        persona2 = new Persona();


        contactos = new ArrayList<>();
        contacto1 = new Contacto("Aracely", "Amaro", "+541168648864", "ara6amaro@gmail.com", Estrategia.EMAIL);
        contactos.add(contacto1);

        persona.inicializar("Aracely", "Amaro", "qwerty 890", TipoDeDocumento.DNI,33333333, LocalDate.of(1987, 9, 24), contactos);

        //organizaciones
        organizaciones= new ArrayList<>();

        org1 = new Organizacion();
        org1.setNombre("Huellitas");
        org1.setUbicacion(new Lugar(-34.6335328,-58.4921025));
        organizaciones.add(org1);

        org2 = new Organizacion();
        org2.setNombre("Naricitas Frias");
        org2.setUbicacion(new Lugar(-34.5888834,-58.5455626));
        organizaciones.add(org2);

        org3 = new Organizacion();
        org3.setNombre("El Hogar de Claudia");
        org3.setUbicacion(new Lugar(-34.6038713,-58.5754228));
        organizaciones.add(org3);

        org4 = new Organizacion();
        org4.setNombre("Ayudacan");
        org4.setUbicacion(new Lugar(-34.6321582,-58.468661));
        organizaciones.add(org4);

        gestor = GestorDePublicaciones.getInstancia();
        gestor.setOrganizaciones(organizaciones);

        //lugar
        ubicacion = new Lugar(-34.6236837, -58.4613992);

        //preferencias y comodidades

        preguntaTieneGatos= new Pregunta();
        preguntaTieneGatos.setPregunta("Tiene gatos?");
        preguntaTienePatio = new Pregunta();
        preguntaTienePatio.setPregunta("Tiene patio en su casa?");

        respuestas = new ArrayList<>();
        rt1 = new RespuestaSobrePregunta();
        rt1.setPregunta(preguntaTieneGatos);
        rt1.setRespuesta("Si");

        rt2 = new RespuestaSobrePregunta();
        rt2.setPregunta(preguntaTienePatio);
        rt2.setRespuesta("No");

        respuestas.add(rt1);
        respuestas.add(rt2);

        //Cuestionario Preferencias
        cuestionarioPref = new Cuestionario("Preferencias");
        preg1 = new Pregunta();
        preg2 = new Pregunta();
        preg1.setPregunta("Tamaño");
        preg2.setPregunta("Edad");

        r1 = new RespuestaSobrePregunta();
        r2 = new RespuestaSobrePregunta();
        r1.setRespuesta("Grande");
        r2.setRespuesta("Cachorro");

        preguntasCuestionarioPref = new ArrayList<>();
        preguntasCuestionarioPref.add(preg1);
        preguntasCuestionarioPref.add(preg2);

        respuestasCuestionarioPref = new ArrayList<>();
        respuestasCuestionarioPref.add(rt1);
        respuestasCuestionarioPref.add(rt2);

        //Cuestionario Comodidades
        cuestionarioCom = new Cuestionario("Comodidades");

        preg3 = new Pregunta();
        preg4 = new Pregunta();
        preg3.setPregunta("¿Tiene Patio?");
        preg4.setPregunta("¿Tiene un canil cerca?");

        r3 = new RespuestaSobrePregunta();
        r4 = new RespuestaSobrePregunta();
        r3.setRespuesta("Si");
        r4.setRespuesta("No");

        preguntasCuestionarioCom = new ArrayList<>();
        preguntasCuestionarioCom.add(preg3);
        preguntasCuestionarioCom.add(preg4);

        respuestasCuestionarioCom = new ArrayList<>();
        respuestasCuestionarioCom.add(r3);
        respuestasCuestionarioCom.add(r4);

    }
    @Test
    public void personaGeneraPublicacionParaAdoptar(){

        persona.intencionDeAdoptar(cuestionarioPref,cuestionarioCom,ubicacion);
        gestor = GestorDePublicaciones.getInstancia();

        Assert.assertEquals("Intencion de adoptar una mascota",gestor.getPublicaciones().get(0).getTipoPublicacion());
        Assert.assertEquals(persona,gestor.getPublicaciones().get(0).getAdoptante());

    }
}
