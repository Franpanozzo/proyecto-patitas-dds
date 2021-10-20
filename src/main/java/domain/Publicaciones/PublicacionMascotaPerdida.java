package domain.Publicaciones;

import domain.ClasesPersistencia.EntidadPersistente;
import domain.Mascota.DatosMascotaPerdida;
import domain.Usuario.DatoDeContacto;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PublicacionMascotaPerdida extends EntidadPersistente {
  @Embedded
  DatosMascotaPerdida datosMascotaPerdida;
  boolean validada = false;
  //Esto no se muestra en interfaz grafica
  @ManyToMany(cascade = CascadeType.ALL)
  List<DatoDeContacto> datoDeContactoDelRescatista = new ArrayList<>();

  public PublicacionMascotaPerdida(DatosMascotaPerdida datosMascotaPerdida, List<DatoDeContacto> datoDeContactoList) {
    this.datosMascotaPerdida = datosMascotaPerdida;
    this.datoDeContactoDelRescatista = datoDeContactoList;
  }

  public List<DatoDeContacto> getDatoDeContactoDelRescatista() {
    return datoDeContactoDelRescatista;
  }

  public void validar() {
    validada = true;
  }

  public DatosMascotaPerdida getDatosMascotaPerdida() {
    return datosMascotaPerdida;
  }

  public boolean validada() {
    return validada;
  }

  public boolean encontradaDespuesDe(LocalDate fechaMin) {
    return datosMascotaPerdida.encontradaDespuesDe(fechaMin);
  }

  public PublicacionMascotaPerdida() {

  }
}









