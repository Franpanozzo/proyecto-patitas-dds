package Usuario;

import Asociacion.Asociacion;
import Exceptions.*;
import Utils.Pregunta;

import java.util.List;
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

    @Override
    public List<DatoDeContacto> getDatoDeContactoList() {
        throw new UsuarioNoTieneDatoDeContacto("Los usuarios administradores no tienen dato de contacto");
    }

    public void agregarCaracteristica(String nuevaCaract) {
        asociacionDondeTrabaja.agregarCarateristica(nuevaCaract.toUpperCase());
    }

    public void agregarPreguntaParaAdopcion(Pregunta preguntaNueva) {
        asociacion.agregarPregunta(preguntaNueva);
    }


}
