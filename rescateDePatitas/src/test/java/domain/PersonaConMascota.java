package domain;

import com.google.zxing.WriterException;
import domain.models.entities.mascotas.Mascota;
import domain.models.entities.personas.Persona;
import domain.models.entities.personas.TipoDeDocumento;
import domain.models.entities.rol.Duenio;
import domain.models.entities.rol.Rol;
import org.junit.Test;

import java.io.IOException;


public class PersonaConMascota {

    @Test
    public void crearPersonaTest() throws IOException, WriterException {
        //String nombre, String apellido, String direccion,TipoDeDocumento tipoDoc,
        //                            Integer nroDoc,Integer fechaDeNacimiento
        Persona persona = new Persona();


        persona.inicializar("Maria Victoria","Sanchez","Peru 1212,CABA",TipoDeDocumento.DNI,3333333,27081996);


        System.out.println ("Datos del Dueño");

        System.out.println ("Nombre:" + persona.getNombre());
        System.out.println ("Apellido:" + persona.getApellido());
        System.out.println ("Direccion:" + persona.getDireccion());
        System.out.println ("Tipo de documento:" + persona.getTipoDoc());
        System.out.println ("Numero de documento:" + persona.getNroDoc());
        System.out.println ("Fecha de nacimiento:" + persona.getFechaDeNacimiento());
        System.out.println ("-------------");

        Rol duenio = new Duenio();

        persona.setRol(duenio);

        persona.registrarMascota("Susana","Susi",2,"tiene una mancha blanca en una pata.",
                "gato", "hembra",persona);

        System.out.println ("Datos de la Mascota");
        Mascota mascota = persona.getRol().getMascotas().get(0);

        System.out.println ("ID Mascota:" + mascota.getIdMascota());
        System.out.println ("Nombre:" + mascota.getNombre());
        System.out.println ("Apodo:" + mascota.getApodo());
        System.out.println ("Edad:" + mascota.getEdad());
        System.out.println ("Descripcion:" + mascota.getDescripcion());
        System.out.println ("Especie:" + mascota.getEspecie());
        System.out.println ("Genero:" + mascota.getGenero());
        System.out.println ("-------------");

        mascota.generarQR();

        persona.registrarMascota("Marta","Susi",2,"tiene una mancha blanca en una pata.",
                "gato", "hembra",persona);

        System.out.println ("Datos de la Mascota");
        Mascota mascota2 = persona.getRol().getMascotas().get(1);

        System.out.println ("ID Mascota:" + mascota2.getIdMascota());
        System.out.println ("Nombre:" + mascota2.getNombre());
        System.out.println ("Apodo:" + mascota2.getApodo());
        System.out.println ("Edad:" + mascota2.getEdad());
        System.out.println ("Descripcion:" + mascota2.getDescripcion());
        System.out.println ("Especie:" + mascota2.getEspecie());
        System.out.println ("Genero:" + mascota2.getGenero());
        System.out.println ("-------------");

        mascota2.generarQR();


    }
}
