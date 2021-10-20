package domain.Publicaciones;

import domain.ClasesPersistencia.EntidadPersistente;
import domain.Usuario.DatoDeContacto;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class PublicacionIntencionAdopcion extends EntidadPersistente {

  @ElementCollection
  @MapKeyColumn(name = "intencion_requisito")
  @Column(name = "requisito")
  private Map<String, String> dataPublicacion = new HashMap<>();

  @ManyToOne(cascade = CascadeType.ALL)
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
