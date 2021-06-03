package FormasDeEncuentro;

import Asociacion.*;

import Mascota.Chapita;
import Mascota.MascotaPerdida;
import Usuario.DatoDeContacto;

import java.util.List;
import java.util.Optional;

public class ConChapita implements FormaDeEncuentro {

  @Override
  public void ejecutarAccion(MascotaPerdida mascotaPerdida, List<DatoDeContacto> datoDeContactos) {
    mascotaPerdida.buscarDuenioCorrespondiente();
  }

}
