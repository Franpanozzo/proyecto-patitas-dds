package server;

import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
  public static void main(String[] args) {
    //Bootstrap.getInstance().init();
    Spark.port(9000);
    Router.init();
    DebugScreen.enableDebugScreen(); //TODO: No hay que tener esta linea cuando entreguemos
  }
}
