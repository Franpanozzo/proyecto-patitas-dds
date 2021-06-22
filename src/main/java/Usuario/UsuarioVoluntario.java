package Usuario;

import Asociacion.*;
import Exceptions.*;
import Utils.Pregunta;
import Publicaciones.PublicacionMascotaPerdida;

import java.util.List;

public class UsuarioVoluntario extends Usuario {

  public UsuarioVoluntario(String nombreUsuario, String contrasenia, Asociacion asociacion, DatosPersonales datosPersonales) {
    super(nombreUsuario, contrasenia, asociacion, datosPersonales);
  }

  public boolean mismoCodigoQR(String codigoQR) {
    return false;
  }

  public void aprobarPublicaciones(List<PublicacionMascotaPerdida> publicaciones) {
    asociacion.aprobarPublicacion(publicaciones);
  }

  public void agregarPreguntaParaAdopcion(Pregunta preguntaNueva) {
    asociacion.agregarPregunta(preguntaNueva);
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




