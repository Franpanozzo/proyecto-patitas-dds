package Asociacion;

import Mascota.Coordenadas;
import Mascota.MascotaPerdida;
import Notificacion.FormaDeNotificar;
import Repositorios.RepositorioUsuarios;
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
    RepositorioUsuarios RepositorioUsuarios;
    Coordenadas direccion;

    public Asociacion(String nombreAsociacion, Coordenadas direccion) {
        this.nombreAsociacion = nombreAsociacion;
        this.RepositorioUsuarios = new RepositorioUsuarios();
        this.direccion = direccion;
    }

    //Metodo para implementar MOCKITO
    public void cambiarFormaDeNotificar(FormaDeNotificar mail) {
        RepositorioUsuarios.setformaDeNotificar(mail);
    }

    public void agregarCarateristica(String caracteristica) {
        caracteristicasPosibles.add(caracteristica);
    }

    public RepositorioUsuarios getGestorDeAsociacion() {
        return RepositorioUsuarios;
    }

    public void registrarUsuario(Usuario usuarioNuevo) {
        RepositorioUsuarios.cargarNuevoUsuario(usuarioNuevo);
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
        RepositorioUsuarios.buscarDuenioYNotificar(codigoQR, nombreAsociacion);
    }

    public void registrarPublicacion(Publicacion publicacion) {
        listaDePublicaciones.add(publicacion);
    }

    public List<Publicacion> publicacionesValidadas() {
        return listaDePublicaciones.stream().filter(publicacion -> publicacion.validada()).collect(Collectors.toList());
    }

    //
    public void aprobarPublicacion(List<Publicacion> listaDePublicaciones) {
        listaDePublicaciones.forEach(publicacion -> publicacion.validar());
    }

    public List<Publicacion> getListaDePublicaciones() {
        return listaDePublicaciones;
    }

    public void encuentroDeMascotaEnPublicacion(Publicacion publicacionElegida, String emailSupuestoDuenio) {
        List<DatoDeContacto> datosDeContRescatista = publicacionElegida.getDatoDeContactoDelRescatista();
        DatoDeContacto algunContactoDelRescatista = datosDeContRescatista.stream().findAny().get();

        this.quitarPublicacion(publicacionElegida);
        RepositorioUsuarios.notificarRescatista(algunContactoDelRescatista, emailSupuestoDuenio);
        // Coordina entrega con el siguiente mail: tataa
    }


}
