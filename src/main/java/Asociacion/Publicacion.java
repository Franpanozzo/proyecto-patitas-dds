package Asociacion;

public class Publicacion {

  boolean validada = false;

  public Publicacion() {
  }

  public void validar() {
    validada = true;
  }

  public boolean validada() {
    return validada;
  }

  public boolean encontradaDespuesDe(LocalDate fechaMin) {
    return datosMascotaPerdida.encontradaDespuesDe(fechaMin);
  }
}
