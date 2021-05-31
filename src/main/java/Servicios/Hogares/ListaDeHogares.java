package Servicios.Hogares;

import java.util.ArrayList;
import java.util.List;

public class ListaDeHogares {
  Integer total;
  Integer offset;
  List<Hogares> hogares = new ArrayList<>();

  public void getHogares() {
    System.out.println(hogares.toString());
  }


}

