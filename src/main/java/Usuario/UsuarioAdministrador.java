package Usuario;

import Asociacion.Asociacion;

import java.util.Objects;

public class UsuarioAdministrador extends Usuario {
    Asociacion asociacionDondeTrabaja;

    public UsuarioAdministrador(String nombreUsuario, String contrasenia, Asociacion asociacionDondeTrabaja) {
        super(nombreUsuario, contrasenia, asociacionDondeTrabaja);
        this.asociacionDondeTrabaja = Objects.requireNonNull(asociacionDondeTrabaja,"La asociacion donde trabaja no tiene que ser null");
    }

    public void agregarCaracteristica(String nuevaCaract) {
        asociacionDondeTrabaja.agregarCarateristica(nuevaCaract.toUpperCase());
    }
}
