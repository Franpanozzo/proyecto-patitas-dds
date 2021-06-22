package FormasDeEncuentro;

import Asociacion.*;
import Mascota.MascotaPerdida;
import Publicaciones.PublicacionMascotaPerdida;
import Repositorios.RepositorioAsociaciones;
import Usuario.DatoDeContacto;

import java.util.List;

public class SinChapita implements FormaDeEncuentro {

  @Override
  public void ejecutarAccion(MascotaPerdida mascotaPerdida, List<DatoDeContacto> datoDeContactos) {
    Asociacion asociacionMasCercana = RepositorioAsociaciones.getInstance().masCercanaA(mascotaPerdida);
    asociacionMasCercana.registrarPublicacion(new PublicacionMascotaPerdida(mascotaPerdida.getDatosMascotaPerdida(), datoDeContactos));
  }
}




