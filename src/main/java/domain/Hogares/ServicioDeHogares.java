package domain.Hogares;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServicioDeHogares {
  @GET("hogares")
  Call<ListaDeHogares> hogares(@Query("offset") int offset);
}
