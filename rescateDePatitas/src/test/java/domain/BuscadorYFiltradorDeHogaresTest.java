package domain;

import domain.models.entities.BuscadorDeHogaresDeTransito;
import domain.models.entities.hogares.ListadoDeHogares;
import org.junit.Assert;
import org.junit.Test;

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
    }
}
