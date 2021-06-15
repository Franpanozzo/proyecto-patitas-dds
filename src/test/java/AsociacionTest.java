import Asociacion.*;
import Notificacion.FormaDeNotificar;
import Notificacion.NotificarPorJavaMail;
import Repositorios.RepositorioUsuarios;
import Servicios.Hogares.*;

import Exceptions.*;
import FormasDeEncuentro.*;
import Repositorios.RepositorioAsociaciones;
import Servicios.Hogares.Hogar;
import Servicios.Hogares.ListaDeHogares;
import Servicios.Hogares.ServicioHogares;
import Usuario.*;
import Mascota.*;
import EntidadesExternas.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.LocalDate;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class AsociacionTest {

    List<String> caracteristicasBombon = Arrays.asList("marron", "grande");
    LocalDate fechaUnMesAtras = LocalDate.now().minusDays(29);
    LocalDate fechaAntigua = LocalDate.of(1999, 05, 23);
    LocalDate fechaActual = LocalDate.now();
    Asociacion patitas;
    static Asociacion garritas = new Asociacion("Garritas",new Coordenadas(12.5578234,9.086421783546927));
    static Asociacion colitas = new Asociacion("Colitas", new Coordenadas(90.62036402,2.362539475273947));
    RepositorioUsuarios repoUsuarios;
    //Asociacion masCercanaAOli;
    //Asociacion masCercanaALasMascotas;
    UsuarioVoluntario sofi;
    UsuarioVoluntario juli;
    Rescatista franB;
    Rescatista facu;
    MascotaPerdida wendy;
    MascotaPerdida murri;
    MascotaPerdida milton;
    MascotaPerdida millo;
    Publicacion publiWendy;
    Publicacion publiMurri;
    Publicacion publiMilton;
    Publicacion publiMillo;
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



    @BeforeAll
    static void iniciarPreTodo() throws IOException {
        lista1 = new ListaDeHogares(Collections.singletonList(hogarSantaAna));
        lista2 = new ListaDeHogares(Collections.singletonList(laPataLoca));
        lista3 = new ListaDeHogares(Collections.singletonList(hogarSantaTeresita));
        lista4 = new ListaDeHogares(Collections.singletonList(hogarSantaMonica));

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
        patitas = new Asociacion("Patitas",new Coordenadas(52.5244444,13.410555555555552));
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
        this.publiWendy = new Publicacion( wendy.getDatosMascotaPerdida(), franB.getContacto());
        //this.publiMurri = new Publicacion(new DatosMascotaPerdida(franB,"foto", Collections.singletonList("perra perdida"),new Coordenadas(52.5244444, 13.410555555555556), fechaActual, Animal.PERRO, Tamanio.CHICA));
        //this.publiMilton = new Publicacion(new DatosMascotaPerdida(facu,"foto", Collections.singletonList("perra perdida"),new Coordenadas(52.5244444, 13.410555555555556), fechaActual, Animal.PERRO, Tamanio.CHICA));
        //this.publiMillo = new Publicacion(new DatosMascotaPerdida(facu,"foto", Collections.singletonList("perra perdida"),new Coordenadas(52.5244444, 13.410555555555556), fechaUnMesAtras, Animal.PERRO, Tamanio.GRANDE));
    }

    @AfterEach
    public void despuesDeCada() {
        RepositorioAsociaciones.getInstance().sacarAsociacion(patitas);
    }


    @Test
    public void rescatistaEncuentraMascotaSinChapitaYBuscaRefugioParaElla() throws IOException {
        franB.informarMascotaEncontrada(wendy, new SinChapita());
        assertTrue(franB.buscarHogares(100).contains(hogarSantaAna));
    }

    @Test
    public void rescatistaEncuentraPerroSinChapitaYBuscaRefugioParaEl() throws IOException {
        franB.informarMascotaEncontrada(murri, new SinChapita());
        assertTrue(franB.buscarHogares(100).contains(hogarSantaMonica));
    }

    @Test
    public void personaPuedeInformarUnPerroPerdidoConChapitaConMailMockito() {
        Rescatista franB = usuariosRescatista("franB");
        MascotaPerdida wendy = new MascotaPerdida(franB, "foto.png", Collections.singletonList("Manso"),new Coordenadas(42.5244444,12.410555555555552), fechaActual, Animal.PERRO, Tamanio.MEDIANA);
        wendy.setChapita(new Chapita("1234", patitas));
        UsuarioDuenio usuarioX = duenioConDosMascotas();
        franB.informarMascotaEncontrada(wendy, new ConChapita());
        Mockito.verify(notificacionFalsa, Mockito.only()).enviarNotificacion(Mockito.any(),Mockito.anyString(),Mockito.anyString());
    }

    @Test
    public void rescatistaPuedeInformarUnPerroPerdidoSinChapita() {
        franB.informarMascotaEncontrada(wendy, new SinChapita());
        assertEquals("fotoWendy.png", patitas.getListaDePublicaciones().get(0).getDatosMascotaPerdida().getFoto());
    }

    @Test
    public void personaEncuentraMascotaEnPubliYSistemaInformaRescatista() {
        patitas.encuentroDeMascotaEnPublicacion(publiWendy, "guillermin.felipettin@gmail.com");
        Mockito.verify(mailFalso, Mockito.only()).enviarMail(Mockito.any());
    }


    @Test
    public void crearUnUsuarioYRegistrarDosMascotas() {
        UsuarioDuenio pepe = duenioConDosMascotas();
        UsuarioAdministrador fran = usuarioAdmin();
        fran.agregarCaracteristica("RAZA");
        fran.agregarCaracteristica("COLOR");
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
        assertTrue(pepe.getMascotasList().contains(oli));
        assertTrue(pepe.getMascotasList().contains(bombon));
        assertTrue(repoUsuarios.getlistaDeUsuarios().contains(pepe));
    }

    @Test
    public void administradorPuedeAgregarFacilmenteCaracteristicaYEstaRegistradoEnElSistema() {
        UsuarioAdministrador fran = usuarioAdmin();
        fran.agregarCaracteristica("RAZA");
        fran.agregarCaracteristica("COLOR");
        fran.agregarCaracteristica("TIPO DE PELO");
        assertTrue(patitas.getCaracteristicasPosibles().contains("RAZA"));
        assertTrue(patitas.getCaracteristicasPosibles().contains("COLOR"));
        assertTrue(patitas.getCaracteristicasPosibles().contains("TIPO DE PELO"));
        assertTrue(repoUsuarios.getlistaDeUsuarios().contains(fran));
    }


    //publicacionesDeLasMascotasPerdidasEnLosUltimos10Dias

    @Test
    public void usuarioPuedeAprobarPublicacionesDeUnasCuantas(){
        franB.informarMascotaEncontrada(wendy, new SinChapita());
        franB.informarMascotaEncontrada(murri, new SinChapita());
        facu.informarMascotaEncontrada(millo, new SinChapita());
        facu.informarMascotaEncontrada(milton, new SinChapita());

        //Estas dos lineas se harian directamente en la UI
        List<Publicacion> publicacionesASeleccionadar = patitas.getListaDePublicaciones();
        publicacionesASeleccionadar.stream().filter(publicacion -> publicacion.getDatosMascotaPerdida().getFoto().equals("fotoWendy.png"));

        sofi.aprobarPublicaciones(publicacionesASeleccionadar);
        assertEquals("fotoWendy.png", patitas.obtenerPublicacionesDeLosUltimosDias().get(0).getDatosMascotaPerdida().getFoto());
    }

    @Test
    public void crearUsuarioContraseniasErroneas() {
        DatosPersonales datosPersonales = new DatosPersonales("FranPanozzo", fechaAntigua, TipoDocumento.DNI, 40122287);
        assertThrows(ContraseniaInvalidaException.class, () -> new UsuarioAdministrador("franpano", "12345", patitas, datosPersonales));
    }

    private MascotaPerdida mascotaPerdida(String foto,List<String> descripcion, LocalDate fecha, Rescatista rescatista, Animal animal, Tamanio tamanio) {
        return new MascotaPerdida(rescatista, foto, descripcion, new Coordenadas(-34.62072,-58.41820), fecha, animal, tamanio);
    }

    private Rescatista usuariosRescatista(String nombre) {
        DatoDeContacto datoDeContacto = new DatoDeContacto("facundofacu", 1130550832, "facundoivan10@gmail.com");
        DatosPersonales datosPersonales = new DatosPersonales(nombre, fechaAntigua, TipoDocumento.DNI, 40122287);
        return new Rescatista(datosPersonales, new Coordenadas(52.5244444, 13.410555555555556), Collections.singletonList(datoDeContacto));
    }

    private UsuarioAdministrador usuarioAdmin() {
        DatosPersonales datosPersonales = new DatosPersonales("FranPanozzo", fechaAntigua, TipoDocumento.DNI, 40122287);
        return new UsuarioAdministrador("franpano", "sofilamejR24", patitas, datosPersonales);
    }

    private Mascota oli() {
        return new Mascota(Animal.PERRO, "olivia", "oli", 12, Sexo.HEMBRA, "educada", "foto" /*Collections.singletonList("gris")*/);
    }

    private Mascota bombon() {
        return new Mascota(Animal.PERRO, "bombon", "bombi", 15, Sexo.HEMBRA, "labrador", "foto" /*caracteristicasBombon*/);
    }

    private UsuarioDuenio duenioConDosMascotas() {
        DatoDeContacto datosDeContactoPepe = new DatoDeContacto("juliaGonzales", 1140520843, "francisco.panozzosf@gmail.com");
        DatosPersonales datosPersonalesPepe = new DatosPersonales("Pepe Guardiola", fechaAntigua, TipoDocumento.DNI, 20149687);
        return new UsuarioDuenio("pepe12",
                "ADr731xsqz",
                patitas,
                datosPersonalesPepe,
                Collections.singletonList(datosDeContactoPepe),
                "1234");
    }

}