package server;

import domain.Asociacion.Asociacion;
import domain.Mascota.Animal;
import domain.Mascota.Coordenadas;
import domain.Mascota.Mascota;
import domain.Mascota.Sexo;
import domain.Repositorios.RepositorioAsociaciones;
import domain.Repositorios.RepositorioUsuarios;
import domain.Usuario.*;

import java.time.LocalDate;
import java.util.*;

public class Bootstrap {
  static LocalDate fechaAntigua = LocalDate.of(1999, 05, 23);
  static Asociacion patitas = new Asociacion("Patitas",new Coordenadas(52.5244444,13.410555555555552));;

  public static void init() {
    //Aca se inicializan todos los datos en la base de datos

    RepositorioAsociaciones.getInstance().agregarAsociacion(patitas);
    usuarios().forEach(usuario -> RepositorioUsuarios.getInstance().cargarNuevoUsuario(usuario));

  }

  public static List<Usuario> usuarios() {
   return Arrays.asList(duenioConDosMascotas(), usuarioAdmin());
  }

  public static UsuarioDuenio duenioConDosMascotas() {
    DatoDeContacto datosDeContactoPepe = new DatoDeContacto("juliaGonzales", 1140520843, "francisco.panozzosf@gmail.com");
    DatosPersonales datosPersonalesPepe = new DatosPersonales("Pepe Guardiola", fechaAntigua, TipoDocumento.DNI, 20149687);
    UsuarioDuenio pepe = new UsuarioDuenio("pepe12",
        "ADr731xsqz",
        patitas,
        datosPersonalesPepe,
        Collections.singletonList(datosDeContactoPepe),
        "1234");

    Mascota oli = oli();
    Mascota bombon = bombon();
    Map<String, String> caracOli = new HashMap<>();
    caracOli.put("RAZA", "Caniche");
    caracOli.put("COLOR", "Gris");
    Map<String, String> carcBombon = new HashMap<>();
    carcBombon.put("RAZA", "Labrador");
    carcBombon.put("TIPODEPELO", "Rulos");
    pepe.registrarMascota(oli, caracOli);
    pepe.registrarMascota(bombon, carcBombon);

    return pepe;
  }

  public static UsuarioAdministrador usuarioAdmin() {
    DatosPersonales datosPersonales = new DatosPersonales("FranPanozzo", fechaAntigua, TipoDocumento.DNI, 40122287);
    return new UsuarioAdministrador("franpano", "sofilamejR24", patitas, datosPersonales);
  }

  public static Mascota oli() {
    return new Mascota(Animal.PERRO, "olivia", "oli", 12, Sexo.HEMBRA, "educada", "foto" /*Collections.singletonList("gris")*/);
  }

  public static Mascota bombon() {
    return new Mascota(Animal.PERRO, "bombon", "bombi", 15, Sexo.HEMBRA, "labrador", "foto" /*caracteristicasBombon*/);
  }
}
