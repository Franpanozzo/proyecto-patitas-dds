package Seguridad;

import Exceptions.ContraseniaInvalidaException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class ValidacionListaContrasenia implements ValidacionContra{
  List<String> listaConstrasenias = leerArchivo("passwordlist.txt");

  public static List<String> leerArchivo(String archivo) {

    List<String> lineas = Collections.emptyList();
    Path pathArchivo = Paths.get("src/main/java/Seguridad").toAbsolutePath().resolve(archivo);
    try {
      lineas = Files.readAllLines(pathArchivo, StandardCharsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return lineas;
  }

  @Override
  public boolean validar(String contrasenia, String nombreUsuario) {
    return listaConstrasenias.contains(contrasenia);
  }

  @Override
  public String mensajeError() {
    return "La contrasenia pertenece a las 10.000 mas usadas";
  }
}
