package domain.Repositorios;

import domain.Exceptions.PreguntaNoGlobalException;
import domain.Utils.Pregunta;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class RepositorioPreguntasGlobales implements WithGlobalEntityManager {

  private static RepositorioPreguntasGlobales instance = new RepositorioPreguntasGlobales();

  @SuppressWarnings("unchecked")
  public List<Pregunta> getListaDePreguntasRequeridas() {
    return entityManager()
        .createQuery("from Pregunta WHERE asociacion_Id IS NULL")
        .getResultList();
  }

  public static RepositorioPreguntasGlobales getInstance() {
    return instance;
  }

  @SuppressWarnings("unchecked")
  public void agregarPreguntaRequerida(Pregunta pregunta) {
    if(pregunta.getAsociacion() == null){
      entityManager()
          .persist(pregunta);
    }
    else{
      throw new PreguntaNoGlobalException(
          "La pregunta que intenta agregar no es global, pertenece a la asociacion " + pregunta.getAsociacion()
      );
    }
  }

  @SuppressWarnings("unchecked")
  public void sacarPreguntaRequerida(String tipoPregunta) {

    Pregunta pregunta = (Pregunta) entityManager()
        .createQuery("from Pregunta where tipo = :tipo")
        .setParameter("tipo", tipoPregunta)
        .getSingleResult();

    entityManager().remove(pregunta);
  }
}

