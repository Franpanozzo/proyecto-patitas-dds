package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MascotasController {
  public ModelAndView inicio(Request request, Response response) {
    return new ModelAndView(null, "mascotas.hbs");
  }
}
