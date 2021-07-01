import Publicaciones.PublicacionMascotaPerdida;
import Exceptions.*;
import FormasDeEncuentro.*;
import Usuario.*;
import Mascota.*;
import EntidadesExternas.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.IOException;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class AsociacionTest extends BaseTest{

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
        Mockito.verify(notificacionFalsa, Mockito.only()).enviarNotificacion(Mockito.any(),Mockito.anyString(),Mockito.anyString());
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
    public void usuarioPuedeAprobarPublicacionesDeUnasCuantas(){
        franB.informarMascotaEncontrada(wendy, new SinChapita());
        franB.informarMascotaEncontrada(murri, new SinChapita());
        facu.informarMascotaEncontrada(millo, new SinChapita());
        facu.informarMascotaEncontrada(milton, new SinChapita());

        //Estas dos lineas se harian directamente en la UI
        List<PublicacionMascotaPerdida> publicacionesASeleccionadar = patitas.getListaDePublicaciones();
        publicacionesASeleccionadar.stream().filter(publicacionMascotaPerdida -> publicacionMascotaPerdida.getDatosMascotaPerdida().getFoto().equals("fotoWendy.png"));

        sofi.aprobarPublicaciones(publicacionesASeleccionadar);
        assertEquals("fotoWendy.png", patitas.obtenerPublicacionesDeLosUltimosDias().get(0).getDatosMascotaPerdida().getFoto());
    }


    @Test
    public void crearUsuarioContraseniasErroneas() {
        DatosPersonales datosPersonales = new DatosPersonales("FranPanozzo", fechaAntigua, TipoDocumento.DNI, 40122287);
        assertThrows(ContraseniaInvalidaException.class, () -> new UsuarioAdministrador("franpano", "12345", patitas, datosPersonales));
    }

}