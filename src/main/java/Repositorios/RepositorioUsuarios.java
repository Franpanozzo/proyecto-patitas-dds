package Repositorios;

import Notificacion.FormaDeNotificar;
import Notificacion.Notificador;
import Publicaciones.PublicacionAdopcionMascota;
import Usuario.*;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;
import java.util.stream.Collectors;

//No es un singleton
public class RepositorioUsuarios implements WithGlobalEntityManager{
  Notificador notificador = new Notificador();

  @SuppressWarnings("unchecked")
  public void cargarNuevoUsuario(Usuario usuarioNuevo) {
    entityManager().persist(usuarioNuevo);
  }
  @SuppressWarnings("unchecked")
  public List<Usuario> getlistaDeUsuarios() {
    return entityManager()
        .createQuery("from Usuario")
        .getResultList();
  }

  public void buscarDuenioYNotificar(String codigoQR, String nombreAsociacion) {
    Usuario usuario = this.usuarioConQR(codigoQR);
    this.notificarEncuentro(usuario, nombreAsociacion);
  }

  public UsuarioDuenio usuarioConQR(String codigoQR) {
    return (UsuarioDuenio) entityManager()
        .createQuery("from Usuario where codigoQR = :codigo")
        .setParameter("codigo", codigoQR)
        .getSingleResult();
  }

  private void notificarEncuentro(Usuario usuario, String nombreAsociacion) {
    DatoDeContacto algunContactoDelUsuario = usuario.getDatoDeContactoList().stream().findAny().get();
    notificador.notificarEncuentro(usuario,nombreAsociacion,algunContactoDelUsuario);
  }

  public void notificarRescatista(DatoDeContacto mailRescatista, String mailDuenioNoRegistrado) {
    notificador.notificarRescatista(mailRescatista, mailDuenioNoRegistrado);
  }
  //Hacer generica estas dos funciones

  public void notificarDuenioActual(DatoDeContacto duenioActual, String mailAdoptador) {
    notificador.notificarDuenioActual(duenioActual, mailAdoptador);
  }

  public void notificarPublicacionCreada(DatoDeContacto futuroAdoptante) {
    notificador.notidicarPublicacionCreada(futuroAdoptante);
  }

  public void enviarRecomendacion(DatoDeContacto datoDeContactoInteresado, List<PublicacionAdopcionMascota> publicacionesQueCumplen) {
    notificador.enviarRecomendacion(datoDeContactoInteresado, publicacionesQueCumplen);
  }

  public void setformaDeNotificar(FormaDeNotificar formaDeNotificar) {
    notificador.setformaDeNotificar(formaDeNotificar);
  }
}





