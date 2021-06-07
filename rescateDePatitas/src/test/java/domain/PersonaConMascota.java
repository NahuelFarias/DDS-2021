package domain;

import com.google.zxing.WriterException;
import domain.controllers.CaracteristicasController;
import domain.models.entities.mascotas.Caracteristica;
import domain.models.entities.mascotas.CaracteristicaConRta;
import domain.models.entities.mascotas.Mascota;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;
import domain.models.entities.personas.TipoDeDocumento;
import domain.models.entities.rol.Duenio;
import domain.models.entities.rol.Rol;
import domain.models.repositories.RepositorioDeCaracteristicas;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PersonaConMascota {
    Persona persona;
    Rol duenio;
    RepositorioDeCaracteristicas repoCaracteristicas;
    CaracteristicasController controller;
    CaracteristicaConRta caracteristicaConRta1;
    CaracteristicaConRta caracteristicaConRta2;
    ArrayList<CaracteristicaConRta> caracteristicasConRtas;
    Mascota mascota,mascota2;


    @Before
    public void Instanciar() {
        persona = new Persona();
        duenio = new Duenio();
        repoCaracteristicas = new RepositorioDeCaracteristicas();
    }

    @Test
    public void crearPersonaConMascotasTest() throws IOException, WriterException {


        persona.inicializar("Maria Victoria","Sanchez","Peru 1212,CABA",TipoDeDocumento.DNI,3333333,27081996);

        System.out.println ("Datos del Dueño");
        System.out.println ("Nombre:" + persona.getNombre());
        System.out.println ("Apellido:" + persona.getApellido());
        System.out.println ("Direccion:" + persona.getDireccion());
        System.out.println ("Tipo de documento:" + persona.getTipoDoc());
        System.out.println ("Numero de documento:" + persona.getNroDoc());
        System.out.println ("Fecha de nacimiento:" + persona.getFechaDeNacimiento());
        System.out.println ("-------------");


        persona.setRol(duenio);

        //Empiezo a cargar caracteristicas al repositorio

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

        caracteristicaConRta1 = new CaracteristicaConRta(caracteristicas.get(0).getDescripcion(),"Si");
        caracteristicaConRta2 = new CaracteristicaConRta(caracteristicas.get(1).getDescripcion(),"Negro");

        caracteristicasConRtas = new ArrayList<CaracteristicaConRta>();
        caracteristicasConRtas.add(caracteristicaConRta1);
        caracteristicasConRtas.add(caracteristicaConRta2);


        persona.registrarMascota("Susana","Susi",2,"tiene una mancha blanca en una pata.",
                "gato", "hembra", caracteristicasConRtas,persona);

        //Listo los datos de la mascota 1 cargada
        System.out.println ("Datos de la Mascota");
        mascota = persona.getRol().getMascotas().get(0);

        System.out.println ("ID Mascota:" + mascota.getIdMascota());
        System.out.println ("Nombre:" + mascota.getNombre());
        System.out.println ("Apodo:" + mascota.getApodo());
        System.out.println ("Edad:" + mascota.getEdad());
        System.out.println ("Descripcion:" + mascota.getDescripcion());
        System.out.println ("Especie:" + mascota.getEspecie());
        System.out.println ("Genero:" + mascota.getGenero());
        String descripcion1 = mascota.getCaracteristicas().get(0).getDescripcion();
        String respuesta1 = mascota.getCaracteristicas().get(0).getRespuestaElegida();
        System.out.println (descripcion1 + ":"+respuesta1);
        String descripcion2 = mascota.getCaracteristicas().get(1).getDescripcion();
        String respuesta2 = mascota.getCaracteristicas().get(1).getRespuestaElegida();
        System.out.println (descripcion2 + ":"+respuesta2);
        System.out.println ("-------------");

        //Fin datos mascota 1

        mascota.generarQR();

        //Registro de mascota 2

        CaracteristicaConRta caracteristicaConRta3 = new CaracteristicaConRta(caracteristicas.get(0).getDescripcion(),"No");
        CaracteristicaConRta caracteristicaConRta4 = new CaracteristicaConRta(caracteristicas.get(1).getDescripcion(),"Marron");

        ArrayList<CaracteristicaConRta> caracteristicasConRtas2 = new ArrayList<CaracteristicaConRta>();
        caracteristicasConRtas2.add(caracteristicaConRta3);
        caracteristicasConRtas2.add(caracteristicaConRta4);

        persona.registrarMascota("Titan","Titi",5,"Tiene los ojos de distinto color.",
                "perro", "macho",caracteristicasConRtas2,persona);

        //Listo los datos de la mascota 2

        mascota2 = persona.getRol().getMascotas().get(1);
        System.out.println ("Datos de la Mascota");
        System.out.println ("ID Mascota:" + mascota2.getIdMascota());
        System.out.println ("Nombre:" + mascota2.getNombre());
        System.out.println ("Apodo:" + mascota2.getApodo());
        System.out.println ("Edad:" + mascota2.getEdad());
        System.out.println ("Descripcion:" + mascota2.getDescripcion());
        System.out.println ("Especie:" + mascota2.getEspecie());
        System.out.println ("Genero:" + mascota2.getGenero());
        descripcion1 = mascota2.getCaracteristicas().get(0).getDescripcion();
        respuesta1 = mascota2.getCaracteristicas().get(0).getRespuestaElegida();
        System.out.println (descripcion1 + ":"+respuesta1);
        descripcion2 = mascota2.getCaracteristicas().get(1).getDescripcion();
        respuesta2 = mascota2.getCaracteristicas().get(1).getRespuestaElegida();
        System.out.println (descripcion2 + ":"+respuesta2);

        System.out.println ("-------------");

        mascota2.generarQR();


    }
}
