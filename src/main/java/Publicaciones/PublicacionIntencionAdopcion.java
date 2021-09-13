package Publicaciones;

import ClasesPersistencia.EntidadPersistente;
import Usuario.DatoDeContacto;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class PublicacionIntencionAdopcion extends EntidadPersistente {

  //@ElementCollection
  @Transient
  private Map<String, String> dataPublicacion = new HashMap<>();

  @ManyToOne
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

  public PublicacionIntencionAdopcion() {

  }

 /*
  public boolean tieneComodidades(List<Comodidades> comodidadesPregunta) {
    return true;
  }
 */
}
