package Asociacion;

import EntidadesExternas.Rescatista;
import Mascota.MascotaPerdida;
import Usuario.Usuario;
//import jdk.vm.ci.meta.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Asociacion {
    List<Usuario> usuariosRegistrados = new ArrayList<>();
    List<MascotaPerdida> mascotasEncontradasEnCalleList = new ArrayList<>();
    List<String> caracteristicasPosibles = new ArrayList<>();

    public void cargarMascota(MascotaPerdida mascota){
        mascotasEncontradasEnCalleList.add(mascota);
    }

    public void agregarCarateristica(String caracteristica) {
        caracteristicasPosibles.add(caracteristica);
    }

    public void registrarUsuario(Usuario usuarioNuevo){
        usuariosRegistrados.add(usuarioNuevo);
    }

    public List<Usuario> getUsuariosRegistrados() {
        return usuariosRegistrados;
    }

    public List<String> getCaracteristicasPosibles(){
        return caracteristicasPosibles;
    }

    public List<MascotaPerdida> getMascotasEncontradasEnCalleList() {
        return mascotasEncontradasEnCalleList;
    }

    public void quitarMascota(MascotaPerdida mascotaPerdida) {
        mascotasEncontradasEnCalleList.remove(mascotaPerdida);
    }

    public List<MascotaPerdida> obtenerMascotasDeLosUltimosDias(){
        LocalDate fechaMin = LocalDate.now().minusDays(10);
        return mascotasEncontradasEnCalleList.stream().
                filter(mascota -> mascota.encontradaDespuesDe(fechaMin)).
                collect(Collectors.toList());
    }

    public boolean esCaractPosible(String caracteristica) {
        return caracteristicasPosibles.contains(caracteristica);
    }


}
