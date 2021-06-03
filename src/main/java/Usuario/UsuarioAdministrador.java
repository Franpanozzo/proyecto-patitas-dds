package Usuario;

import Asociacion.Asociacion;
import Exceptions.UsuarioNoPerdioMascotaException;

import java.util.Objects;

public class UsuarioAdministrador extends Usuario {
    Asociacion asociacionDondeTrabaja;

    public UsuarioAdministrador(String nombreUsuario, String contrasenia, Asociacion asociacionDondeTrabaja, DatosPersonales datosPersonales) {
        super(nombreUsuario, contrasenia, asociacionDondeTrabaja, datosPersonales);
        this.asociacionDondeTrabaja = Objects.requireNonNull(asociacionDondeTrabaja,"La asociacion donde trabaja no tiene que ser nula");
    }

    @Override
    public boolean mismoCodigoQR(String codigoQR) {
        return false;
    }

    @Override
    public String getMailContacto() {
        throw new UsuarioNoPerdioMascotaException("Los usuarios administradores no pierden mascotas, por lo tanto no se los notifica");
    }

    public void agregarCaracteristica(String nuevaCaract) {
        asociacionDondeTrabaja.agregarCarateristica(nuevaCaract.toUpperCase());
    }

}
