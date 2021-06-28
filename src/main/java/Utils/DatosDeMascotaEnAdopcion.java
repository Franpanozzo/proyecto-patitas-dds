package Utils;

import java.util.ArrayList;
import java.util.List;

public class Preferencia {
  List <Comodidades> comodidades;
  private List<String> preferencias = new ArrayList<>();

  public DatosDeMascotaEnAdopcion(List<String> preferencias, List <Comodidades> comodidades) {
    this.preferencias = preferencias;
    this.comodidades = comodidades;
  }
}
