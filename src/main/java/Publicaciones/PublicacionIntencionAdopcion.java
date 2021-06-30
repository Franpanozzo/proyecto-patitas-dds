package Publicaciones;

import Usuario.DatoDeContacto;
import Utils.Comodidades;
import Utils.DatosDeMascotaEnAdopcion;

import java.util.List;

public class PublicacionIntencionAdopcion{
  private DatoDeContacto datoDeContactoInteresado;

  public DatoDeContacto getDatoDeContactoInteresado() {
    return datoDeContactoInteresado;
  }

  public PublicacionIntencionAdopcion(Map<String, String> dataPublicacion, DatoDeContacto datoDeContactoInteresado) {
    this.dataPublicacion = dataPublicacion;
    this.datoDeContactoInteresado = datoDeContactoInteresado;
  }

  public boolean tieneComodidades(List<Comodidades> comodidadesPregunta) {
    return true;
  }
}
