package Repositorios;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

import Asociacion.*;
import Mascota.*;

public class RepositorioAsociaciones {

  private static RepositorioAsociaciones instance;
  public List<Asociacion> listaAsociaciones = new ArrayList<>();

  public static RepositorioAsociaciones getInstance() {
    if (instance == null) {
      instance = new RepositorioAsociaciones();
    }
    return instance;
  }

  public List<Asociacion> getListaAsociaciones() {
    return listaAsociaciones;
  }

  public void agregarAsociacion(Asociacion asociacion) {
    listaAsociaciones.add(asociacion);
  }

  public Asociacion masCercanaA(MascotaPerdida mascota){
     return listaAsociaciones.stream()
         .min(Comparator.comparing(asociacion -> asociacion.distanciaALugarDeEncuentro(mascota)))
         .get();
  }

  public void sacarAsociacion(Asociacion patitas) {
    listaAsociaciones.remove(patitas);
  }
}
