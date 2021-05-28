package Mascota;

import EntidadesExternas.Rescatista;
import FormasDeEncuentro.FormaDeEncuentro;

import java.time.LocalDate;

public class DatosMascotaPerdida {
  Rescatista rescatista;
  String foto;
  String descripcionEstado;
  Coordenadas lugarDeEncuentro;
  LocalDate fechaEncuentro;

  public DatosMascotaPerdida(Rescatista rescatista, String foto, String descripcionEstado, Coordenadas lugarDeEncuentro, LocalDate fechaEncuentro) {
    this.rescatista = rescatista;
    this.foto = foto;
    this.descripcionEstado = descripcionEstado;
    this.lugarDeEncuentro = lugarDeEncuentro;
    this.fechaEncuentro = fechaEncuentro;
  }

  public Coordenadas getLugarDeEncuentro() {
    return lugarDeEncuentro;
  }

  public LocalDate getFechaEncuentro(){
    return fechaEncuentro;
  }

}
