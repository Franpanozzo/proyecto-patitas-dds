package FormasDeEncuentro;

import Asociacion.*;

import Mascota.Chapita;
import Mascota.MascotaPerdida;

import java.util.List;
import java.util.Optional;

public class ConChapita implements FormaDeEncuentro {
  Chapita chapita;

  public ConChapita(Chapita chapita) {
    this.chapita = chapita;
  }

  @Override
  public void ejecutarAccion(MascotaPerdida mascotaPerdida) {
    mascotaPerdida.buscarDuenioCorrespondiente();
  }
}
