package domain.Usuario;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Embeddable
public class DatosPersonales {
  String nombreApellido;
  @Column(columnDefinition = "DATE")
  LocalDate fechaNacimiento;
  @Enumerated(EnumType.STRING)
  TipoDocumento tipoDocumento;
  Integer numeroDocumento;

  public DatosPersonales(String nombreApellido,
                         LocalDate fechaNacimiento,
                         TipoDocumento tipoDocumento,
                         Integer numeroDocumento){
    this.nombreApellido = nombreApellido;
    this.fechaNacimiento = fechaNacimiento;
    this.tipoDocumento = tipoDocumento;
    this.numeroDocumento = numeroDocumento;
  }

  public DatosPersonales() {

  }

  public String getNombreYApellidos() {
    return nombreApellido;
  }
}
