import Asociacion.Asociacion;
import Mascota.Mascota;
import Usuario.UsuarioDuenio;
import Usuario.DatoDeContacto;
import Usuario.UsuarioAdministrador;
import Mascota.MascotaPerdida;
import Mascota.Coordenadas;
import Mascota.Animal;
import Mascota.Sexo;
import EntidadesExternas.Rescatista;
import Usuario.Usuario;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AsociacionTest {

  List<String> caracteristicasBombon = Arrays.asList("marron","grande");
  LocalDate fechita;
  LocalDate fecha;
  LocalDate fechaPerdida = LocalDate.now();
  List<Usuario> usuariosRegistrados;
  List<MascotaPerdida> mascotasEncontradasEnCalleList;
  List<String> caracteristicasPosibles;


  @Test
  public void registrarDosMascotasAUnUsuario(){
    pepe().registrarMascota(bombon());
    pepe().registrarMascota(oli());
   /** assertEquals(pepe().getMascotasList(),Arrays.asList(bombon(),oli()),0);*/
    assertTrue(pepe().getMascotasList().contains(oli()));
    assertTrue(pepe().getMascotasList().contains(bombon()));
  }
  @Test
  public void administradorPuedeAgregarFacilmenteCaracteristicas(){
    fran().agregarCaracteristica("peludo");
    fran().agregarCaracteristica("negro");
    assertTrue(Asociacion.getInstance().getCaracteristicasPosibles().contains("peludo"));
    assertTrue(Asociacion.getInstance().getCaracteristicasPosibles().contains("negro"));

  }

  @Test
  public void personaPuedeInformarUnPerroPerdido(){
    franB().informarMascotaEncontrada(wendy());
    assertTrue(Asociacion.getInstance().getMascotasEncontradasEnCalleList().contains(wendy()));
  }

  @Test
  public void listarMascotasPerdidas10Dias(){
    franB().informarMascotaEncontrada(wendy());
    franB().informarMascotaEncontrada(murri());
    facu().informarMascotaEncontrada(millo());
    facu().informarMascotaEncontrada(milton());
    assertEquals(Asociacion.getInstance().obtenerMascotasDeLosUltimosDias(), Arrays.asList(wendy(),murri(),milton()));
    /**este test esta mal pq no sabemos usar ASSERTEQUALS y como
     * usamos local date no sabemos la fecha de millo, suponemos que no lo devuelve por el now
     * pero nos falta saber el intervalo de los 10 dias*/
  }

  private MascotaPerdida millo(){
    return new MascotaPerdida(facu(),"foto","perdida",new Coordenadas("52° 31' 28'' N"," 13° 24' 38'' E"),fechita);
  }

  private MascotaPerdida milton(){
    return new MascotaPerdida(facu(),"foto","perdida",new Coordenadas("52° 31' 28'' N"," 13° 24' 38'' E"),fecha);
  }

  private MascotaPerdida murri(){
    return new MascotaPerdida(franB(),"foto","perdida",new Coordenadas("52° 31' 28'' N"," 13° 24' 38'' E"),fecha);
  }

  private MascotaPerdida wendy(){
  return new MascotaPerdida(franB(),"foto","perdida",new Coordenadas("52° 31' 28'' N"," 13° 24' 38'' E"),fechaPerdida);
  }

  private Rescatista facu(){
    return new Rescatista("facuAlv",fecha,"dni",40122587,new Coordenadas("52° 31' 28'' N"," 13° 24' 38'' E"),Collections.singletonList(datoFacu("fede",1130450832,"fedecarp@gmail.com")));
  }

  private Rescatista franB(){
    return new Rescatista("FranBlasco",fecha,"dni",40122287,new Coordenadas("52° 31' 28'' N"," 13° 24' 38'' E"),Collections.singletonList(datoFran("facundofacu",1130550832,"facuelmejor@gmail.com")));
  }
  private UsuarioAdministrador fran(){
    return new UsuarioAdministrador("franpano","sofilamejor","franBautiPanozzo",fecha,"dni",42842567, Collections.singletonList(datoFran("pepeGonzales",1140520743, "pepitogonzales@gmail.com")));
  }

  private Mascota oli() {
    return new Mascota(Animal.PERRO,"olivia","oli",12,Sexo.HEMBRA, "educada","foto", Collections.singletonList("gris"));
  }

  private Mascota bombon() {
    return new Mascota(Animal.PERRO,"bombon","bombi",15,Sexo.HEMBRA, "labrador","foto", caracteristicasBombon );
  }

  private UsuarioDuenio pepe() {
    return new UsuarioDuenio("pepe12", "pepito", "pepeGonzales",fecha,"dni",2022440, Collections.singletonList(datoPepe("juliaGonzales",1140520843, "jgonzales@gmail.com")));
  }

  private DatoDeContacto datoFacu(String nombreYApellido, Integer telefono, String email) {
    return new DatoDeContacto(nombreYApellido, telefono, email);
  }

  private DatoDeContacto datoFran(String nombreYApellido, Integer telefono, String email) {
    return new DatoDeContacto(nombreYApellido, telefono, email);
  }

  private DatoDeContacto datoPepe(String nombreYApellido, Integer telefono, String email) {
    return new DatoDeContacto(nombreYApellido, telefono, email);
  }



}

