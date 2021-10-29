package domain.Repositorios;
import java.util.List;
import java.util.Comparator;

import domain.Asociacion.*;
import domain.Mascota.*;
import domain.Usuario.Usuario;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class RepositorioAsociaciones implements WithGlobalEntityManager {

  private static RepositorioAsociaciones instance;


  public static RepositorioAsociaciones getInstance() {
    if (instance == null) {
      instance = new RepositorioAsociaciones();
    }
    return instance;
  }

  @SuppressWarnings("unchecked")
  public List<Asociacion> getListaAsociaciones() {
    return entityManager()
        .createQuery("from Asociacion")
        .getResultList();
  }

  @SuppressWarnings("unchecked")
  public void agregarAsociacion(Asociacion asociacion) {
    entityManager()
        .persist(asociacion);
  }

  public Asociacion asociacionConNombre(String nombreAsociacion) {
    return (Asociacion) entityManager()
        .createQuery("from Asociacion where nombreAsociacion = :nombreAsociacion")
        .setParameter("nombreAsociacion", nombreAsociacion)
        .getSingleResult();
  }

  public Asociacion masCercanaA(MascotaPerdida mascota){
    return getListaAsociaciones().stream()
        .min(Comparator.comparing(asociacion -> asociacion.distanciaALugarDeEncuentro(mascota)))
        .get();
  }

  @SuppressWarnings("unchecked")
  public void sacarAsociacion(Asociacion asociacion) {
    entityManager()
        .remove(asociacion);
  }
}
