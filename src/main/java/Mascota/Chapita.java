package Mascota;
import Asociacion.*;
import Usuario.*;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

@Embeddable
public class Chapita {

  //Foreign key a UsuarioDuenio
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



