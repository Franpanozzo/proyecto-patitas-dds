package domain.Utils;

import domain.Asociacion.Asociacion;
import domain.ClasesPersistencia.EntidadPersistente;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class Pregunta extends EntidadPersistente {
  String preguntaAdopcion; //¿Que tipo de animal vas a dar en adpocion?
  String preguntaIntencion; //¿Que tipo de animal queres adoptar?
  String tipo; //"ANIMAL"
  @ElementCollection
  List<String> respuestasPosibles; //["PERRO", "GATO"]
  @ManyToOne
  Asociacion asociacion;

  public Pregunta( String preguntaAdopcion, String preguntaIntencion, String tipo, List<String> resupuestasPosibles, Asociacion asociacion){
    this.preguntaAdopcion = preguntaAdopcion;
    this.preguntaIntencion = preguntaIntencion;
    this.tipo = tipo;
    this.respuestasPosibles = resupuestasPosibles;
    this.asociacion = asociacion;
  }

  public Asociacion getAsociacion(){
    return asociacion;
  }

  public String getTipo() {
    return tipo;
  }

  public String getPreguntaAdopcion() {
    return preguntaAdopcion;
  }

  public String getPreguntaIntencion() {
    return preguntaIntencion;
  }

  public String getRespuestasPosibles() {
    return String.valueOf(respuestasPosibles);
  }

  public boolean mismoTipo(String tipoResp) {
    return tipo.equals(tipoResp);
  }

  public Pregunta() {

  }
}







