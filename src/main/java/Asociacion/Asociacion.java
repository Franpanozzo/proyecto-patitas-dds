package Asociacion;

import Mailer.JavaMail;
import Mascota.Coordenadas;
import Mascota.MascotaPerdida;
import Repositorios.RepositorioUsuarios;
import Usuario.Usuario;
//import jdk.vm.ci.meta.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Asociacion {
    List<String> caracteristicasPosibles = new ArrayList<>();
    List<Publicacion> listaDePublicaciones = new ArrayList<>();
    RepositorioUsuarios repoUsuariosRegistrados;
    Coordenadas direccion;

    public Asociacion(Coordenadas direccion) {
        this.repoUsuariosRegistrados = new RepositorioUsuarios();
        this.direccion = direccion;
    }

    //Metodo para implementar MOCKITO
    public void cambiarMail(JavaMail mail) {
        repoUsuariosRegistrados.setMail(mail);
    }

    public void agregarCarateristica(String caracteristica) {
        caracteristicasPosibles.add(caracteristica);
    }

    public RepositorioUsuarios getRepoUsuariosRegistrados() {
        return repoUsuariosRegistrados;
    }

    public void registrarUsuario(Usuario usuarioNuevo) {
        repoUsuariosRegistrados.cargarNuevoUsuario(usuarioNuevo);
    }

    public List<String> getCaracteristicasPosibles() {
        return caracteristicasPosibles;
    }

    /*
    public void quitarPublicacion(Publicacion publicacion) {
       listaDePublicaciones.remove(publicacion);
    }
    */

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
        repoUsuariosRegistrados.buscarDuenioYNotificar(codigoQR);
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
}
