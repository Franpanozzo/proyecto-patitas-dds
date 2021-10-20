package server;

import controllers.UsuariosController;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {

  public static void init() {
    Spark.staticFileLocation("/public");
    Router.configure();
  }

  public static void configure() {
    HandlebarsTemplateEngine engineTemplate = new HandlebarsTemplateEngine();
    UsuariosController usuariosController = new UsuariosController();

    Spark.get("/", (req, res) -> "Hola");

    Spark.get("/usuarios", usuariosController::mostrar, engineTemplate);

  }

}