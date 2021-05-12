import Asociacion.Asociacion;

import Exceptions.ContraseniaInvalidaException;
import Mascota.Mascota;
import Usuario.UsuarioDuenio;
import Usuario.DatoDeContacto;
import Usuario.UsuarioAdministrador;
import Usuario.TipoDocumento;
import Mascota.MascotaPerdida;
import Mascota.Coordenadas;
import Mascota.Animal;
import Mascota.Sexo;
import EntidadesExternas.Rescatista;
import Asociacion.RepositorioUsuarios;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class AsociacionTest {

    List<String> caracteristicasBombon = Arrays.asList("marron", "grande");
    LocalDate fechaUnMesAtras = LocalDate.now().minusDays(29);
    LocalDate fechaAntigua = LocalDate.of(1999, 05, 23);
    LocalDate fechaActual = LocalDate.now();
    Asociacion patitas = new Asociacion();
    RepositorioUsuarios repoUsuarios = patitas.getUsuariosRegistrados();

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
    public void personaPuedeInformarUnPerroPerdido() {
        Rescatista franB = usuariosRescatista("franB");
        MascotaPerdida wendy = mascotaPerdida("perra perdida", fechaActual, franB);
        franB.informarMascotaEncontrada(wendy, patitas);
        assertTrue(patitas.getMascotasEncontradasEnCalleList().contains(wendy));
    }

    @Test
    public void listarMascotasPerdidas10Dias() {
        Rescatista franB = usuariosRescatista("franB");
        Rescatista facu = usuariosRescatista("facu");
        MascotaPerdida wendy = mascotaPerdida("Sola, asustada", fechaActual, franB);
        MascotaPerdida murri = mascotaPerdida("perra perdida", fechaActual, franB);
        MascotaPerdida milton = mascotaPerdida("perra perdida", fechaActual, facu);
        MascotaPerdida millo = mascotaPerdida("perra perdida", fechaUnMesAtras, facu);
        franB.informarMascotaEncontrada(wendy, patitas);
        franB.informarMascotaEncontrada(murri, patitas);
        facu.informarMascotaEncontrada(millo, patitas);
        facu.informarMascotaEncontrada(milton, patitas);
        assertEquals(Arrays.asList(wendy, murri, milton), patitas.obtenerMascotasDeLosUltimosDias());
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
        return new MascotaPerdida(rescatista, "foto", descripcion, new Coordenadas("52° 31' 28'' N", " 13° 24' 38'' E"), fecha);
    }

    private Rescatista usuariosRescatista(String nombre) {
        return new Rescatista(nombre, fechaAntigua, "dni", 40122287, new Coordenadas("52° 31' 28'' N", " 13° 24' 38'' E"), Collections.singletonList(datoFran("facundofacu", 1130550832, "facuelmejor@gmail.com")));
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
        DatoDeContacto datosPepe = datoPepe("juliaGonzales", 1140520843, "jgonzales@gmail.com");
        return new UsuarioDuenio("pepe12",
                "ADr731xsqz",
                patitas,
                "Pep",
                fechaAntigua,
                TipoDocumento.DNI,
                20149687,
                Collections.singletonList(datosPepe));
    }

    private DatoDeContacto datoFran(String nombreYApellido, Integer telefono, String email) {
        return new DatoDeContacto(nombreYApellido, telefono, email);
    }

    private DatoDeContacto datoPepe(String nombreYApellido, Integer telefono, String email) {
        return new DatoDeContacto(nombreYApellido, telefono, email);
    }

}

