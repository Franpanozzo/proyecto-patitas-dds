package ClasesPersistencia;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class EntidadPersistente {

  @Id
  @GeneratedValue
  private long Id;

  public long getId() {
    return Id;
  }
}
