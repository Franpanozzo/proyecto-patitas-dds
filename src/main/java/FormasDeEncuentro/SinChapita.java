package FormasDeEncuentro;

import Asociacion.*;
import Mascota.MascotaPerdida;
import Repositorios.RepositorioAsociaciones;

public class SinChapita implements FormaDeEncuentro {

  @Override
  public void ejecutarAccion(MascotaPerdida mascotaPerdida) {
    Asociacion asociacionMasCercana = RepositorioAsociaciones.getInstance().masCercanaA(mascotaPerdida);
    asociacionMasCercana.registrarPublicacion(new Publicacion(/*Datos personales segun ley*/));
  }
}




