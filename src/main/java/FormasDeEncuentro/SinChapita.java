package FormasDeEncuentro;

import Asociacion.*;
import Mascota.MascotaPerdida;

import java.util.Comparator;
import java.util.List;

public class SinChapita implements FormaDeEncuentro{


  @Override
  public void ejecutarAccion(List<Asociacion> asociacionesPosibles, MascotaPerdida mascotaPerdida) {

    Asociacion asociacionMasCercana = asociacionesPosibles.stream().
        min(Comparator.comparing(asociacion -> asociacion.distanciaALugarDeEncuentro(mascotaPerdida))).get();

    asociacionMasCercana.registrarPublicacion(new Publicacion(/*Datos personales segun ley*/));
  }
}
