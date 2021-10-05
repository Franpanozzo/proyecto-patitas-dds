package Notificacion;

import Publicaciones.PublicacionAdopcionMascota;
import Usuario.Usuario;
import Usuario.DatoDeContacto;

import java.util.List;
import java.util.stream.Collectors;

public class Notificador {
  FormaDeNotificar formaDeNotificar = new NotificarPorJavaMail();

  //Set para mockear
  public void setformaDeNotificar(FormaDeNotificar formaDeNotificar) {
    this.formaDeNotificar = formaDeNotificar;
  }

  public void notificarEncuentro(Usuario usuario, String nombreAsociacion, DatoDeContacto algunContactoDelUsuario) {
    formaDeNotificar.enviarNotificacion(algunContactoDelUsuario, "AVISO DE ENCUENTRO DE MASCOTA",
        "Buenos dias, encontramos a la mascota que perdio tu familiar " + usuario.getNombreYApellido()
            + ""
            + "\nContactanos lo antes posible con la asociacion " + nombreAsociacion + " para acordar el punto de entrega");
  }

  public void notificarRescatista(DatoDeContacto mailRescatista, String mailDuenioNoRegistrado) {
    formaDeNotificar.enviarNotificacion(mailRescatista, "AVISO DE ENCUENTRO DE MASCOTA",
        "Buenos dias, el dueño de la mascota que encontraste quiere coordinar un punto de encuentro."
            + "\nContactate con el lo antes posible: " + mailDuenioNoRegistrado);
  }

  public void notificarDuenioActual(DatoDeContacto duenioActual, String mailAdoptador) {
    formaDeNotificar.enviarNotificacion(duenioActual, "AVISO DE ADOPCION",
        "Buenos dias, alguien quiere adoptar tu mascota."
            + "\nContactate con el lo antes posible: " + mailAdoptador);
  }

  public void notidicarPublicacionCreada(DatoDeContacto futuroAdoptante) {
    formaDeNotificar.enviarNotificacion(futuroAdoptante, "SU PUBLICACION SE CREO CORRECTAMENTE",
        "Buenos dias, su publicacion de intencion de adopcion fue creada con éxito \nPuede darla de baja haciendo click aquí https://patitas.com/dar_de_baja_publicacion");
  }

  public void enviarRecomendacion(DatoDeContacto datoDeContactoInteresado, List<PublicacionAdopcionMascota> publicacionesQueCumplen) {
    formaDeNotificar.enviarNotificacion(datoDeContactoInteresado, "RECOMENDACION DE MASCOTAS EN ADOPCION",
        "Buenos dias, te sugerimos que veas las siguientes publicacion que creamos que pueden ser ideales para ti: " + this.agarrarLinks(publicacionesQueCumplen)
            + ""
            + "\n Saludos! Proyecto patitas.");
  }

  public List<String> agarrarLinks(List<PublicacionAdopcionMascota> publicacionAdopcionMascotas) {
    return publicacionAdopcionMascotas.stream().map(PublicacionAdopcionMascota::getLink).collect(Collectors.toList());
  }
}
