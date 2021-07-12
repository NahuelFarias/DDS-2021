package domain.duenio;

import domain.models.entities.mascotas.DatosMascotaPerdida;
import domain.models.entities.mascotas.Foto;
import domain.models.entities.mascotas.Lugar;
import domain.models.entities.mascotas.Mascota;
import domain.models.entities.notificaciones.estrategias.Estrategia;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;
import domain.models.entities.personas.TipoDeDocumento;
import domain.models.entities.publicaciones.GestorDePublicaciones;
import domain.models.entities.publicaciones.PublicacionGenerica;
import domain.models.entities.publicaciones.PublicacionPerdidaNoRegistrada;
import domain.models.entities.rol.Duenio;
import domain.models.entities.rol.Rescatista;
import org.junit.Before;
import org.junit.Test;
import services.EditorDeFotos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EncontreMiMascotaEnPublicacion {


    Persona personaDuenio;
    Persona personaRescatista;
    Duenio duenio;
    Rescatista rescatista;
    Mascota mascota;

    Contacto contactoR1,contactoR2,contactoD1;
    List<Contacto> contactos, contactosRescatista;
    Foto foto;
    List<Foto> fotos;
    EditorDeFotos editor;

    PublicacionGenerica publicacion;
    DatosMascotaPerdida datosMascota;
    Lugar lugar;
    GestorDePublicaciones gestor;

    @Before
    public void instanciar() throws IOException {

        personaDuenio = new Persona();
        //Llena los contactos en el formulario
        contactoD1 = new Contacto("Aracely", "Amaro", "+541168648864", "ara6amaro@gmail.com", Estrategia.EMAIL);
        List<Contacto> contactosDuenio = new ArrayList<>();
        contactosDuenio.add(contactoD1);
        personaDuenio.setContactos(contactos);


        //rescatista
        contactosRescatista = new ArrayList<>();
        contactoR1 = new Contacto("Sole", "Grilletta", "+541157530658", "sole.012@gmail.com", Estrategia.EMAIL);
        contactoR2 = new Contacto("Sole", "Grilletta", "+541157530658", "sole.012@gmail.com", Estrategia.WHATSAPP);
        contactosRescatista.add(contactoR1);
        contactosRescatista.add(contactoR2);
        personaRescatista = new Persona();
        rescatista = new Rescatista();
        personaRescatista.setRol(rescatista);
        personaRescatista.inicializar("Sole", "Grilletta", "Urquiza 3835,CABA", TipoDeDocumento.DNI, 33333333, 27081998, contactosRescatista);



        fotos = new ArrayList<>();
        foto = new Foto();
        foto.setURLfoto("src/main/resources/FotoDePrueba2.jpg");
        fotos.add(foto);
        editor = new EditorDeFotos();
        fotos= editor.redimensionarFotos(fotos);
        lugar = new Lugar(-34.6692966,-58.4766928);
        datosMascota = new DatosMascotaPerdida(fotos,"La encontre en buen estado.",lugar);

        rescatista.encontreUnaMascotaPerdidaSinChapita(personaRescatista,datosMascota);

    }
    @Test
    public void duenioEncuentraASuMascotaPerdida(){
        gestor = GestorDePublicaciones.getInstancia();
        publicacion = gestor.getPublicaciones().get(0);
        personaDuenio.encontreMiMascotaPerdida((PublicacionPerdidaNoRegistrada) publicacion,contactoD1);
    }

}
