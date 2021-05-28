package FormasDeEncuentro;

import Asociacion.*;
import Mascota.MascotaPerdida;

import java.util.Comparator;
import java.util.List;

public class SinChapita implements FormaDeEncuentro {

  @Override
  public void ejecutarAccion(MascotaPerdida mascotaPerdida) {
    Asociacion asociacionMasCercana = RepositorioAsociaciones.getInstance().masCercanaA(mascotaPerdida);
    asociacionMasCercana.registrarPublicacion(new Publicacion(/*Datos personales segun ley*/));
  }
}





