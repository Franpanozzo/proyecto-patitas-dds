package domain.FormasDeEncuentro;

import domain.Asociacion.*;
import domain.Mascota.MascotaPerdida;
import domain.Publicaciones.PublicacionMascotaPerdida;
import domain.Repositorios.RepositorioAsociaciones;
import domain.Usuario.DatoDeContacto;

import java.util.List;

public class SinChapita implements FormaDeEncuentro {

  @Override
  public void ejecutarAccion(MascotaPerdida mascotaPerdida, List<DatoDeContacto> datoDeContactos) {
    Asociacion asociacionMasCercana = RepositorioAsociaciones.getInstance().masCercanaA(mascotaPerdida);
    asociacionMasCercana.registrarPublicacion(new PublicacionMascotaPerdida(mascotaPerdida.getDatosMascotaPerdida(), datoDeContactos));
  }
}




