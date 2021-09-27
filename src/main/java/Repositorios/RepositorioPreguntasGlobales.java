package Repositorios;

import Exceptions.PreguntaNoGlobalException;
import Utils.Pregunta;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import javax.persistence.Entity;
import java.util.ArrayList;
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

  public List<Pregunta> getListaDePreguntasRequeridas() {
    return listaDePreguntasRequeridas;
  }

  public void agregarPreguntaRequerida(Pregunta pregunta) {
    listaDePreguntasRequeridas.add(pregunta);
  }

  public void sacarPreguntaRequerida(Pregunta pregunta) {
    entityManager()
        .remove(pregunta);
  }
}




