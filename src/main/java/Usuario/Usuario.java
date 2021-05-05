package Usuario;

import Asociacion.Asociacion;
import Exceptions.ContraseniaDebilException;
import Exceptions.ReglasNistException;
import Seguridad.ReglasNist;

import java.time.LocalDate;
import java.util.*;
import Seguridad.OWASP;
import Seguridad.ValidacionesMain;

public abstract class Usuario {
    String nombreUsuario;
    String contrasenia;
    Asociacion asociacion;


    public Usuario(String nombreUsuario, String contrasenia, Asociacion asociacion) {
        this.nombreUsuario = Objects.requireNonNull(nombreUsuario, "Nombre de usuario no tiene que ser null");
        this.contrasenia = ValidacionesMain.validarContrasenia(nombreUsuario, contrasenia);
        this.asociacion = Objects.requireNonNull(asociacion, "El usuario tiene que registrarse en una asociacion");
        asociacion.registrarUsuario(this);
    }



}




