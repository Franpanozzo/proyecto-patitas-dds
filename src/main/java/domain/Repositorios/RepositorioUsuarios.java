package domain.Repositorios;

import domain.Notificacion.FormaDeNotificar;
import domain.Notificacion.Notificador;
import domain.Publicaciones.PublicacionAdopcionMascota;
import domain.Usuario.*;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import javax.persistence.NoResultException;
import java.util.List;

//No es un singleton
public class RepositorioUsuarios implements WithGlobalEntityManager{
  Notificador notificador = new Notificador();

  private static RepositorioUsuarios instance;


  public static RepositorioUsuarios getInstance() {
    if (instance == null) {
      instance = new RepositorioUsuarios();
    }
    return instance;
  }

  @SuppressWarnings("unchecked")
  public void cargarNuevoUsuario(Usuario usuarioNuevo) {
    entityManager().persist(usuarioNuevo);
  }

  public void modificarUsuario(Usuario usuario) {
    entityManager().merge(usuario);
  }

  @SuppressWarnings("unchecked")
  public List<Usuario> getlistaDeUsuarios() {
    return entityManager()
        .createQuery("from Usuario")
        .getResultList();
  }

  public void buscarDuenioYNotificar(String codigoQR, String nombreAsociacion) {
    Usuario usuario = this.usuarioConQR(codigoQR);
    System.out.println("EL USUARIO TIENE EL CODIGO: " + codigoQR);
    this.notificarEncuentro(usuario, nombreAsociacion);
  }

  public Usuario usuarioConNombre(String nombreUsuario) {
    try {
      return (Usuario) entityManager()
          .createQuery("from Usuario where nombreUsuario = :nombreUsuario")
          .setParameter("nombreUsuario", nombreUsuario)
          .getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  public Usuario borrarUsuarioConNombre(String nombreUsuario) {
    return (UsuarioDuenio) entityManager()
        .createQuery("delete from Usuario where nombreUsuario = :nombreUsuario")
        .setParameter("nombreUsuario", nombreUsuario)
        .getSingleResult();
  }

  public UsuarioDuenio usuarioConQR(String codigoQR) {
    return (UsuarioDuenio) entityManager()
        .createQuery("from Usuario where codigoQR = :codigo")
        .setParameter("codigo", codigoQR)
        .getSingleResult();
  }

  private void notificarEncuentro(Usuario usuario, String nombreAsociacion) {
    DatoDeContacto algunContactoDelUsuario = usuario.getDatoDeContactoList().stream().findAny().get();
    System.out.println("EL DATO DE CONT TIENE EL MAIL: " + algunContactoDelUsuario.getEmail());
    notificador.notificarEncuentro(usuario,nombreAsociacion,algunContactoDelUsuario);
  }


  public UsuarioAdministrador administradorConNombre(String nombreUsuario) {
    try {
      return (UsuarioAdministrador) entityManager()
          .createQuery("from Usuario where nombreUsuario = :nombreUsuario and tipo = :tipo")
          .setParameter("nombreUsuario", nombreUsuario)
          .setParameter("tipo", "A")
          .getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
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





