package Usuario;

import java.time.LocalDate;

public class DatosPersonales {
  String nombreApellido;
  LocalDate fechaNacimiento;
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
}
