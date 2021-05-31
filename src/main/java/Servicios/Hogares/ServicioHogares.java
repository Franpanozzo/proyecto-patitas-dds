package Servicios.Hogares;

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

  public ListaDeHogares listadoDeHogares(){
    ServicioDeHogares servicioDeHogares = this.retrofit.create(ServicioDeHogares.class);
    Call<ListaDeHogares> requestHogares = servicioDeHogares.hogares(1);
    Response<ListaDeHogares> responseHogares = requestHogares.execute();
    return responseHogares.body();
  }

}

