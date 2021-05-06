package Asociacion;

import Mascota.MascotaPerdida;
import SeguridadAlternativa.Validaciones;
import Usuario.Usuario;
//import jdk.vm.ci.meta.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Asociacion {
    List<MascotaPerdida> mascotasEncontradasEnCalleList = new ArrayList<>();
    List<String> caracteristicasPosibles = new ArrayList<>();
    RepositorioUsuarios usuariosRegistrados;
    //Validaciones validacionesAsociacion;

    public void cargarMascota(MascotaPerdida mascota) {
        mascotasEncontradasEnCalleList.add(mascota);
    }

    public void agregarCarateristica(String caracteristica) {
        caracteristicasPosibles.add(caracteristica);
    }

    public RepositorioUsuarios getUsuariosRegistrados() {
        return usuariosRegistrados;
    }

    public void registrarUsuario(Usuario usuarioNuevo){
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

    public List<MascotaPerdida> obtenerMascotasDeLosUltimosDias() {
        LocalDate fechaMin = LocalDate.now().minusDays(10);
        return mascotasEncontradasEnCalleList.stream().
                filter(mascota -> mascota.encontradaDespuesDe(fechaMin)).
                collect(Collectors.toList());
    }

    public boolean esCaractPosible(String caracteristica) {
        return caracteristicasPosibles.contains(caracteristica);
    }

    public Asociacion() {
        //this.validacionesAsociacion = new Validaciones();
        this.usuariosRegistrados = new RepositorioUsuarios();
    }
    /*
    public String validarContrasenia(String contrasenia, String usuario) {
        return validacionesAsociacion.validarContrasenia(contrasenia, usuario);
    }*/

}
