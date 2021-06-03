package FormasDeEncuentro;

import Asociacion.*;
import Mascota.MascotaPerdida;
import Repositorios.RepositorioAsociaciones;
import Usuario.DatoDeContacto;

import java.util.List;

public class SinChapita implements FormaDeEncuentro {

  @Override
  public void ejecutarAccion(MascotaPerdida mascotaPerdida, List<DatoDeContacto> datoDeContactos) {
    Asociacion asociacionMasCercana = RepositorioAsociaciones.getInstance().masCercanaA(mascotaPerdida);
    asociacionMasCercana.registrarPublicacion(new Publicacion(mascotaPerdida.getDatosMascotaPerdida(), datoDeContactos));
  }
}




