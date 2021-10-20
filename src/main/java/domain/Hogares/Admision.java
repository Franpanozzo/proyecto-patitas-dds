package domain.Hogares;

public class Admision {
  boolean perros;
  boolean gatos;

  public Admision(boolean perros, boolean gatos) {
    this.perros = perros;
    this.gatos = gatos;
  }

  public boolean getPerros(){
    return perros;
  }

  public boolean getGatos(){
    return gatos;
  }
}
