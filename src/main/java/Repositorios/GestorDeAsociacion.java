package Repositorios;

import Mailer.JavaMail;
import Mailer.Mail;
import Usuario.Usuario;

import java.time.chrono.JapaneseChronology;
import java.util.ArrayList;
import java.util.List;

public class GestorDeAsociacion {
  JavaMail mail = new JavaMail();
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
    this.notificarPorMail(usuario, nombreAsociacion);
  }

//Set para mockear
  public void setMail(JavaMail mail) {
    this.mail = mail;
  }

  private void notificarPorMail(Usuario usuario, String nombreAsociacion) {
    mail.enviarMail(new Mail(usuario.getMailContacto(),"AVISO DE ENCUENTRO DE MASCOTA",
        "Buenos dias, encontramos a la mascota que perdio tu familiar " + usuario.getNombreYApellido()
                + ""
                + "\nContactanos lo antes posible para acordar el punto de entrega" ));
  }

  public void notificarRescatista(String mailRescatista, String mailDuenioNoRegistrado) {
    mail.enviarMail(new Mail(mailRescatista,"AVISO DE ENCUENTRO DE MASCOTA",
        "Buenos dias, el dueño de la mascota que encontraste quiere coordinar un punto de encuentro."
            + "\nContactate con el lo antes posible: " + mailDuenioNoRegistrado ));
  }


}




