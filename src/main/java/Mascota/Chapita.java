package Mascota;
import Asociacion.*;
import Usuario.*;

public class Chapita {
  private String codigoQR;
  private Asociacion asociacion;

  public Chapita(String codigo, Asociacion asociacion) {
    this.codigoQR = codigo;
    this.asociacion = asociacion;
  }

  public Asociacion getAsociacion() {
    return asociacion;
  }

  public void identificarDuenioEnAsociacion() {
    asociacion.buscarDuenioYNotificar(codigoQR);
  }
}



