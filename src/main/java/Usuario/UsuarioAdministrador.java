package Usuario;

import Asociacion.Asociacion;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class UsuarioAdministrador extends Usuario {
    Asociacion asociacionDondeTrabaja;

    @Override
    public UsuarioAdministrador crearUsuario(String nombreUsuario,
                                             String contrasenia,
                                             String nombreApellido,
                                             LocalDate fechaNacimiento,
                                             tipoDocumento tipoDocumento,
                                             Integer numeroDocumento,
                                             List<DatoDeContacto> datoDeContactoList,
                                             Asociacion asociacionDondeTrabaja){
        this.nombreUsuario = Objects.requireNonNull(nombreUsuario, "Nombre de usuario no tiene que ser null");
        this.contrasenia = validarContrasenia(nombreUsuario, contrasenia);
        this.nombreApellido = Objects.requireNonNull(nombreApellido, "El nombre apellido no tiene que ser null");
        this.fechaNacimiento =Objects.requireNonNull(fechaNacimiento, "La fecha nacimiento no tiene que ser null");
        this.tipoDocumento = Objects.requireNonNull(tipoDocumento, "El tipo documento, no tiene que ser null");
        this.numeroDocumento = Objects.requireNonNull(numeroDocumento, "El numero documento no tiene que ser null");
        this.datoDeContactoList = Objects.requireNonNull(datoDeContactoList, "El dato de contacto no tiene que ser null");
        this.asociacionDondeTrabaja = Objects.requireNonNull(asociacionDondeTrabaja, "La asociacion donde trabaja no tiene que ser null");
        asociacionDondeTrabaja.registrarUsuario(this);
        return new UsuarioAdministrador();
    }

    public void agregarCaracteristica(String nuevaCaract) {
        asociacionDondeTrabaja.agregarCarateristica(nuevaCaract.toUpperCase());
    }
}
