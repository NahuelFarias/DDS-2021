package domain.services;

import services.BuscadorDeHogaresDeTransito;
import services.FiltradorDeHogaresDeTransito;
import domain.models.entities.hogares.ListadoDeHogares;
import domain.models.entities.hogares.Hogar;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BuscadorYFiltradorDeHogaresTest {
    @Test
    public void buscaHogaresDeTransitoDeLaAPI() {
        BuscadorDeHogaresDeTransito buscadorDeHogaresDeTransito = BuscadorDeHogaresDeTransito.getInstancia();
        ListadoDeHogares listadoDeHogares = null;
        try {
            listadoDeHogares = new ListadoDeHogares();
            listadoDeHogares = buscadorDeHogaresDeTransito.listadoDeHogares(1);
        } catch (Exception e) {e.printStackTrace();}
        Assert.assertNotNull(listadoDeHogares);
        List<Hogar> listaHogares = new FiltradorDeHogaresDeTransito().filtrarPorAnimalAceptado(listadoDeHogares.hogares, "perro");
        int i = 0;
        for(Hogar hogar: listaHogares){
            System.out.println(i + ") " + hogar.nombre);
            System.out.println(hogar.telefono);
            i++;
        }


    }
}
