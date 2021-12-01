package server;

import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
  public static void main(String[] args) {
    //Bootstrap.getInstance().init();
    //Spark.port(Integer.parseInt(System.getenv("PORT")));
    Spark.port(getHerokuAssignedPort());
    Router.init();
    DebugScreen.enableDebugScreen(); //TODO: No hay que tener esta linea cuando entreguemos
    Spark.staticFileLocation("/src/resources");
  }

  static int getHerokuAssignedPort() {
    ProcessBuilder processBuilder = new ProcessBuilder();
    if (processBuilder.environment().get("PORT") != null) {
      return Integer.parseInt(processBuilder.environment().get("PORT"));
    }

    return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
  }

}
