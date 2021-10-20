package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class UsuariosController {

  public ModelAndView mostrar(Request request, Response response) {
    return new ModelAndView(null, "home.hbs");
  }
}
