import Asociacion.Asociacion;
import EntidadesExternas.Rescatista;
import Mascota.*;
import Notificacion.FormaDeNotificar;
import Publicaciones.PublicacionAdopcionMascota;
import Publicaciones.PublicacionIntencionAdopcion;
import Publicaciones.PublicacionMascotaPerdida;
import Repositorios.RepositorioAsociaciones;
import Repositorios.RepositorioUsuarios;
import Servicios.Hogares.*;
import Usuario.*;
import Utils.Pregunta;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class BaseTest {
  List<String> caracteristicasBombon = Arrays.asList("marron", "grande");
  LocalDate fechaUnMesAtras = LocalDate.now().minusDays(29);
  static LocalDate fechaAntigua = LocalDate.of(1999, 05, 23);
  LocalDate fechaActual = LocalDate.now();
  Asociacion patitas = new Asociacion("Patitas",new Coordenadas(52.5244444,13.410555555555552));;
  static Asociacion garritas = new Asociacion("Garritas",new Coordenadas(12.5578234,9.086421783546927));
  static Asociacion colitas = new Asociacion("Colitas", new Coordenadas(90.62036402,2.362539475273947));
  RepositorioUsuarios repoUsuarios;
  //Asociacion masCercanaAOli;
  //Asociacion masCercanaALasMascotas;
  UsuarioAdministrador franP = usuarioAdmin();
  UsuarioVoluntario sofi;
  UsuarioVoluntario juli;
  Rescatista franB;
  Rescatista facu;
  MascotaPerdida wendy;
  MascotaPerdida murri;
  MascotaPerdida milton;
  MascotaPerdida millo;
  PublicacionMascotaPerdida publiWendy;
  PublicacionMascotaPerdida publiMurri;
  PublicacionMascotaPerdida publiMilton;
  PublicacionMascotaPerdida publiMillo;
  static Pregunta necesitaPatio = new Pregunta("¿Nesecita patio la mascota?", "¿Tiene patio para la mascota?", "PATIO", Arrays.asList("SI", "NO", "GRANDE", "CHICO"));
  static Pregunta tipoDeTamanio = new Pregunta( "¿Que tamanio tiene su mascota?", "¿Que tamanio le gustaria que tenga su mascota?", "TAMANIO", Arrays.asList("CHIC", "MEDIANO", "GRANDE"));
  static Pregunta tipoAnimal = new Pregunta("¿Que animal es el que quiere dar en adopcion?", "¿Que animal quiere adoptar?",
      "ANIMAL", Arrays.asList("PERRO","GATO"));
  static Hogar hogarSantaAna = new Hogar("1","Santa Ana", new Ubicacion(), "42427652", new Admision(false, true),
      20, 2, Boolean.TRUE, Arrays.asList("Manso"));
  static Hogar hogarSantaTeresita = new Hogar("2","Santa Teresita", new Ubicacion(), "42427672", new Admision(false, false),
      20, 2, Boolean.TRUE, Arrays.asList("Tranquilo"));
  static Hogar laPataLoca = new Hogar("3","La pata loca", new Ubicacion(), "42417622", new Admision(false, false),
      20, 2, Boolean.TRUE, Arrays.asList("Tranquilo"));
  static Hogar hogarSantaMonica = new Hogar("4","Santa Monica", new Ubicacion(), "43427622", new Admision(true, true),
      20, 2, Boolean.FALSE, Arrays.asList("Tranquilo"));
  static ListaDeHogares lista1;
  static ListaDeHogares lista2;
  static ListaDeHogares lista3;
  static ListaDeHogares lista4;
  FormaDeNotificar notificacionFalsa;
  static DatoDeContacto datosHarry = new DatoDeContacto("HarryPotter", 1130558832, "harryRodriguez@gmail.com");
  static DatoDeContacto datosPepe = new DatoDeContacto("Pepe", 1138658832, "pepeRodriguez@gmail.com");
  static Map<String, String> datosPublicacion1 = new HashMap<String, String>(){{
      put("ANIMAL", "PERRO");
      put("TAMANIO", "CHICO");
      put("PATIO", "NO");
    }};
  static Map<String, String> datosPublicacion2 = new HashMap<String, String>(){{
    put("ANIMAL", "GATO");
    put("TAMANIO", "NULL");
    put("PATIO", "SI");
  }};

  static Map<String, String> datosPublicacion3 = new HashMap<String, String>(){{
    put("ANIMAL", "GATO");
    put("TAMANIO", "CHICO");
    put("PATIO", "SI");
  }};

  @BeforeAll
  public static void iniciarPreTodo() throws IOException {

    lista1 = new ListaDeHogares(40, Collections.singletonList(hogarSantaAna));
    lista2 = new ListaDeHogares(40,Collections.singletonList(laPataLoca));
    lista3 = new ListaDeHogares(40,Collections.singletonList(hogarSantaTeresita));
    lista4 = new ListaDeHogares(40,Collections.singletonList(hogarSantaMonica));

    ServicioHogares ServicioHogaresFalso = Mockito.mock(ServicioHogares.class);
    ObtenedorServicioHogares.cambiarServicio(ServicioHogaresFalso);
    Mockito.when(ServicioHogaresFalso.listadoDeHogares(1)).thenReturn(lista1);
    Mockito.when(ServicioHogaresFalso.listadoDeHogares(2)).thenReturn(lista2);
    Mockito.when(ServicioHogaresFalso.listadoDeHogares(3)).thenReturn(lista3);
    Mockito.when(ServicioHogaresFalso.listadoDeHogares(4)).thenReturn(lista4);
    //Aca agregarias a las demas cuando testeemos la api

  }

  @BeforeEach
  public void iniciarPreTest(){

    DatosPersonales datosPersonales = new DatosPersonales("FranPanozzo", fechaAntigua, TipoDocumento.DNI, 40122287);
    repoUsuarios = patitas.getGestorDeAsociacion();
    RepositorioAsociaciones.getInstance().agregarAsociacion(patitas);
    notificacionFalsa = Mockito.mock(FormaDeNotificar.class);
    patitas.cambiarFormaDeNotificar(notificacionFalsa);

    this.franB = usuariosRescatista("franB");
    this.facu = usuariosRescatista("facu");
    this.wendy = mascotaPerdida("fotoWendy.png", Collections.singletonList("Manso"), fechaActual, franB, Animal.GATO, Tamanio.MEDIANA);
    this.murri = mascotaPerdida("fotoMurri.png", Collections.singletonList("Tranquilo"), fechaActual, franB, Animal.PERRO, Tamanio.CHICA);
    this.milton = mascotaPerdida("fotoMilton.png", Collections.singletonList("Amistoso"), fechaActual, facu, Animal.PERRO, Tamanio.CHICA);
    this.millo = mascotaPerdida("fotoMillo.png", Collections.singletonList("Manso"), fechaUnMesAtras, facu , Animal.PERRO, Tamanio.GRANDE);
    sofi = new UsuarioVoluntario("sofiKpita","sofilamejR24",  patitas, datosPersonales);
    juli = new UsuarioVoluntario("juli","sofilamejR24",  patitas, datosPersonales);
    this.publiWendy = new PublicacionMascotaPerdida( wendy.getDatosMascotaPerdida(), franB.getContacto());
    franP.agregarPreguntaParaAdopcion(necesitaPatio);
    franP.agregarPreguntaParaAdopcion(tipoAnimal);
    franP.agregarPreguntaParaAdopcion(tipoDeTamanio);
    //this.publiMurri = new PublicacionMascotaPerdida(new DatosMascotaPerdida(franB,"foto", Collections.singletonList("perra perdida"),new Coordenadas(52.5244444, 13.410555555555556), fechaActual, Animal.PERRO, Tamanio.CHICA));
    //this.publiMilton = new PublicacionMascotaPerdida(new DatosMascotaPerdida(facu,"foto", Collections.singletonList("perra perdida"),new Coordenadas(52.5244444, 13.410555555555556), fechaActual, Animal.PERRO, Tamanio.CHICA));
    //this.publiMillo = new PublicacionMascotaPerdida(new DatosMascotaPerdida(facu,"foto", Collections.singletonList("perra perdida"),new Coordenadas(52.5244444, 13.410555555555556), fechaUnMesAtras, Animal.PERRO, Tamanio.GRANDE));
  }

  @AfterEach
  public void despuesDeCada() {
    RepositorioAsociaciones.getInstance().sacarAsociacion(patitas);
    franP.quitarPreguntaParaAdopcion(necesitaPatio);
    franP.quitarPreguntaParaAdopcion(tipoAnimal);
    franP.quitarPreguntaParaAdopcion(tipoDeTamanio);
  }

  public MascotaPerdida mascotaPerdida(String foto,List<String> descripcion, LocalDate fecha, Rescatista rescatista, Animal animal, Tamanio tamanio) {
    return new MascotaPerdida(rescatista, foto, descripcion, new Coordenadas(-34.62072,-58.41820), fecha, animal, tamanio);
  }

  public Rescatista usuariosRescatista(String nombre) {
    DatoDeContacto datoDeContacto = new DatoDeContacto("facundofacu", 1130550832, "facundoivan10@gmail.com");
    DatosPersonales datosPersonales = new DatosPersonales(nombre, fechaAntigua, TipoDocumento.DNI, 40122287);
    return new Rescatista(datosPersonales, new Coordenadas(52.5244444, 13.410555555555556), Collections.singletonList(datoDeContacto));
  }

  public UsuarioAdministrador usuarioAdmin() {
    DatosPersonales datosPersonales = new DatosPersonales("FranPanozzo", fechaAntigua, TipoDocumento.DNI, 40122287);
    return new UsuarioAdministrador("franpano", "sofilamejR24", patitas, datosPersonales);
  }

  public Mascota oli() {
    return new Mascota(Animal.PERRO, "olivia", "oli", 12, Sexo.HEMBRA, "educada", "foto" /*Collections.singletonList("gris")*/);
  }

  public Mascota bombon() {
    return new Mascota(Animal.PERRO, "bombon", "bombi", 15, Sexo.HEMBRA, "labrador", "foto" /*caracteristicasBombon*/);
  }

  public UsuarioDuenio duenioConDosMascotas() {
    DatoDeContacto datosDeContactoPepe = new DatoDeContacto("juliaGonzales", 1140520843, "francisco.panozzosf@gmail.com");
    DatosPersonales datosPersonalesPepe = new DatosPersonales("Pepe Guardiola", fechaAntigua, TipoDocumento.DNI, 20149687);
    return new UsuarioDuenio("pepe12",
        "ADr731xsqz",
        patitas,
        datosPersonalesPepe,
        Collections.singletonList(datosDeContactoPepe),
        "1234");
  }

  public PublicacionAdopcionMascota generarPublicacionAdopcionSimple(Map<String,String> datosPublicacion, DatoDeContacto contacto) {
    return new PublicacionAdopcionMascota(datosPublicacion,contacto);
  }

  public PublicacionIntencionAdopcion generarPublicacionIntencionAdopcionSimple(Map<String,String> datosPublicacion, DatoDeContacto contacto) {
    return new PublicacionIntencionAdopcion(datosPublicacion,contacto);
  }
}


