package Servicios.Hogares;

import retrofit2.http.GET;

public interface ServicioDeHogares {
  @GET("hogares")
  Call<ListaDeHogares> hogares(@Query("offset") int offset);
}
