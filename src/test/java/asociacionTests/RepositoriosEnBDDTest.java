package asociacionTests;

import Repositorios.RepositorioPreguntasGlobales;
import Utils.Pregunta;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import java.util.Arrays;
import java.util.List;


public class RepositoriosEnBDDTest extends BaseTest implements WithGlobalEntityManager {

  @Test
  public void estaCargadaUnaPreguntaConAsociacionYLaQueryNoLaAgarra() {

    final List<Pregunta> preguntas = RepositorioPreguntasGlobales.getInstance().getListaDePreguntasRequeridas();

    assertEquals(2, preguntas.size());
    assertEquals(preguntas.get(0).getTipo(), "ANIMAL");
    assertEquals(preguntas.get(1).getTipo(), "TAMANIO");
  }

  @Test
  public void seBorraCorrectamenteUnaPreguntaDeLaBase() {
    RepositorioPreguntasGlobales.getInstance().sacarPreguntaRequerida(tipoAnimal);
    final List<Pregunta> preguntas = RepositorioPreguntasGlobales.getInstance().getListaDePreguntasRequeridas();

    assertEquals(1, preguntas.size());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void seCargaPorCascadaLaPreguntaNoRequeridaDeLaAsociacion() {
    final List<Pregunta> preguntas = entityManager().createQuery("from Pregunta").getResultList();

    assertEquals(3, preguntas.size());
  }
}

