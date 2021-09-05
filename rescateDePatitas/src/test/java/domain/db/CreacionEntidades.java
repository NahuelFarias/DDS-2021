package domain.db;

import db.EntityManagerHelper;
import domain.models.entities.notificaciones.estrategias.Estrategia;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;
import domain.models.entities.personas.TipoDeDocumento;
import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreacionEntidades {
    Persona persona;
    Contacto contacto;
    List<Contacto> contactos;

    @Test
    public void persistirUsuarioTest1(){
        persona = new Persona();
        persona.setNombre("Eze");
        persona.setApellido("Escobar");
        persona.setFechaDeNacimiento(LocalDate.of(1987, 9, 24));
        persona.setTipoDoc(TipoDeDocumento.DNI);
        persona.setNroDoc(44444444);
        persona.setDireccion("Lalalala");
        contacto = new Contacto("Soledad", "Grilleta", "+541157530658", "sole.012@gmail.com", Estrategia.EMAIL);
        contactos = new ArrayList<>();
        contactos.add(contacto);
        persona.setContactos(contactos);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(persona);
        EntityManagerHelper.commit();
    }

    @Test
    public void persistirUsuarioTest2(){
        persona = new Persona();
        contacto = new Contacto("Soledad", "Grilleta", "+541157530658", "sole.012@gmail.com", Estrategia.EMAIL);
        contacto.setPersona(persona);
        contactos = new ArrayList<>();
        contactos.add(contacto);
        persona.inicializar("Sole", "Grilletta", "Peru 1212,CABA", TipoDeDocumento.DNI, 3333333, LocalDate.of(1987, 9, 24),contactos);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(persona);
        EntityManagerHelper.commit();
    }

    @Test
    public void recuperandoAEze(){
        Persona eze = (Persona) EntityManagerHelper.createQuery("from Persona where nombre = 'Eze'").getSingleResult();
        Assert.assertEquals("Eze", eze.getNombre());
    }

    // TODO revisar el tema del delete

    /*    @Test
    public void eliminandooAEze(){
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.createQuery("Delete from 'Persona' where nombre = 'Eze'");
        EntityManagerHelper.commit();
    }*/

}
