package domain.Hogares;

import java.util.ArrayList;
import java.util.List;

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

