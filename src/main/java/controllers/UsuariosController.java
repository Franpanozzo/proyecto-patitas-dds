package controllers;

import domain.Repositorios.RepositorioUsuarios;
import domain.Usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class UsuariosController {

  public ModelAndView mostrar(Request request, Response response) {
    return new ModelAndView(null, "home.hbs");
  }

  public ModelAndView entrar(Request request, Response response) {
    return new ModelAndView(null, "login.hbs");
  }

  public ModelAndView post(Request request, Response response) {
    String usuario = request.queryParams("nombre");
    String password = request.queryParams("password");
    Usuario usuarioEncontrado = RepositorioUsuarios.getInstance().usuarioConNombre(usuario);

    if (usuarioEncontrado == null ||
        !usuarioEncontrado.equals(password)) {
      return new ModelAndView(null, "login.hbs");
    }

    request.session().attribute("usuario_logueado", usuario);
    response.redirect("/mascotas");
    return null;

  }

  public Response logout(Request request, Response response){
    request.session().invalidate();
    response.redirect("/");
    return response;
  }
}

