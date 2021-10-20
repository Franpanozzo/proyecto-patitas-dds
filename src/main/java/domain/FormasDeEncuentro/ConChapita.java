package domain.FormasDeEncuentro;

import domain.Mascota.MascotaPerdida;
import domain.Usuario.DatoDeContacto;

import java.util.List;

public class ConChapita implements FormaDeEncuentro {

  @Override
  public void ejecutarAccion(MascotaPerdida mascotaPerdida, List<DatoDeContacto> datoDeContactos) {
    mascotaPerdida.buscarDuenioCorrespondiente();
  }

}
