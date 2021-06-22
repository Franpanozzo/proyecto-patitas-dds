package Utils;

import java.util.ArrayList;
import java.util.List;

public class Preferencia {
  private boolean tienePatio;
  private boolean tieneOtrosAnimales;
  private List<String> preferencias = new ArrayList<>();

  public Preferencia(boolean tienePatio, boolean tieneOtrosAnimales, List<String> preferencias) {
    this.tienePatio = tienePatio;
    this.tieneOtrosAnimales = tieneOtrosAnimales;
    this.preferencias = preferencias;
  }
}
