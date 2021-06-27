package Publicaciones;

import Usuario.DatoDeContacto;
import Utils.Comodidades;
import Utils.Preferencia;

import java.util.ArrayList;
import java.util.List;

public class PublicacionIntencionAdopcion{
  private DatoDeContacto datoDeContactoInteresado;
  private Preferencia preferencia;

  public DatoDeContacto getDatoDeContactoInteresado() {
    return datoDeContactoInteresado;
  }

  public Preferencia getPreferencia() {
    return preferencia;
  }

  public PublicacionIntencionAdopcion(DatoDeContacto datoDeContactoInteresado, Preferencia preferencia) {
    this.datoDeContactoInteresado = datoDeContactoInteresado;
    this.preferencia = preferencia;
  }

  public boolean tieneComodidades(List<Comodidades> comodidadesPregunta) {
    return true;
  }
}
