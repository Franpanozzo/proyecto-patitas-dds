package Publicaciones;

import Usuario.DatoDeContacto;
import Utils.Comodidades;
import Utils.DatosDeMascotaEnAdopcion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PublicacionIntencionAdopcion{
  private Map<String, String> dataPublicacion = new HashMap<>();
  private DatoDeContacto datoDeContactoInteresado;

  public DatoDeContacto getDatoDeContactoInteresado() {
    return datoDeContactoInteresado;
  }

  public PublicacionIntencionAdopcion(Map<String, String> dataPublicacion, DatoDeContacto datoDeContactoInteresado) {
    this.dataPublicacion = dataPublicacion;
    this.datoDeContactoInteresado = datoDeContactoInteresado;
  }

  public Map<String, String> getDataPublicacion() {
    return dataPublicacion;
  }



 /*
  public boolean tieneComodidades(List<Comodidades> comodidadesPregunta) {
    return true;
  }
 */
}
