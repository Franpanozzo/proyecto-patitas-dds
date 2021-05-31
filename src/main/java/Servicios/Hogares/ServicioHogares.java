package Servicios.Hogares;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ServicioHogares {
  private static ServicioHogares instancia = null;
  private static final String urlAPI = "https://api.refugiosdds.com.ar/api/";
  private Retrofit retrofit;

  private ServicioHogares(){
    this.retrofit = new Retrofit.Builder()
        .baseUrl(urlAPI)
        .addConverterFactory(GsonConvertorFactory.create())
        .build();
  }

  public static ServicioHogares getInstance() {
    if(instancia == null) {
      instancia = new ServicioHogares();
    }
    return instancia;
  }

  public ListaDeHogares listadoDeHogares(int offset) throws IOException {
    ServicioDeHogares servicioDeHogares = this.retrofit.create(ServicioDeHogares.class);
    Call<ListaDeHogares> requestHogares = servicioDeHogares.hogares(offset);
    Response<ListaDeHogares> responseHogares = requestHogares.execute();
    return responseHogares.body();
  }

}

