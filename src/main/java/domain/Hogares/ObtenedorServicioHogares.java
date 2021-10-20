package domain.Hogares;

import domain.Mascota.DatosMascotaPerdida;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ObtenedorServicioHogares {
  static List<Hogar> listaHogares = new ArrayList<>();
  static ServicioHogares servicio = ServicioHogares.getInstance();
  //List<ListaDeHogares> ListaDeListaDeHogares = new ArrayList<>();

  public static void setAllHogares() throws IOException {
    ListaDeHogares listaVariable = servicio.listadoDeHogares(1);

    agregarHogares(listaVariable.getListaHogares());

    int paginasRestantes = listaVariable.getTotal() / 10;

    for(int i=2;i<=paginasRestantes;i++) {
      //System.out.println(listaVariable.getTotal().toString() + listaVariable.getOffset().toString() + listaVariable.getListaHogares().toString());
      agregarHogares(servicio.listadoDeHogares(i).getListaHogares());
    }
  }

  public static void cambiarServicio(ServicioHogares nuevoServ) {
    servicio = nuevoServ;
  }

  public static void agregarHogares(List<Hogar> listaHogares) {
    ObtenedorServicioHogares.listaHogares.addAll(listaHogares);
  }

  public static List<Hogar> hogaresQueCumplan(double radio, DatosMascotaPerdida datosMascota) throws IOException {
    setAllHogares();
    return listaHogares.stream()
        .filter(h -> h.estaDentroRadio(radio, datosMascota))
        .filter(h -> h.tieneDisponibilidad())
        .filter(h -> h.aceptaAnimal(datosMascota))
        .filter(h -> h.aceptaSegunPatio(datosMascota))
        .filter(h -> h.cumpleCaracteristicaPuntual(datosMascota))
        .collect(Collectors.toList());
  }
}
