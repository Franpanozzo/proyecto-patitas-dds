package controllers;

import domain.Asociacion.Asociacion;
import domain.Repositorios.RepositorioAsociaciones;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogController {

  public ModelAndView mostrar(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    model.put("botonLogOut",request.session().attribute("usuario_logueado"));
    return new ModelAndView(model, "home.hbs");
  }

  public ModelAndView entrar(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    model.put("botonSignUp","botonSignUp");
    return new ModelAndView(model, "login.hbs");
  }

  public Response logout(Request request, Response response){
    request.session().invalidate();
    response.redirect("/");
    return response;
  }

  public ModelAndView signup(Request request, Response response) {
    return new ModelAndView(null, "signup.hbs");
  }

  public ModelAndView nuevaMascota(Request request, Response response) {
    return new ModelAndView(null,"guardar_mascota.hbs");
  }

  public ModelAndView configuracion(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    Asociacion asociacion = RepositorioAsociaciones.getInstance().asociacionConNombre("Patitas");
    List<String> caracts = asociacion.getCaracteristicasPosibles();
    model.put("caracteristicas",caracts);
    model.put("botonLogOut","botonLogOut");

    return new ModelAndView(model, "configuracion.hbs");
  }
}
