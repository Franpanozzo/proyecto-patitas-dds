package Usuario;

import Asociacion.Asociacion;
import Seguridad.Validaciones;

import java.util.*;

public abstract class Usuario {
  String nombreUsuario;
  String contrasenia;
  Asociacion asociacion;


  public Usuario(String nombreUsuario, String contrasenia, Asociacion asociacion) {
    this.nombreUsuario = Objects.requireNonNull(nombreUsuario, "Nombre de usuario no tiene que ser null. ");
    this.contrasenia = Validaciones.validarContrasenia(contrasenia, nombreUsuario);
    this.asociacion = Objects.requireNonNull(asociacion, "El usuario tiene que registrarse en una asociacion. ");
    asociacion.registrarUsuario(this);
  }

  public String getNombreUsuario() {
    return nombreUsuario;
  }

  public abstract boolean mismoCodigoQR(String codigoQR);

  public abstract String getMailContacto();
}




