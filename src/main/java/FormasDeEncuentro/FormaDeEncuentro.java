package FormasDeEncuentro;

import Asociacion.Asociacion;
import Mascota.MascotaPerdida;
import Usuario.DatoDeContacto;

import java.util.List;

public interface FormaDeEncuentro {
  void ejecutarAccion(MascotaPerdida mascotaPerdida, List<DatoDeContacto> datoDeContactos);
}
