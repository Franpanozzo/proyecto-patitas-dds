package domain.Mascota;

import domain.EntidadesExternas.Rescatista;
import domain.Exceptions.DescripcionInvalidaException;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Embeddable
public class DatosMascotaPerdida {
  @Transient
  Rescatista rescatista;
  @Enumerated(EnumType.STRING)
  Animal animal;
  @Enumerated(EnumType.STRING)
  Tamanio tamanio;
  String foto;
  @ElementCollection
  List<String> descripcionEstado;
  @Embedded
  Coordenadas lugarDeEncuentro; //embedded
  @Column(columnDefinition = "DATE")
  LocalDate fechaEncuentro;
  @Transient
  List<String> descripcionesPosibles = Arrays.asList("MANSO","DELGADO","AMISTOSO","PACIFICO","TRANQUILO");

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
    if(!descripcionesPosibles.containsAll(descripcionEstado.stream().map(String::toUpperCase).collect(Collectors.toList()))) {
      throw new DescripcionInvalidaException("Solo me interesan estas 5 caracteristicas: Manso|Delgado|Amistoso|Pacifico|Tranquilo ");
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

  public DatosMascotaPerdida() {

  }
}

