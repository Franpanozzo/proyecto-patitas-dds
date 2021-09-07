package Usuario;

import Asociacion.Asociacion;
import ClasesPersistencia.EntidadPersistente;
import Seguridad.Validaciones;

import javax.persistence.*;
import java.util.*;

@Entity
public abstract class Usuario extends EntidadPersistente{

  @Transient
  DatosPersonales datosPersonales;
  String nombreUsuario;
  String contrasenia;
  @Transient
  Asociacion asociacion;


  public Usuario(String nombreUsuario, String contrasenia, Asociacion asociacion, DatosPersonales datosPersonales) {
    this.nombreUsuario = Objects.requireNonNull(nombreUsuario, "Nombre de usuario no tiene que ser null. ");
    this.contrasenia = Validaciones.validarContrasenia(contrasenia, nombreUsuario);
    this.asociacion = Objects.requireNonNull(asociacion, "El usuario tiene que registrarse en una asociacion. ");
    this.datosPersonales = Objects.requireNonNull(datosPersonales, "Ingrese los datos personales. ");
    asociacion.registrarUsuario(this);
  }

  public Usuario() {

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
}




