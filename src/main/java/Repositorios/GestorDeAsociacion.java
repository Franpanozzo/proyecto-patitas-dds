package Repositorios;

import Mailer.JavaMail;
import Mailer.Mail;
import Notificacion.FormaDeNotificar;
import Usuario.*;

import java.time.chrono.JapaneseChronology;
import java.util.ArrayList;
import java.util.List;

public class GestorDeAsociacion {
  FormaDeNotificar formaDeNotificar;
  List<Usuario> listaDeUsuarios = new ArrayList<>();

  public void cargarNuevoUsuario(Usuario usuarioNuevo) {
    listaDeUsuarios.add(usuarioNuevo);
  }

  public List<Usuario> getlistaDeUsuarios() {
    return listaDeUsuarios;
  }

  public void buscarDuenioYNotificar(String codigoQR, String nombreAsociacion) {
    Usuario usuario = listaDeUsuarios
      .stream()
      .filter(unUsuario -> unUsuario.mismoCodigoQR(codigoQR))
      .findAny().get();
    this.notificar(usuario, nombreAsociacion);
  }

//Set para mockear
  public void setformaDeNotificar(FormaDeNotificar formaDeNotificar) {
    this.formaDeNotificar = formaDeNotificar;
  }

  private void notificar(Usuario usuario, String nombreAsociacion) {
    DatoDeContacto algunContactoDelUsuario = usuario.getDatoDeContactoList().stream().findAny().get();
    formaDeNotificar.enviarNotificacion(algunContactoDelUsuario,"AVISO DE ENCUENTRO DE MASCOTA",
        "Buenos dias, encontramos a la mascota que perdio tu familiar " + usuario.getNombreYApellido()
            + ""
            + "\nContactanos lo antes posible para acordar el punto de entrega" );
  }

  public void notificarRescatista(DatoDeContacto mailRescatista, String mailDuenioNoRegistrado) {
    formaDeNotificar.enviarNotificacion(mailRescatista,"AVISO DE ENCUENTRO DE MASCOTA",
        "Buenos dias, el dueño de la mascota que encontraste quiere coordinar un punto de encuentro."
            + "\nContactate con el lo antes posible: " + mailDuenioNoRegistrado );
  }


}




