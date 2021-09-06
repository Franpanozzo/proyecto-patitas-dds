package Repositorios;

import Utils.Pregunta;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

public class RepositorioPreguntasGlobales {

  private static RepositorioPreguntasGlobales instance = new RepositorioPreguntasGlobales();
  List<Pregunta> listaDePreguntasRequeridas = new ArrayList<>();

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
    listaDePreguntasRequeridas.remove(pregunta);
  }
}




