package server;

import com.mysql.jdbc.StringUtils;
import controllers.MascotasController;
import controllers.UsuariosController;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router implements WithGlobalEntityManager {
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

    Spark.before("/mascotas", (request, response) -> {
      if(request.session().attribute("usuario_logueado") == null) {
        response.redirect("/login");
      }
    });

    Spark.after(((request, response) -> {
      PerThreadEntityManagers.getEntityManager().getEntityManagerFactory().getCache().evictAll();
    }));

    Spark.get("/", usuariosController::mostrar, engineTemplate);

    Spark.get("/login", usuariosController::entrar, engineTemplate);

    Spark.post("/session", usuariosController::loggear, engineTemplate);

    Spark.post("/sessionOut", usuariosController::logout);

    Spark.get("/signup", usuariosController::signup, engineTemplate);

    Spark.post("/signup", usuariosController::crear);

    Spark.get("/mascotas", mascotasController::mascotas, engineTemplate);


  }

}