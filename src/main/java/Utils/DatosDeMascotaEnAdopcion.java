package Utils;

import java.util.ArrayList;
import java.util.List;
import Mascota.Animal;
import Mascota.Sexo;
import Mascota.Tamanio;

public class DatosDeMascotaEnAdopcion {
  Animal tipoDeAnimal;
  Sexo sexoDeAnimal;
  Tamanio tamanioDeAnimal;
  List <Comodidades> comodidades;
  private List<String> preferencias = new ArrayList<>();

  public DatosDeMascotaEnAdopcion(List<String> preferencias, List <Comodidades> comodidades) {
    this.preferencias = preferencias;
    this.comodidades = comodidades;
  }
}
