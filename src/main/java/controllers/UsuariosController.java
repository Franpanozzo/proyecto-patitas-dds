package controllers;

import domain.Repositorios.RepositorioUsuarios;
import domain.Usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.NoResultException;
import java.util.HashMap;
import java.util.Map;

public class UsuariosController {

  public ModelAndView mostrar(Request request, Response response) {
    return new ModelAndView(null, "home.hbs");
  }

  public ModelAndView entrar(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    model.put("botonSignUp","botonSignUp");
    return new ModelAndView(model, "login.hbs");
  }

  public ModelAndView post(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    String usuario = request.queryParams("nombre");
    String password = request.queryParams("password");
    Usuario usuarioEncontrado = RepositorioUsuarios.getInstance().usuarioConNombre(usuario);

    if (usuarioEncontrado == null || !usuarioEncontrado.getContrasenia().equals(password)) {
      //Aca podemos imprimir un mensaje en el coso que esta mal el nombre de usuario o contraseña
      model.put("botonSignUp","botonSignUp");
      return new ModelAndView(model, "login.hbs");
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

