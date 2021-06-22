package Publicaciones;

import Usuario.DatoDeContacto;
import Utils.Preferencia;

import java.util.ArrayList;
import java.util.List;

public class PublicacionIntencionAdopcion{
  private DatoDeContacto datoDeContactoInteresado;
  private Preferencia preferencia;

  public PublicacionIntencionAdopcion(DatoDeContacto datoDeContactoInteresado, Preferencia preferencia) {
    this.datoDeContactoInteresado = datoDeContactoInteresado;
    this.preferencia = preferencia;
  }
}
