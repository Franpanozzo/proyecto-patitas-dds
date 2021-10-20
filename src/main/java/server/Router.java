package server;

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

    Spark.get("/", (req, res) -> "Hola");

  }

}