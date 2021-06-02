package Servicios.Hogares;
import Mascota.Animal;
import Mascota.Coordenadas;
import Mascota.Tamanio;
import Mascota.DatosMascotaPerdida;

import java.util.ArrayList;
import java.util.List;

public class Hogar {
  String id;
  String nombre;
  Ubicacion ubicacion;
  String telefono;
  Admision admisiones;
  Integer capacidad;
  Integer lugares_disponibles;
  boolean patio;
  List<String> caracteristicas = new ArrayList<>();

  public boolean aceptaAnimal(DatosMascotaPerdida datosMascota) {
    if (datosMascota.getAnimal().equals(Animal.PERRO) && admisiones.getPerros()) {
      return true;
    }
    if (datosMascota.getAnimal().equals(Animal.GATO) && admisiones.getGatos()) {
      return true;
    }
    return false;
  }

  public boolean tieneDisponibilidad() {
    return !lugares_disponibles.equals(0);
  }

  public boolean aceptaSegunPatio(DatosMascotaPerdida datosMascota) {
    if (patio && (datosMascota.getTamanio().equals(Tamanio.GRANDE) || datosMascota.getTamanio().equals(Tamanio.MEDIANA))) {
      return true;
    }
    if (!patio && datosMascota.getTamanio().equals(Tamanio.CHICA)) {
      return true;
    }
    return false;
  }

  public boolean estaDentroRadio(double radio, DatosMascotaPerdida datosMascota) {

    return datosMascota.getLugarDeEncuentro().distanciaA(new Coordenadas(ubicacion.getLatitud(), ubicacion.getLongitud())) <= radio;
  }

  public boolean cumpleCaracteristicaPuntual(DatosMascotaPerdida datosMascota) {
    return caracteristicas.stream().allMatch(unaCarac -> datosMascota.getDescripcionEstado().contains(unaCarac));
  }

  public String getNombre() {
    return nombre;
  }
}