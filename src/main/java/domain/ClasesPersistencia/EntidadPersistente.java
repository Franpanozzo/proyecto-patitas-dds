package domain.ClasesPersistencia;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class EntidadPersistente {

  @Id
  @GeneratedValue
  private long Id;

  public long getId() {
    return Id;
  }
}
