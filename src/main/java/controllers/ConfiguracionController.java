package controllers;

import domain.Asociacion.Asociacion;
import domain.Repositorios.RepositorioAsociaciones;
import domain.Repositorios.RepositorioUsuarios;
import domain.Usuario.UsuarioAdministrador;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfiguracionController {
  public Response nuevaCaract(Request request, Response response) {
    UsuarioAdministrador usuario = RepositorioUsuarios.getInstance().administradorConNombre(request.session().attribute("usuario_logueado"));
    String nuevaCaract = request.queryParams("caract");
    System.out.println("Caract nueva:" + nuevaCaract);
    System.out.println("Usuario:" + usuario.getNombreUsuario());


    usuario.agregarCaracteristica(nuevaCaract);
    RepositorioAsociaciones.getInstance().modificarAsociacion(usuario.getAsociacion());

    response.redirect("/configuracion");
    return null;
  }

  public ModelAndView borrarCaract(Request request, Response response) {
    UsuarioAdministrador usuario = RepositorioUsuarios.getInstance().administradorConNombre(request.session().attribute("usuario_logueado"));
    String caractBorrada = request.params("caractBorrada");

    usuario.quitarCaracteristica(caractBorrada);
    RepositorioAsociaciones.getInstance().modificarAsociacion(usuario.getAsociacion());

    response.redirect("/configuracion");
    return null;
  }
}
