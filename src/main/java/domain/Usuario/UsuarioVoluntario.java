package domain.Usuario;

import domain.Asociacion.*;
import domain.Exceptions.*;
import domain.Publicaciones.PublicacionMascotaPerdida;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("V")
public class UsuarioVoluntario extends Usuario {

  public UsuarioVoluntario(String nombreUsuario, String contrasenia, Asociacion asociacion, DatosPersonales datosPersonales) {
    super(nombreUsuario, contrasenia, asociacion, datosPersonales);
  }

  public boolean mismoCodigoQR(String codigoQR) {
    return false;
  }

  public void aprobarPublicaciones(List<PublicacionMascotaPerdida> publicaciones) {
    asociacion.aprobarPublicaciones(publicaciones);
  }

  @Override
  public String getMailContacto() {
    throw new UsuarioNoPerdioMascotaException("Los usuarios voluntarios no pierden mascotas, por lo tanto no se los notifica");
  }
  @Override
  public List<DatoDeContacto> getDatoDeContactoList() {
    throw new UsuarioNoTieneDatoDeContacto("Los usuarios voluntarios no tienen dato de contacto");
  }

  public UsuarioVoluntario() {

  }
}




