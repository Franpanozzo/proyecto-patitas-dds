package Asociacion;

import Mascota.DatosMascotaPerdida;

import java.time.LocalDate;

public class Publicacion {
  DatosMascotaPerdida datosMascotaPerdida;
  boolean validada = false;

  public Publicacion(DatosMascotaPerdida datosMascotaPerdida) {
    this.datosMascotaPerdida = datosMascotaPerdida;
  }

  public void validar() {
    validada = true;
  }

  DatosMascotaPerdida getDatosMascotaPerdida() {
    return datosMascotaPerdida;
  }

  public boolean validada() {
    return validada;
  }

  public boolean encontradaDespuesDe(LocalDate fechaMin) {
    return datosMascotaPerdida.encontradaDespuesDe(fechaMin);
  }
}
