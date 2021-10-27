package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class MascotasController {
  public ModelAndView mascotas(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    model.put("botonLogOut",request.session().attribute("usuario_logueado"));
    return new ModelAndView(model, "mascotas.hbs");

  }
}
