package controllers;

import domain.Asociacion.Asociacion;
import domain.Repositorios.RepositorioAsociaciones;
import domain.Repositorios.RepositorioPreguntasGlobales;
import domain.Repositorios.RepositorioUsuarios;
import domain.Usuario.UsuarioAdministrador;
import domain.Utils.Pregunta;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfiguracionController {
  public Response nuevaCaract(Request request, Response response) {
    UsuarioAdministrador usuario = RepositorioUsuarios.getInstance().administradorConNombre(request.session().attribute("usuario_logueado"));
    String nuevaCaract = request.queryParams("caract");

    usuario.agregarCaracteristica(nuevaCaract);
    RepositorioAsociaciones.getInstance().modificarAsociacion(usuario.getAsociacion());

    response.redirect("/configuracion");
    return null;
  }

  public ModelAndView borrarCaract(Request request, Response response) {
    UsuarioAdministrador usuario = RepositorioUsuarios.getInstance().administradorConNombre(request.session().attribute("usuario_logueado"));
    String caractBorrada = request.params("caractBorrada");

    usuario.quitarCaracteristica(caractBorrada);
    PerThreadEntityManagers.getEntityManager().getTransaction().begin();
    RepositorioAsociaciones.getInstance().modificarAsociacion(usuario.getAsociacion());
    PerThreadEntityManagers.getEntityManager().getTransaction().commit();

    response.redirect("/configuracion");
    return null;
  }

  public Response nuevaPreg(Request request, Response response) {
    String pregIntencion = request.queryParams("pregIntencion");
    String pregAdopcion = request.queryParams("pregAdopcion");
    String tipo = request.queryParams("tipo");
    String[] respPosibles = request.queryParams("respPosibles").split(",");
    List<String> respPosiblesArray = Arrays.asList(respPosibles);

    Pregunta pregunta = new Pregunta(pregAdopcion, pregIntencion, tipo, respPosiblesArray, null);

    RepositorioPreguntasGlobales.getInstance().agregarPreguntaRequerida(pregunta);

    response.redirect("/configuracion");
    return null;
  }

  public ModelAndView borrarPreg(Request request, Response response) {
    String pregBorrada = request.params("pregBorrada");

    PerThreadEntityManagers.getEntityManager().getTransaction().begin();
    RepositorioPreguntasGlobales.getInstance().sacarPreguntaRequerida(pregBorrada);
    PerThreadEntityManagers.getEntityManager().getTransaction().commit();

    response.redirect("/configuracion");
    return null;
  }
}
