package domain.CronJobs;

import domain.Asociacion.Asociacion;
import domain.Repositorios.RepositorioAsociaciones;

public class RecomendacionSemanal {

  public static void main(String[] args){
    RepositorioAsociaciones.getInstance().getListaAsociaciones().forEach(Asociacion::enviarRecomendaciones);
  }
}





