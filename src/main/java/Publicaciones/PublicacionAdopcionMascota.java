package Publicaciones;
import Utils.*;
import Usuario.DatoDeContacto;

import java.util.List;

public class PublicacionAdopcionMascota {
  private List<Pregunta> dataPublicacion;
  DatoDeContacto datoDeContacto;

  public PublicacionAdopcionMascota(List<Pregunta> dataPublicacion, DatoDeContacto datoDeContacto) {
    this.dataPublicacion = dataPublicacion;
    this.datoDeContacto = datoDeContacto;
  }

  public DatoDeContacto getDatoDeContacto(){
    return datoDeContacto;
  }

}
