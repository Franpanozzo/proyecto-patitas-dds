package Servicios.Hogares;

import Mascota.DatosMascotaPerdida;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaDeHogares {
  Integer total;
  Integer offset;
  List<Hogar> hogares = new ArrayList<>();

  public ListaDeHogares(int total, List<Hogar> hogares) {
    this.total = total;
    this.hogares = hogares;
  }

  public List<Hogar> getListaHogares() {
    return hogares;
  }

  public Integer getTotal() {
    return total;
  }

  public Integer getOffset() {
    return offset;
  }

}

