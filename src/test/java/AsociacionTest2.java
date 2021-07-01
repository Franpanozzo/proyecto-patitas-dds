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
  patitas.generarPublicacionParaAdopcion(datosPublicacion1,
     datosHarry
  );
  List<PublicacionAdopcionMascota> publicaciones = patitas.getListaDePublicacionesParaAdoptar();
  List<DatoDeContacto> dato = publicaciones.stream().map(PublicacionAdopcionMascota::getDatoDeContacto).collect(Collectors.toList());
  assertTrue(dato.contains(datosHarry));
}


@Test
  public void generarPublicacionParaDarEnAdopcionAUnaMascotaErroneas() {
   assertThrows(NoTodasLasPreguntasFueronRespondidas.class, () -> patitas.generarPublicacionParaAdopcion(
      datosPublicacion2,
      datosHarry));
}


@Test
  public void permitirEliminarUnaPreguntaParaAdoptarMascota(){
  franP.quitarPreguntaParaAdopcion(necesitaPatio);
  franP.agregarPreguntaParaAdopcion(necesitaPatio);
  assertTrue(patitas.getListaDePreguntas().contains(necesitaPatio));
}


@Test
  public void seEnviaNotificacionAlDuenioCuandoApareceAlgunInteresadoEnAdoptarla() {
  patitas.generarPublicacionParaAdopcion(datosPublicacion1, datosHarry);

  PublicacionAdopcionMascota publicacionDesdeUI = patitas.getListaDePublicacionesParaAdoptar().get(0);
  patitas.adoptarMascotaPublicada(publicacionDesdeUI, "francisco.panozzosf@gmail.com");
  Mockito.verify(notificacionFalsa, Mockito.only()).enviarNotificacion(Mockito.any(),Mockito.anyString(),Mockito.anyString());
}


@Test
  public void generarPublicacionIntencionDeAdopcion() {
    patitas.generarPublicacionIntencionAdopcion(
        datosPublicacion1,
        datosHarry
    );
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

  Mockito.verify(notificacionFalsa, Mockito.only()).enviarNotificacion(datosHarry,Mockito.anyString(),Mockito.anyString());
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








