package asociacionTests;

import domain.Repositorios.RepositorioPreguntasGlobales;
import domain.Usuario.UsuarioDuenio;
import domain.Utils.Pregunta;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

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
    RepositorioPreguntasGlobales.getInstance().sacarPreguntaRequerida(tipoAnimal.getTipo());
    final List<Pregunta> preguntas = RepositorioPreguntasGlobales.getInstance().getListaDePreguntasRequeridas();

    assertEquals(1, preguntas.size());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void seCargaPorCascadaLaPreguntaNoRequeridaDeLaAsociacion() {
    final List<Pregunta> preguntas = entityManager().createQuery("from Pregunta").getResultList();

    assertEquals(3, preguntas.size());
  }

  @Test
  public void filtrarUsuarioPorCodigoQR() {
    UsuarioDuenio usuarioABuscar = duenioConDosMascotas();
    UsuarioDuenio buscado = repoUsuarios.usuarioConQR("1234");
    assertEquals(usuarioABuscar.getCodigoQR(), buscado.getCodigoQR());
  }


}








