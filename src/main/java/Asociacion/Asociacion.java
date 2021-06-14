package Asociacion;

import Mailer.JavaMail;
import Mascota.Coordenadas;
import Mascota.MascotaPerdida;
import Notificacion.FormaDeNotificar;
import Notificacion.NotificarPorJavaMail;
import Repositorios.GestorDeAsociacion;
import Usuario.*;
import Usuario.DatoDeContacto;
//import jdk.vm.ci.meta.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Asociacion {
    String nombreAsociacion;
    List<String> caracteristicasPosibles = new ArrayList<>();
    List<Publicacion> listaDePublicaciones = new ArrayList<>();
    GestorDeAsociacion GestorDeAsociacion;
    Coordenadas direccion;

    public Asociacion(String nombreAsociacion, Coordenadas direccion) {
        this.nombreAsociacion = nombreAsociacion;
        this.GestorDeAsociacion = new GestorDeAsociacion();
        this.direccion = direccion;
    }

    //Metodo para implementar MOCKITO
    public void cambiarMail(FormaDeNotificar mail) {
        GestorDeAsociacion.setformaDeNotificar(mail);
    }

    public void agregarCarateristica(String caracteristica) {
        caracteristicasPosibles.add(caracteristica);
    }

    public GestorDeAsociacion getGestorDeAsociacion() {
        return GestorDeAsociacion;
    }

    public void registrarUsuario(Usuario usuarioNuevo) {
        GestorDeAsociacion.cargarNuevoUsuario(usuarioNuevo);
    }

    public List<String> getCaracteristicasPosibles() {
        return caracteristicasPosibles;
    }

    public void quitarPublicacion(Publicacion publicacion) {
       listaDePublicaciones.remove(publicacion);
    }

    public List<Publicacion> obtenerPublicacionesDeLosUltimosDias() {
        LocalDate fechaMin = LocalDate.now().minusDays(10);
        return this.publicacionesValidadas().stream().
            filter(publi -> publi.encontradaDespuesDe(fechaMin)).
            collect(Collectors.toList());
    }

    public double distanciaALugarDeEncuentro(MascotaPerdida mascotaPerdida) {
        return mascotaPerdida.distanciaAEncuentro(direccion);
    }

    public void buscarDuenioYNotificar(String codigoQR) {
        GestorDeAsociacion.buscarDuenioYNotificar(codigoQR, nombreAsociacion);
    }

    public void registrarPublicacion(Publicacion publicacion) {
        listaDePublicaciones.add(publicacion);
    }

    public List<Publicacion> publicacionesValidadas() {
        return listaDePublicaciones.stream().filter(publicacion -> publicacion.validada()).collect(Collectors.toList());
    }

    public void aprobarPublicaciones() {
        listaDePublicaciones.forEach(publicacion -> publicacion.validar());
    }

    public List<Publicacion> getListaDePublicaciones() {
        return listaDePublicaciones;
    }

    public void encuentroDeMascotaEnPublicacion(Publicacion publicacionElegida, String emailSupuestoDuenio) {
        List<DatoDeContacto> datosDeContRescatista = publicacionElegida.getDatoDeContactoDelRescatista();
        DatoDeContacto algunContactoDelRescatista = datosDeContRescatista.stream().findAny().get();

        this.quitarPublicacion(publicacionElegida);
        GestorDeAsociacion.notificarRescatista(algunContactoDelRescatista, emailSupuestoDuenio);
        // Coordina entrega con el siguiente mail: tataa
    }


}
