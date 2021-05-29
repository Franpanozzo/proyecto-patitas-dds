package Repositorios;

import Mailer.JavaMail;
import Mailer.Mail;
import Usuario.Usuario;

import java.time.chrono.JapaneseChronology;
import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuarios {

  List<Usuario> listaDeUsuarios = new ArrayList<>();

  public void cargarNuevoUsuario(Usuario usuarioNuevo) {
    listaDeUsuarios.add(usuarioNuevo);
  }

  public List<Usuario> getlistaDeUsuarios() {
    return listaDeUsuarios;
  }

  public void buscarDuenioYNotificar(String codigoQR) {
    Usuario usuario = listaDeUsuarios
      .stream()
      .filter(unUsuario -> unUsuario.mismoCodigoQR(codigoQR))
      .findAny().get();
    this.notificarPorMail(usuario);
  }

  private void notificarPorMail(Usuario usuario) {
    JavaMail.enviarMail(new Mail(usuario.getMailContacto(),"AVISO DE ENCUENTRO DE MASCOTA",
        "Buenos dias, encontramos a la mascota que perdio tu familiar"));
  }
}




