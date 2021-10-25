package controllers;

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

    response.redirect("/mascotas");
    return null;
  }

}
