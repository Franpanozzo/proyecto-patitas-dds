package domain.Usuario;

import domain.Asociacion.Asociacion;
import domain.ClasesPersistencia.EntidadPersistente;
import domain.Seguridad.Validaciones;

import javax.persistence.*;
import java.util.*;

@Entity
@DiscriminatorColumn(name = "tipo", length = 1)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Usuario extends EntidadPersistente{

  @Embedded
  DatosPersonales datosPersonales;
  String nombreUsuario;
  String contrasenia;
  @ManyToOne
  Asociacion asociacion;

  public Usuario(String nombreUsuario, String contrasenia, Asociacion asociacion, DatosPersonales datosPersonales) {
    this.nombreUsuario = Objects.requireNonNull(nombreUsuario, "Nombre de usuario no tiene que ser null. ");
    this.contrasenia = Validaciones.validarContrasenia(contrasenia, nombreUsuario);
    this.asociacion = asociacion;
    this.datosPersonales = Objects.requireNonNull(datosPersonales, "Ingrese los datos personales. ");
    if(asociacion != null) {
      asociacion.registrarUsuario(this);
    }
  }

  public String getNombreUsuario() {
    return nombreUsuario;
  }

  public abstract boolean mismoCodigoQR(String codigoQR);

  public abstract String getMailContacto();

  public String getNombreYApellido(){
    return datosPersonales.getNombreYApellidos();
  }

  public abstract List<DatoDeContacto> getDatoDeContactoList();

  public Usuario() {

  }

  public String getContrasenia() {
    return contrasenia;
  }
}




