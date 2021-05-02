package Asociacion;

import EntidadesExternas.Rescatista;
import Mascota.MascotaPerdida;
import Usuario.Usuario;
import jdk.vm.ci.meta.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Asociacion {
    List<Usuario> usuariosRegistrados = new ArrayList<>();
    List<MascotaPerdida> mascotasEncontradasEnCalleList = new ArrayList<>();
    List<String> caracteristicasPosibles = new ArrayList<>();

    private static Asociacion instance = new Asociacion();

    private Asociacion() {}

    public static Asociacion getInstance() {
        return instance;
    }

    public void cargarMascota(MascotaPerdida mascota){
        mascotasEncontradasEnCalleList.add(mascota);
    }

    public void agregarCarateristica(String caracteristica) {
        caracteristicasPosibles.add(caracteristica);
    }

    public static void registrarUsuario(){

    }

    public void quitarMascota(MascotaPerdida mascotaPerdida) {
        mascotasEncontradasEnCalleList.remove(mascotaPerdida);
    }

    public List<MascotaPerdida> obtenerMascotasDeLosUltimosDias(){
        LocalDate date = LocalDate.now().minusDays(10);
        return mascotasEncontradasEnCalleList.stream().
                filter(mascota -> mascota.encontradaEnFecha(date)).
                collect(Collectors.toList());
    }

    public boolean esCaractPosible(String caracteristica) {
    return caracteristicasPosibles.contains(caracteristica);
    }


}
