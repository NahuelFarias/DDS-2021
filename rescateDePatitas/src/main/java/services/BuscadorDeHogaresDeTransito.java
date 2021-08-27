package services;


import domain.models.entities.hogares.ListadoDeHogares;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class BuscadorDeHogaresDeTransito {
    private static BuscadorDeHogaresDeTransito instancia = null;
    //TODO: Mover urlAPIHogares a un archivo config
    private final String urlAPIHogares = Configuracion.leerPropiedad("URL_API_HOGARES");;
    private Retrofit retrofit;

    private BuscadorDeHogaresDeTransito() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlAPIHogares)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static BuscadorDeHogaresDeTransito getInstancia() {
        if(instancia == null) {
            instancia = new BuscadorDeHogaresDeTransito();
        }
        return instancia;
    }

    public ListadoDeHogares listadoDeHogares(int offset) throws IOException {
        HomeSearcher homeSearcher = this.retrofit.create(HomeSearcher.class);
        Call<ListadoDeHogares> requestHogares = homeSearcher.hogares(offset);
        Response<ListadoDeHogares> responseHogares = requestHogares.execute();
        return responseHogares.body();
    }
}
