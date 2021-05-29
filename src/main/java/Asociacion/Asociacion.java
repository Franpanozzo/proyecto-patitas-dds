package Asociacion;

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
    List<MascotaPerdida> mascotasEncontradasEnCalleList = new ArrayList<>();
    List<String> caracteristicasPosibles = new ArrayList<>();
    List<Publicacion> listaDePublicaciones = new ArrayList<>();
    RepositorioUsuarios usuariosRegistrados;
    Coordenadas direccion;

    public void cargarMascota(MascotaPerdida mascota) {
        mascotasEncontradasEnCalleList.add(mascota);
    }

    public void agregarCarateristica(String caracteristica) {
        caracteristicasPosibles.add(caracteristica);
    }

    public RepositorioUsuarios getUsuariosRegistrados() {
        return usuariosRegistrados;
    }

    public void registrarUsuario(Usuario usuarioNuevo) {
        usuariosRegistrados.cargarNuevoUsuario(usuarioNuevo);
    }

    public List<String> getCaracteristicasPosibles() {
        return caracteristicasPosibles;
    }

    public List<MascotaPerdida> getMascotasEncontradasEnCalleList() {
        return mascotasEncontradasEnCalleList;
    }

    public void quitarMascota(MascotaPerdida mascotaPerdida) {
        mascotasEncontradasEnCalleList.remove(mascotaPerdida);
    }

    public List<Publicacion> obtenerPublicacionDeLosUltimosDias() {
        LocalDate fechaMin = LocalDate.now().minusDays(10);
        return this.publicacionesValidadas().stream().
            filter(publi -> publi.encontradaDespuesDe(fechaMin)).
            collect(Collectors.toList());
    }

    public Asociacion(Coordenadas direccion) {
        this.usuariosRegistrados = new RepositorioUsuarios();
        this.direccion = direccion;
    }

    public double distanciaALugarDeEncuentro(MascotaPerdida mascotaPerdida) {
        return mascotaPerdida.distanciaAEncuentro(direccion);
    }

    public void buscarDuenioYNotificar(String codigoQR) {
        usuariosRegistrados.buscarDuenioYNotificar(codigoQR);
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
}
