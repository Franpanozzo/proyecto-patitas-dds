package Utils;

import Asociacion.Asociacion;
import ClasesPersistencia.EntidadPersistente;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Pregunta extends EntidadPersistente {
  String preguntaAdopcion; //¿Que tipo de animal vas a dar en adpocion?
  String preguntaIntencion; //¿Que tipo de animal queres adoptar?
  String tipo; //"ANIMAL"
  @ElementCollection
  List<String> resupuestasPosibles; //["PERRO", "GATO"]
  @ManyToOne
  Asociacion asociacion;

  public Pregunta( String preguntaAdopcion, String preguntaIntencion, String tipo, List<String> resupuestasPosibles, Asociacion asociacion){
    this.preguntaAdopcion = preguntaAdopcion;
    this.preguntaIntencion = preguntaIntencion;
    this.tipo = tipo;
    this.resupuestasPosibles = resupuestasPosibles;
    this.asociacion = asociacion;
  }

  public String getTipo() {
    return tipo;
  }

  public boolean mismoTipo(String tipoResp) {
    return tipo.equals(tipoResp);
  }

  public Pregunta() {

  }
}







