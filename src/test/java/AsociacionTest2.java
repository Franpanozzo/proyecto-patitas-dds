import Exceptions.ContraseniaInvalidaException;
import Exceptions.NoTodasLasPreguntasFueronRespondidas;
import FormasDeEncuentro.SinChapita;
import Publicaciones.PublicacionAdopcionMascota;
import Publicaciones.PublicacionIntencionAdopcion;
import Usuario.DatoDeContacto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class AsociacionTest2 extends BaseTest{

@Test
  public void generarPublicacionParaDarEnAdopcionAUnaMascota(){
  PublicacionAdopcionMascota publicacionAdopcionMascota = new PublicacionAdopcionMascota(datosPublicacion1, datosHarry);
  patitas.generarPublicacionParaAdopcion(publicacionAdopcionMascota);
  List<PublicacionAdopcionMascota> publicaciones = patitas.getListaDePublicacionesParaAdoptar();
  List<DatoDeContacto> dato = publicaciones.stream().map(PublicacionAdopcionMascota::getDatoDeContacto).collect(Collectors.toList());
  assertTrue(dato.contains(datosHarry));
}


@Test
  public void generarPublicacionParaDarEnAdopcionAUnaMascotaErroneas() {
  PublicacionAdopcionMascota publicacionAdopcionMascota = new PublicacionAdopcionMascota(datosPublicacion2,datosHarry);
   assertThrows(NoTodasLasPreguntasFueronRespondidas.class, () -> patitas.generarPublicacionParaAdopcion(publicacionAdopcionMascota));
}


@Test
  public void permitirEliminarUnaPreguntaParaAdoptarMascota(){
  franP.quitarPreguntaParaAdopcion(necesitaPatio);
  franP.agregarPreguntaParaAdopcion(necesitaPatio);
  assertTrue(patitas.getListaDePreguntas().contains(necesitaPatio));
}


@Test
  public void seEnviaNotificacionAlDuenioCuandoApareceAlgunInteresadoEnAdoptarla() {
  PublicacionAdopcionMascota publicacionAdopcionMascota = new PublicacionAdopcionMascota(datosPublicacion1, datosHarry);
  patitas.generarPublicacionParaAdopcion(publicacionAdopcionMascota);

  PublicacionAdopcionMascota publicacionDesdeUI = patitas.getListaDePublicacionesParaAdoptar().get(0);
  patitas.adoptarMascotaPublicada(publicacionDesdeUI, "francisco.panozzosf@gmail.com");
  Mockito.verify(notificacionFalsa, Mockito.only()).enviarNotificacion(Mockito.any(),Mockito.anyString(),Mockito.anyString());
}


@Test
  public void generarPublicacionIntencionDeAdopcion() {
  PublicacionIntencionAdopcion publicacionIntencionAdopcion = new PublicacionIntencionAdopcion(datosPublicacion1, datosHarry);
    patitas.generarPublicacionIntencionAdopcion(publicacionIntencionAdopcion);
    List<PublicacionIntencionAdopcion> publicaciones = patitas.getListaDePublicacionesIntencionAdopcion();
    List<DatoDeContacto> dato = publicaciones.stream().map(PublicacionIntencionAdopcion::getDatoDeContactoInteresado).collect(Collectors.toList());
    assertTrue(dato.contains(datosHarry));
  }


@Test
  public void seLeEnviaRecomendacionSemanales() {

  PublicacionIntencionAdopcion publicacionIntension = generarPublicacionIntencionAdopcionSimple(
      datosPublicacion1,
      datosHarry
  );
  PublicacionAdopcionMascota publicacionAdopcionNoCoincidente = generarPublicacionAdopcionSimple(
      datosPublicacion3,
      datosPepe
  );
  PublicacionAdopcionMascota publicacionAdopcionCoincidente = generarPublicacionAdopcionSimple(
      datosPublicacion1,
      datosPepe
  );
  patitas.generarPublicacionIntencionAdopcion(publicacionIntension);
  patitas.generarPublicacionParaAdopcion(publicacionAdopcionCoincidente);
  patitas.generarPublicacionParaAdopcion(publicacionAdopcionNoCoincidente);
  patitas.enviarRecomendaciones();

  Mockito.verify(notificacionFalsa, Mockito.times(2)).enviarNotificacion(Mockito.any(),Mockito.anyString(),Mockito.anyString());
}


@Test
  public void filtraLasPublicacionesSegunPreferencias() {
    PublicacionIntencionAdopcion publicacionIntension = generarPublicacionIntencionAdopcionSimple(
        datosPublicacion1,
        datosHarry
    );
    PublicacionAdopcionMascota publicacionAdopcionNoCoincidente = generarPublicacionAdopcionSimple(
        datosPublicacion3,
        datosPepe
    );
    PublicacionAdopcionMascota publicacionAdopcionCoincidente = generarPublicacionAdopcionSimple(
        datosPublicacion1,
        datosPepe
    );
    patitas.generarPublicacionIntencionAdopcion(publicacionIntension);
    patitas.generarPublicacionParaAdopcion(publicacionAdopcionCoincidente);
    patitas.generarPublicacionParaAdopcion(publicacionAdopcionNoCoincidente);
    List<PublicacionAdopcionMascota> listaPublicacionesFiltradas = patitas.filtrarPublicacionesInteresadosAdopcion(publicacionIntension);
    assertTrue(listaPublicacionesFiltradas.contains(publicacionAdopcionCoincidente));
  }
}








