package domain.db;

import db.EntityManagerHelper;
import domain.controllers.CaracteristicasController;
import domain.models.entities.mascotas.*;
import domain.models.entities.notificaciones.estrategias.Estrategia;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;
import domain.models.entities.personas.TipoDeDocumento;
import domain.models.entities.personas.Usuario;
import domain.models.entities.publicaciones.*;
import domain.models.entities.rol.Duenio;
import domain.models.entities.rol.Rescatista;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreacionEntidades {
    Persona persona,persona2;
    Contacto contacto, contacto2;
    List<Contacto> contactos,contactos2;
    CaracteristicasController controller;
    CaracteristicaConRta caracteristicaConRta1, caracteristicaConRta2, caracteristicaConRta3, caracteristicaConRta4;
    ArrayList<CaracteristicaConRta> caracteristicasConRtas, caracteristicasConRtas2;
    Organizacion org1,org2,org3,org4,org5,orgMasCercana;
    Lugar puntoEncuentro;
    List<Organizacion> organizaciones;
    GestorDePublicaciones gestor;
    Organizacion organizacion;
    RespuestaConcreta rt1, rt2,rt3,rt4;
    List<RespuestaConcreta> respuestasOrganizacion;
    CuestionarioContestado cuestionarioContestadoDeAdopcion;
    List<Pregunta> preguntasCuestionario = new ArrayList<>();
    List<RespuestaConcreta> respuestasGenerales;
    Pregunta preguntaTieneGatos;
    Pregunta preguntaTienePatio;
    Pregunta preg1;
    Pregunta preg2;
    CuestionarioContestado cuestionarioContestadoPyC;
    List<RespuestaConcreta> respuestasCuestionarioPreferenciasYComodidades;
    List<Pregunta> preguntasCuestionarioPreferenciasYComodidades;
    RespuestaConcreta r1,r2,r3,r4;
    Pregunta preg3,preg4;
    List<RespuestaConcreta> respuestas;



    @Before
    public void setup(){
        persona = new Persona();
        persona.setNombre("Maria");
        persona.setApellido("Gonzalez");
        persona.setFechaDeNacimiento(LocalDate.of(1956, 9, 24));
        persona.setTipoDoc(TipoDeDocumento.DNI);
        persona.setNroDoc(9444444);
        persona.setDireccion("Av Mitre 234");
        contacto = new Contacto("Soledad", "Grilleta", "+541157530658", "sole.012@gmail.com", Estrategia.EMAIL);
        contactos = new ArrayList<>();
        contactos.add(contacto);
        persona.setContactos(contactos);

        persona2 = new Persona();
        persona2.setNombre("Julio");
        persona2.setApellido("Miguel");
        persona2.setFechaDeNacimiento(LocalDate.of(1987, 3, 11));
        persona2.setTipoDoc(TipoDeDocumento.DNI);
        persona2.setNroDoc(34444444);
        persona2.setDireccion("Las heras 2461");
        contacto2 = new Contacto("Julio", "Miguel", "+541157530658", "sole.012@gmail.com", Estrategia.EMAIL);
        contactos2 = new ArrayList<>();
        contactos2.add(contacto);
        persona2.setContactos(contactos);

        persona = new Persona();
        contacto = new Contacto("Soledad", "Grilleta", "+541157530658", "sole.012@gmail.com", Estrategia.EMAIL);
        contacto.setPersona(persona);
        contactos = new ArrayList<>();
        contactos.add(contacto);
        persona.inicializar("Sole", "Grilletta", "Peru 1212,CABA", TipoDeDocumento.DNI, 3333333, LocalDate.of(1987, 9, 24),contactos);

        organizaciones = new ArrayList<>();

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

        org5 = new Organizacion();
        org5.setNombre("El refugio"); //Lugano
        org5.setUbicacion(new Lugar(-34.6766714,-58.4790033));
        organizaciones.add(org5);

        puntoEncuentro = new Lugar(-34.6621347,-58.4803575); //Campus

        gestor = GestorDePublicaciones.getInstancia();
        gestor.setOrganizaciones(organizaciones);

        Usuario usuario = new Usuario("julio_miguel","kjasdA1@hkasdh");
        persona.setUsuario(usuario);
        persona.addRol(new Duenio());
        persona.setRolElegido(new Duenio());

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

        List<Pregunta> caracteristicas = controller.getRepositorio().caracteristicas;

        caracteristicaConRta1 = new CaracteristicaConRta(caracteristicas.get(0).getPregunta(),"Si");
        caracteristicaConRta2 = new CaracteristicaConRta(caracteristicas.get(1).getPregunta(),"Negro");

        //Armo la lista de caracteristicas para agregar a la mascota
        caracteristicasConRtas = new ArrayList<>();
        caracteristicasConRtas.add(caracteristicaConRta1);
        caracteristicasConRtas.add(caracteristicaConRta2);

        //Redimensiono las fotos para agregar a la mascota
        List<Foto> fotos = new ArrayList<>();
        Foto foto = new Foto();
        foto.setURLfoto("src/main/resources/FotoDePrueba2.jpg");
        fotos.add(foto);

        Mascota.MascotaDTO mascotaDTO = new Mascota.MascotaDTO();
        mascotaDTO.inicializar(persona,"Susana","Susi",2,"tiene una mancha blanca en una pata.",
                "gato", "hembra", caracteristicasConRtas, fotos);
        persona.registrarMascota(mascotaDTO);


        preguntaTieneGatos= new Pregunta();
        preguntaTieneGatos.setPregunta("Tiene gatos?");
        preguntaTienePatio = new Pregunta();
        preguntaTienePatio.setPregunta("Tiene patio en su casa?");

        respuestas = new ArrayList<>();
        rt1 = new RespuestaConcreta();
        rt1.setPregunta(preguntaTieneGatos);
        rt1.setRespuesta("Si");

        rt2 = new RespuestaConcreta();
        rt2.setPregunta(preguntaTienePatio);
        rt2.setRespuesta("No");

        respuestas.add(rt1);
        respuestas.add(rt2);

        //Cuestionario PreferenciasYComodidades
        cuestionarioContestadoPyC = new CuestionarioContestado();
        preg1 = new Pregunta();
        preg2 = new Pregunta();
        preg1.setPregunta("Tamaño");
        preg2.setPregunta("Edad");

        r1 = new RespuestaConcreta();
        r2 = new RespuestaConcreta();
        r1.setPregunta(preg1);
        r1.setRespuesta("Grande");
        r2.setPregunta(preg2);
        r2.setRespuesta("Cachorro");

        preguntasCuestionarioPreferenciasYComodidades = new ArrayList<>();
        preguntasCuestionarioPreferenciasYComodidades.add(preg1);
        preguntasCuestionarioPreferenciasYComodidades.add(preg2);

        respuestasCuestionarioPreferenciasYComodidades = new ArrayList<>();
        respuestasCuestionarioPreferenciasYComodidades.add(rt1);
        respuestasCuestionarioPreferenciasYComodidades.add(rt2);

        preg3 = new Pregunta();
        preg4 = new Pregunta();
        preg3.setPregunta("¿Tiene Patio?");
        preg4.setPregunta("¿Tiene un canil cerca?");

        r3 = new RespuestaConcreta();
        r4 = new RespuestaConcreta();
        r3.setPregunta(preg3);
        r3.setRespuesta("Si");
        r4.setPregunta(preg4);
        r4.setRespuesta("No");

        preguntasCuestionarioPreferenciasYComodidades.add(preg3);
        preguntasCuestionarioPreferenciasYComodidades.add(preg4);

        respuestasCuestionarioPreferenciasYComodidades.add(r3);
        respuestasCuestionarioPreferenciasYComodidades.add(r4);

        cuestionarioContestadoPyC.setRespuestas(respuestasCuestionarioPreferenciasYComodidades);

    }
    @Test
    public void persistirUsuarioTest1(){

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(persona);
        EntityManagerHelper.getEntityManager().persist(persona2);
        EntityManagerHelper.commit();
    }

    @Test
    public void persistirUsuarioTest2(){

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(persona);
        EntityManagerHelper.commit();
    }

    @Test
    public void recuperandoAJulio(){
        Persona julio = (Persona) EntityManagerHelper.createQuery("from Persona where id = 2").getSingleResult();
        Assert.assertEquals("Julio", julio.getNombre());
    }

    @Test
    public void eliminandoAJulio(){
        /*
        Persona julio = (Persona) EntityManagerHelper.createQuery("from Persona where nombre = 'Julio' and id = 2").getSingleResult();

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().remove(julio);
        EntityManagerHelper.commit();
        */
    }

    @Test
    public void persistirUsuarioYMascotas(){

        Duenio duenio = (Duenio) persona.getRolElegido();


        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(duenio.getMascotas().get(0));
        EntityManagerHelper.getEntityManager().persist(persona);
        EntityManagerHelper.commit();

    }

    @Test
    public void persistirPublicaciones(){
        //Publicacion encontrada
        persona2.setRolElegido(new Rescatista());
        List<Foto> fotos = new ArrayList<>();
        Foto foto = new Foto();
        foto.setURLfoto("src/main/resources/FotoDePrueba2.jpg");
        fotos.add(foto);
        DatosMascotaEncontrada encontrada = new DatosMascotaEncontrada(fotos,"En buen estado",new Lugar(12345,12234));
        gestor = GestorDePublicaciones.getInstancia();
        gestor.generarPublicacionMascotaEncontrada(persona2, encontrada);

        //Publicacion En Adopcion
        Cuestionario cuest = new Cuestionario();
        organizacion = new Organizacion();

        respuestasGenerales = new ArrayList<>();
        rt1 = new RespuestaConcreta();
        rt1.setPregunta(preguntaTieneGatos);
        rt1.setRespuesta("Si");

        rt2 = new RespuestaConcreta();
        rt2.setPregunta(preguntaTienePatio);
        rt2.setRespuesta("No");

        respuestasGenerales.add(rt1);
        respuestasGenerales.add(rt2);

        respuestasOrganizacion = new ArrayList<>();
        rt1 = new RespuestaConcreta();
        preg1= new Pregunta();
        preg1.setPregunta("Raza");
        preg1.setTipoDePregunta("Caracteristica");
        preg1.setVisible(true);
        preg1.setOrganizacion(organizacion);
        preg1.setCuestionario(cuest);
        rt1.setPregunta(preg1);
        rt1.setRespuesta("Collie");

        rt2 = new RespuestaConcreta();
        preg2 = new Pregunta();
        preg2.setPregunta("Es amigable con niños?");
        preg2.setTipoDePregunta("Caracteristica");
        preg2.setVisible(true);
        preg2.setOrganizacion(organizacion);
        preg2.setCuestionario(cuest);
        rt2.setPregunta(preg2);
        rt2.setRespuesta("Si");

        respuestasOrganizacion.add(rt1);
        respuestasOrganizacion.add(rt2);
        preguntasCuestionario.add(preg1);
        preguntasCuestionario.add(preg2);
        cuest.setPreguntas(preguntasCuestionario);

        Duenio duenio = (Duenio) persona.getRolElegido();
        gestor = GestorDePublicaciones.getInstancia();
        gestor.generarPublicacionEnAdopcion(duenio.getMascotas().get(0),respuestasOrganizacion,respuestasGenerales,organizacion);

        //Publicacion Perdida Registrada
        gestor.generarPublicacionMascotaPerdida(duenio.getMascotas().get(0));

        //Publicacion Intencion de Adopcion

        gestor.generarPublicacionIntencionAdoptar(persona, cuestionarioContestadoPyC);
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(gestor.getPublicaciones().get(0));
        EntityManagerHelper.getEntityManager().persist(gestor.getPublicaciones().get(1));
        EntityManagerHelper.getEntityManager().persist(gestor.getPublicaciones().get(2));
        EntityManagerHelper.getEntityManager().persist(gestor.getPublicaciones().get(3));
        EntityManagerHelper.commit();


    }

}
