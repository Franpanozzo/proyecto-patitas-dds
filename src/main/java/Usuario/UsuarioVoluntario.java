package Usuario;

import Asociacion.Asociacion;
import Exceptions.*;

import java.util.List;

public class UsuarioVoluntario extends Usuario {

  public UsuarioVoluntario(String nombreUsuario, String contrasenia, Asociacion asociacion, DatosPersonales datosPersonales) {
    super(nombreUsuario, contrasenia, asociacion, datosPersonales);
  }

  public boolean mismoCodigoQR(String codigoQR) {
    return false;
  }

  public void aprobarPublicaciones() {
    asociacion.aprobarPublicaciones();
  }

  @Override
  public String getMailContacto() {
    throw new UsuarioNoPerdioMascotaException("Los usuarios voluntarios no pierden mascotas, por lo tanto no se los notifica");
  }
  @Override
  public List<DatoDeContacto> getDatoDeContactoList() {
    throw new UsuarioNoTieneDatoDeContacto("Los usuarios voluntarios no tienen dato de contacto");
  }
}




