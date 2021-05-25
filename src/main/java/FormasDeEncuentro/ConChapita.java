package FormasDeEncuentro;

import Asociacion.Asociacion;
import Mascota.MascotaPerdida;

import java.util.List;
import java.util.Optional;

public class ConChapita implements FormaDeEncuentro{
  @Override
  public void ejecutarAccion(List<Asociacion> asociacionesPosibles, MascotaPerdida mascotaPerdida) {
    Asociacion asociacion = asociacionesPosibles.get(0);
    asociacion.cargarMascota(mascotaPerdida);
    asociacion.buscarDuenio(mascotaPerdida);
  }
}
