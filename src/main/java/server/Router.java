package server;

import controllers.MascotasController;
import controllers.UsuariosController;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {
  private static HandlebarsTemplateEngine engine;

  private static void initEngine() {
    Router.engine = HandlebarsTemplateEngineBuilder
        .create()
        .withDefaultHelpers()
        .withHelper("isTrue", BooleanHelper.isTrue)
        .build();
  }

  public static void init() {
    Router.initEngine();
    Spark.staticFileLocation("/public");
    Router.configure();
  }

  public static void configure() {
    HandlebarsTemplateEngine engineTemplate = new HandlebarsTemplateEngine();
    UsuariosController usuariosController = new UsuariosController();
    MascotasController mascotasController = new MascotasController();

    Spark.get("/", (req, res) -> "Hola");

    Spark.get("/usuarios", usuariosController::mostrar, engineTemplate);

    Spark.get("/login", usuariosController::entrar, engineTemplate);

    Spark.post("/session", usuariosController::post, engineTemplate);

    Spark.get("/mascotas", mascotasController::inicio, engineTemplate);


  }

}