package Asociacion;

import Mascota.MascotaPerdida;
import Usuario.Usuario;

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

  public void buscarDuenio(MascotaPerdida mascotaPerdida) {
    Usuario usuario = listaDeUsuarios
      .stream()
      .filter(unUsuario -> unUsuario.mismoCodigoQR(mascotaPerdida.getCodigoQR()))
      .findAny().get();
    //TODO: usuario.notificarPorMail();
  }
}
