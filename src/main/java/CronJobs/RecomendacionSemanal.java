package CronJobs;

import Asociacion.Asociacion;
import Repositorios.RepositorioAsociaciones;

public class RecomendacionSemanal {

  public static void main(String[] args){
    RepositorioAsociaciones.getInstance().getListaAsociaciones().forEach(Asociacion::enviarRecomendaciones);
  }
}





