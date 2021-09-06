package Mascota;

import EntidadesExternas.Rescatista;
import Exceptions.DescripcionInvalidaException;
import FormasDeEncuentro.FormaDeEncuentro;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class DatosMascotaPerdida {
  Rescatista rescatista;
  Animal animal;
  Tamanio tamanio;
  String foto;
  List<String> descripcionEstado;
  Coordenadas lugarDeEncuentro;
  LocalDate fechaEncuentro;
  List<String> descripcionesPosibles = Arrays.asList("Manso","Delgado","Amistoso","Pacifico","Tranquilo");

  public DatosMascotaPerdida(Rescatista rescatista, String foto, List<String> descripcionEstado, Coordenadas lugarDeEncuentro, LocalDate fechaEncuentro, Animal animal, Tamanio tamanio) {
    this.rescatista = rescatista;
    this.foto = foto;
    this.descripcionesValidas(descripcionEstado);
    this.descripcionEstado = descripcionEstado;
    this.lugarDeEncuentro = lugarDeEncuentro;
    this.fechaEncuentro = fechaEncuentro;
    this.tamanio = tamanio;
    this.animal = animal;
  }
  private void descripcionesValidas(List<String> descripcionEstado) {
    if(!descripcionesPosibles.containsAll(descripcionEstado)) {
      throw new DescripcionInvalidaException("Solo me interesan estas 3 caracteristicas: Manso|Delgado|Amistoso|Pacifico|Tranquilo ");
    }
  }

  public String getFoto() {
    return foto;
  }

  public List<String> getDescripcionEstado(){
    return descripcionEstado;
  }

  public Animal getAnimal(){
    return animal;
  }

  public Tamanio getTamanio(){return tamanio;}

  public Coordenadas getLugarDeEncuentro() {
    return lugarDeEncuentro;
  }

  public boolean encontradaDespuesDe(LocalDate fechaLimite){
    return fechaEncuentro.isAfter(fechaLimite);
  }

}

