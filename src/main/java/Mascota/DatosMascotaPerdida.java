package Mascota;

import EntidadesExternas.Rescatista;
import Exceptions.DescripcionInvalidaException;
import FormasDeEncuentro.FormaDeEncuentro;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class DatosMascotaPerdida {
  Rescatista rescatista;
  String foto;
  List<String> descripcionEstado;
  Coordenadas lugarDeEncuentro;
  LocalDate fechaEncuentro;
  List<String> descripcionesPosibles = Arrays.asList("Manso","Delgado","Amistoso");

  public DatosMascotaPerdida(Rescatista rescatista, String foto, String descripcionEstado, Coordenadas lugarDeEncuentro, LocalDate fechaEncuentro) {
    this.rescatista = rescatista;
    this.foto = foto;
    this.descripcionesValidas(descripcionEstado);
    this.descripcionEstado = descripcionEstado;
    this.lugarDeEncuentro = lugarDeEncuentro;
    this.fechaEncuentro = fechaEncuentro;
  }

  public Coordenadas getLugarDeEncuentro() {
    return lugarDeEncuentro;
  }

  public boolean encontradaDespuesDe(LocalDate fechaLimite){
    return fechaEncuentro.isAfter(fechaLimite);
  }

}

