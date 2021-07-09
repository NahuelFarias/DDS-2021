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
import domain.models.entities.rol.Rescatista;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EncontreMiMascota {

        Persona personaDuenio;
        Persona personaRescatista;
        Duenio duenio;
        Rescatista rescatista;
        Mascota mascota;

        CaracteristicasController controller;
        CaracteristicaConRta caracteristicaConRta1;
        CaracteristicaConRta caracteristicaConRta2;
        ArrayList<CaracteristicaConRta> caracteristicasConRtas;
        List<Caracteristica> caracteristicas;
        ArrayList<String> rtas, rtas2;

        Contacto contacto1,contacto2;
        List<Contacto> contactos, contactosRescatista;
        Foto foto;
        List<Foto> fotos;
        List<Mascota> mascotas;

        @Before
        public void instanciar() throws IOException {
            //duenio
            personaDuenio = new Persona();

            contacto1 = new Contacto("Maria", "Morales", "+541122222222", "m.a.morales@gmail.com", Estrategia.EMAIL);

            contactos = new ArrayList<>();
            contactos.add(contacto1);

            personaDuenio.inicializar("Maria", "Morales", "Peru 1212,CABA", TipoDeDocumento.DNI, 3333333, 27081996, contactos);

            duenio = new Duenio();

            personaDuenio.setRol(duenio);

            //rescatista

            personaRescatista = new Persona();
            rescatista = new Rescatista();
            personaRescatista.setRol(rescatista);
            personaRescatista.inicializar("Aracely", "Amaro", "Brasil 1112,CABA", TipoDeDocumento.DNI, 33311111, 30031998, contactosRescatista);

            contactosRescatista = new ArrayList<>();
            contacto2 = new Contacto("Aracely", "Amaro", "+541168648864", "ara6amaro@gmail.com", Estrategia.EMAIL);
            contactosRescatista.add(contacto2);

            mascotas = new ArrayList<>();

            //Termino de cargar caracteristicas al repositorio
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

            //mascota
            mascota = new Mascota(personaRescatista);
            mascota.inicializar("Susana","Susi",2,"tiene una mancha blanca en una pata.",
                    "gato", "hembra", caracteristicasConRtas, fotos);
            //aniado la mascota en la lista del rescatista
            rescatista.agregarMascota(mascota);

        }
        @Test
        public void duenioEncuentraASuMascotaPerdida(){
            Mascota mascotaperdida = personaRescatista.getRol().getMascotas().get(0);
            personaDuenio.encontreMiMascotaPerdida( mascotaperdida,personaDuenio.getContactos().get(0));
        }
}