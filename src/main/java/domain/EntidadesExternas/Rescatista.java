package domain.EntidadesExternas;

import domain.FormasDeEncuentro.FormaDeEncuentro;
import domain.Hogares.Hogar;
import domain.Hogares.ObtenedorServicioHogares;
import domain.Usuario.DatoDeContacto;
import domain.Mascota.Coordenadas;
import domain.Mascota.MascotaPerdida;
import domain.Usuario.DatosPersonales;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Rescatista {
    DatosPersonales datosPersonales;
    Coordenadas direccion;
    List<DatoDeContacto> contacto = new ArrayList<DatoDeContacto>();
    MascotaPerdida mascotaPerdida;

    public Rescatista(DatosPersonales datosPersonales, Coordenadas direccion, List<DatoDeContacto> contacto) {
        this.datosPersonales = datosPersonales;
        this.direccion = direccion;
        this.contacto = contacto;
    }

    public void informarMascotaEncontrada(MascotaPerdida mascota, FormaDeEncuentro formaDeEncuentro) {
       formaDeEncuentro.ejecutarAccion(mascota,contacto);
       mascotaPerdida = mascota;
    }

    public List<Hogar> buscarHogares(double radio) throws IOException {
        //List<Hogar> listaDeHogares = ObtenedorServicioHogares.hogaresQueCumplan(radio,mascotaPerdida.getDatosMascotaPerdida());
        //System.out.println(listaDeHogares.stream().map(hogar -> hogar.getNombre()).collect(Collectors.toList()).toString());
        return ObtenedorServicioHogares.hogaresQueCumplan(radio,mascotaPerdida.getDatosMascotaPerdida());
    }

    public List<DatoDeContacto> getContacto() {
        return contacto;
    }
}









