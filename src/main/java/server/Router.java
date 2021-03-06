package server;

import controllers.ConfiguracionController;
import controllers.LogController;
import controllers.MascotasController;
import controllers.UsuariosController;
import domain.Repositorios.RepositorioUsuarios;
import domain.Usuario.UsuarioAdministrador;
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
    LogController logController = new LogController();
    ConfiguracionController configuracionController = new ConfiguracionController();


    Spark.before(((request, response) -> {
      if(request.requestMethod() == "POST") {
        PerThreadEntityManagers.getEntityManager().getTransaction().begin();
      }
    }));

    Spark.after(((request, response) -> {
      if(request.requestMethod() == "POST") {
        PerThreadEntityManagers.getEntityManager().getTransaction().commit();
        PerThreadEntityManagers.getEntityManager().getEntityManagerFactory().getCache().evictAll();
      }
    }));

    Spark.exception(Exception.class, (e, req, res) -> {
      System.out.println(e.getMessage());
      PerThreadEntityManagers.getEntityManager().getTransaction().rollback();
    });


    Spark.before("/mascotas", (request, response) -> {
      if(request.session().attribute("usuario_logueado") == null) {
        response.redirect("/login");
      }

      UsuarioAdministrador usuario = RepositorioUsuarios.getInstance().administradorConNombre(request.session().attribute("usuario_logueado"));
      if(usuario != null ) {
        response.redirect("/configuracion");
      }
    });

    Spark.before("/configuracion", (request, response) -> {
      UsuarioAdministrador usuario = RepositorioUsuarios.getInstance().administradorConNombre(request.session().attribute("usuario_logueado"));
      if(usuario == null ) {
        response.redirect("/");
      }
    });

    //RUTAS

    Spark.get("/", logController::mostrar, engineTemplate);

    Spark.get("/login", logController::entrar, engineTemplate);

    Spark.post("/session", usuariosController::loggear, engineTemplate);

    Spark.get("/sessionOut", logController::logout);

    Spark.get("/signup", logController::signup, engineTemplate);

    Spark.post("/signup", usuariosController::guardar, engineTemplate);

    Spark.get("/mascotas", mascotasController::mascotas, engineTemplate);

    Spark.get("/mascotas/nueva", logController::nuevaMascota, engineTemplate);

    Spark.post("/mascotas/nueva", mascotasController::guardar);

    Spark.get("/configuracion", logController::configuracion, engineTemplate);

    Spark.post("/configuracion/nuevaCaract", configuracionController::nuevaCaract);

    Spark.get("/configuracion/:caractBorrada", configuracionController::borrarCaract);

    Spark.post("/configuracion/preguntas/nuevaPregunta", configuracionController::nuevaPreg);

    Spark.get("/configuracion/preguntas/:pregBorrada", configuracionController::borrarPreg);

    Spark.get("/mascotas/encontrada", logController::encontrar, engineTemplate);

    Spark.post("/mascotas/encontrada", mascotasController::guardarEncontrada);

  }

}