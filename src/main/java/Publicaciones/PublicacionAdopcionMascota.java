package Publicaciones;
import Utils.*;
import Usuario.DatoDeContacto;

import java.util.List;
import java.util.stream.Collectors;

public class PublicacionAdopcionMascota {
  private List<Pregunta> dataPublicacion;
  DatoDeContacto datoDeContacto;
  String link;
  private DatosDeMascotaEnAdopcion datosDeMascotaEnAdopcion;


  public String getLink() {
    return link;
  }

  public PublicacionAdopcionMascota(List<Pregunta> dataPublicacion, DatoDeContacto datoDeContacto) {
    this.dataPublicacion = dataPublicacion;
    this.datoDeContacto = datoDeContacto;
  }

  public DatoDeContacto getDatoDeContacto(){
    return datoDeContacto;
  }

  public boolean cumpleRequisitos(PublicacionIntencionAdopcion publicacionIntencionAdopcion) {
    return publicacionIntencionAdopcion.tieneComodidades(this.comodidadesPregunta());
  }

  public List<Comodidades> comodidadesPregunta() {
    return dataPublicacion.stream().map(Pregunta::getComodidadesNecesarias).collect(Collectors.toList());
  }
}
