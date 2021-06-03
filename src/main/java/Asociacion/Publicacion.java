package Asociacion;

import Mascota.DatosMascotaPerdida;
import Usuario.DatoDeContacto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Publicacion {
  DatosMascotaPerdida datosMascotaPerdida;
  boolean validada = false;
  //Esto no se muestra en interfaz grafica
  List<DatoDeContacto> datoDeContactoDelRescatista = new ArrayList<>();

  public Publicacion(DatosMascotaPerdida datosMascotaPerdida, List<DatoDeContacto> datoDeContactoList) {
    this.datosMascotaPerdida = datosMascotaPerdida;
    this.datoDeContactoDelRescatista = datoDeContactoList;
  }

  public List<DatoDeContacto> getDatoDeContactoDelRescatista() {
    return datoDeContactoDelRescatista;
  }

  public void validar() {
    validada = true;
  }

  public DatosMascotaPerdida getDatosMascotaPerdida() {
    return datosMascotaPerdida;
  }

  public boolean validada() {
    return validada;
  }

  public boolean encontradaDespuesDe(LocalDate fechaMin) {
    return datosMascotaPerdida.encontradaDespuesDe(fechaMin);
  }
}
