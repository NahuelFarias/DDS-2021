package domain.models.repositories.testMemoData;

import domain.controllers.CaracteristicasController;
import domain.models.entities.Persistente;
import domain.models.entities.mascotas.CaracteristicaConRta;
import domain.models.entities.mascotas.Foto;
import domain.models.entities.mascotas.Mascota;
import domain.models.entities.personas.Persona;
import domain.models.entities.publicaciones.Pregunta;
import services.EditorDeFotos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataMascota {
    private static List<Mascota> mascotas = new ArrayList<>();

    public static List<Persistente> getList() {
        if(mascotas.size() == 0) {
            Persona duenio = new Persona();
            CaracteristicasController controller;

            // TODO SACARLO DE ACA
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
            // TODO SACARLO DE ACA

            Mascota.MascotaDTO mascotaDTO = new Mascota.MascotaDTO();
            List<Pregunta> caracteristicas = controller.getRepositorio().caracteristicas;

            CaracteristicaConRta caracteristicaConRta1 = new CaracteristicaConRta(caracteristicas.get(0).getPregunta(),"Si");
            CaracteristicaConRta caracteristicaConRta2 = new CaracteristicaConRta(caracteristicas.get(1).getPregunta(),"Negro");

            ArrayList<CaracteristicaConRta> caracteristicasConRtas = new ArrayList<>();
            caracteristicasConRtas.add(caracteristicaConRta1);
            caracteristicasConRtas.add(caracteristicaConRta2);


            EditorDeFotos editor = new EditorDeFotos();

            List<Foto> fotos = new ArrayList<>();
            Foto foto = new Foto();
            foto.setURLfoto("img/perro1.jpg");
            fotos.add(foto);
            fotos= editor.redimensionarFotos(fotos);

            List<Foto> fotos2 = new ArrayList<>();
            Foto foto2 = new Foto();
            foto2.setURLfoto("img/perro3.jpg");
            fotos2.add(foto2);
            fotos2= editor.redimensionarFotos(fotos2);

            Mascota susana = new Mascota(duenio);
            susana.setNombre("Susana");
            susana.setFotos(fotos);
            susana.setDescripcion("Holaalalalalalaalalala");

            Mascota rafa = new Mascota(duenio);
            rafa.setNombre("Rafa");
            rafa.setFotos(fotos2);
            rafa.setDescripcion("Chauauauauauauauua");

            //mascotaDTO.inicializar(duenio,"Susana","Susi",2,"tiene una mancha blanca en una pata.",
            //        "gato", "hembra", caracteristicasConRtas, fotos);

            addAll(susana, rafa);
        }
        return (List<Persistente>)(List<?>) mascotas;
    }

    protected static void addAll(Mascota... mascotas){
        Collections.addAll(DataMascota.mascotas, mascotas);
    }

}
