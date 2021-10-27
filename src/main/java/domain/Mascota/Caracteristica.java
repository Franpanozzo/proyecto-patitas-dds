package domain.Mascota;

public class Caracteristica {
  String key;
  String value;

  public Caracteristica(String k, String v) {
    key = k;
    value = v;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
