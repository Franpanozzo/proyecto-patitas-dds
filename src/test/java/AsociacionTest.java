import Asociacion.Asociacion;

import Exceptions.*;
import FormasDeEncuentro.ConChapita;
import FormasDeEncuentro.FormaDeEncuentro;
import Repositorios.RepositorioUsuarios;
import Usuario.*;
import Mascota.*;
import EntidadesExternas.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class AsociacionTest {

    List<String> caracteristicasBombon = Arrays.asList("marron", "grande");
    LocalDate fechaUnMesAtras = LocalDate.now().minusDays(29);
    LocalDate fechaAntigua = LocalDate.of(1999, 05, 23);
    LocalDate fechaActual = LocalDate.now();
    Asociacion patitas = new Asociacion(new Coordenadas(52.5244444,13.410555555555552));
    RepositorioUsuarios repoUsuarios = patitas.getUsuariosRegistrados();

    /*@BeforeAll
    public void iniciar() {
        Rescatista franB = usuariosRescatista("franB");
        MascotaPerdida wendy = new MascotaPerdida(franB, "foto.png", "Pelo largo",new Coordenadas(42.5244444,12.410555555555552), fechaActual);
        wendy.setChapita(new Chapita("1234", patitas));
    }*/

    @Test
    public void personaPuedeInformarUnPerroPerdidoConChapita() {
        Rescatista franB = usuariosRescatista("franB");
        MascotaPerdida wendy = new MascotaPerdida(franB, "foto.png", "Pelo largo",new Coordenadas(42.5244444,12.410555555555552), fechaActual);
        wendy.setChapita(new Chapita("1234", patitas));
        UsuarioDuenio usuarioX = duenioConDosMascotas();
        franB.informarMascotaEncontrada(wendy, new ConChapita());
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

    @Test
    public void personaPuedeInformarUnPerroPerdidoSinChapita() {

    }

    @Test
    public void listarMascotasPerdidas10Dias() {
       /*
        Rescatista franB = usuariosRescatista("franB");
        Rescatista facu = usuariosRescatista("facu");
        MascotaPerdida wendy = mascotaPerdida("Sola, asustada", fechaActual, franB);
        MascotaPerdida murri = mascotaPerdida("perra perdida", fechaActual, franB);
        MascotaPerdida milton = mascotaPerdida("perra perdida", fechaActual, facu);
        MascotaPerdida millo = mascotaPerdida("perra perdida", fechaUnMesAtras, facu);
        franB.informarMascotaEncontrada(wendy, Collections.singletonList(patitas));
        franB.informarMascotaEncontrada(murri, Collections.singletonList(patitas));
        facu.informarMascotaEncontrada(millo, Collections.singletonList(patitas));
        facu.informarMascotaEncontrada(milton, Collections.singletonList(patitas));
        List<MascotaPerdida> mascotasPerdidas = ...;
        assertEquals(Arrays.asList(wendy, murri, milton), );
        */
        
    }

    @Test
    public void crearUsuarioContraseniasErroneas() {
        assertThrows(ContraseniaInvalidaException.class, () -> new UsuarioAdministrador("franpano", "12345", patitas));
        /*UsuarioAdministrador usuarioNist1 = new UsuarioAdministrador().crearUsuario("usuarioNist1","as","Usuario Nist1", fechaAntigua, tipoDocumento.DNI,42842567, Collections.singletonList(datoFran("pepeGonzales",1140520743, "pepitogonzales@gmail.com")), patitas);
        UsuarioAdministrador usuarioNist2 = new UsuarioAdministrador().crearUsuario("usuarioNist2","usuarioNist2","Usuario Nist2", fechaAntigua, tipoDocumento.DNI,42842567, Collections.singletonList(datoFran("pepeGonzales",1140520743, "pepitogonzales@gmail.com")), patitas);;
        UsuarioAdministrador usuarioNist3 = new UsuarioAdministrador().crearUsuario("usuarioNist3","AAAAAAAAAA","Usuario Nist3", fechaAntigua, tipoDocumento.DNI,42842567, Collections.singletonList(datoFran("pepeGonzales",1140520743, "pepitogonzales@gmail.com")), patitas);
        UsuarioAdministrador usuarioNist4 = new UsuarioAdministrador().crearUsuario("usuarioNist4","123456789","Usuario Nist4", fechaAntigua, tipoDocumento.DNI,42842567, Collections.singletonList(datoFran("pepeGonzales",1140520743, "pepitogonzales@gmail.com")), patitas);;
        UsuarioAdministrador usuarioNist5 = new UsuarioAdministrador().crearUsuario("usuarioNist5","987654321","Usuario Nist5", fechaAntigua, tipoDocumento.DNI,42842567, Collections.singletonList(datoFran("pepeGonzales",1140520743, "pepitogonzales@gmail.com")), patitas);;
        UsuarioAdministrador usuarioNist6 = new UsuarioAdministrador().crearUsuario("usuarioNist6","aspoweqrrs","Usuario Nist6", fechaAntigua, tipoDocumento.DNI,42842567, Collections.singletonList(datoFran("pepeGonzales",1140520743, "pepitogonzales@gmail.com")), patitas);;
        */
    }

    private MascotaPerdida mascotaPerdida(String descripcion, LocalDate fecha, Rescatista rescatista) {
        return new MascotaPerdida(rescatista, "foto", descripcion, new Coordenadas(52.5244444, 13.410555555555556), fecha);
    }

    private Rescatista usuariosRescatista(String nombre) {
        DatoDeContacto datoDeContacto = new DatoDeContacto("facundofacu", 1130550832, "facuelmejor@gmail.com");
        DatosPersonales datosPersonales = new DatosPersonales(nombre, fechaAntigua, TipoDocumento.DNI, 40122287);
        return new Rescatista(datosPersonales, new Coordenadas(52.5244444, 13.410555555555556), Collections.singletonList(datoDeContacto));
    }

    private UsuarioAdministrador usuarioAdmin() {
        return new UsuarioAdministrador("franpano", "sofilamejR24", patitas);
    }

    private Mascota oli() {
        return new Mascota(Animal.PERRO, "olivia", "oli", 12, Sexo.HEMBRA, "educada", "foto" /*Collections.singletonList("gris")*/);
    }

    private Mascota bombon() {
        return new Mascota(Animal.PERRO, "bombon", "bombi", 15, Sexo.HEMBRA, "labrador", "foto" /*caracteristicasBombon*/);
    }

    private UsuarioDuenio duenioConDosMascotas() {
        DatoDeContacto datosDeContactoPepe = new DatoDeContacto("juliaGonzales", 1140520843, "francisco.panozzosf@gmail.com");
        DatosPersonales datosPersonalesPepe = new DatosPersonales("Pep", fechaAntigua, TipoDocumento.DNI, 20149687);
        return new UsuarioDuenio("pepe12",
                "ADr731xsqz",
                patitas,
                datosPersonalesPepe,
                Collections.singletonList(datosDeContactoPepe),
                "1234");
    }

}

