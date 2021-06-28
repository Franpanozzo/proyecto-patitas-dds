package Publicaciones;

import Usuario.DatoDeContacto;
import Utils.Comodidades;
import Utils.DatosDeMascotaEnAdopcion;

import java.util.List;

public class PublicacionIntencionAdopcion{
  private DatoDeContacto datoDeContactoInteresado;
  private DatosDeMascotaEnAdopcion preferenciasDeAdopcion;

  public DatoDeContacto getDatoDeContactoInteresado() {
    return datoDeContactoInteresado;
  }

  public DatosDeMascotaEnAdopcion getPreferencia() {
    return preferenciasDeAdopcion;
  }

  public PublicacionIntencionAdopcion(DatoDeContacto datoDeContactoInteresado, DatosDeMascotaEnAdopcion datosDeMascotaEnAdopcion) {
    this.datoDeContactoInteresado = datoDeContactoInteresado;
    this.preferenciasDeAdopcion = datosDeMascotaEnAdopcion;
  }

  public boolean tieneComodidades(List<Comodidades> comodidadesPregunta) {
    return true;
  }
}
