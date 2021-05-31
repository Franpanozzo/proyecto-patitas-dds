package Servicios.Hogares;

import java.util.ArrayList;
import java.util.List;

public class Hogar {
  String id;
  String nombre;
  Ubicacion ubicacion;
  String telefono;
  boolean perros;
  boolean gatos;
  Integer capacidad;
  Integer lugares_disponibles;
  boolean patio;
  List<String> caracteristicas = new ArrayList<>();

}
