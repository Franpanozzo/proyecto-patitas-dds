package Usuario;

import Asociacion.Asociacion;

import java.time.LocalDate;
import java.util.List;

public class UsuarioAdministrador extends Usuario {

    public UsuarioAdministrador(String nombreUsuario, String contraseña, String nombreApellido, LocalDate fechaNacimiento, String tipoDocumento, Integer numeroDocumento, List<DatoDeContacto> datoDeContactoList) {
        super(nombreUsuario, contraseña, nombreApellido, fechaNacimiento, tipoDocumento, numeroDocumento, datoDeContactoList);
    }

    public void agregarCaracteristica(String nuevaCaract) {
        Asociacion.getInstance().agregarCarateristica(nuevaCaract.toUpperCase());
    }
}
