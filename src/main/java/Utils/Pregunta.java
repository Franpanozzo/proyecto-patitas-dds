package Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pregunta {
  private boolean requerida;
  String preguntaAdopcion; //¿Que tipo de animal vas a dar en adpocion?
  String preguntaIntencion; //¿Que tipo de animal queres adoptar?
  String tipo; //"ANIMAL"
  List<String> resupuestasPosibles; //["PERRO", "GATO"]

  public Pregunta(boolean requerida, String preguntaAdopcion, String preguntaIntencion, String tipo, List<String> resupuestasPosibles){
    this.requerida = requerida;
    this.preguntaAdopcion = preguntaAdopcion;
    this.preguntaIntencion = preguntaIntencion;
    this.tipo = tipo;
    this.resupuestasPosibles = resupuestasPosibles;
  }
  
  public String getTipo() {
    return tipo;
  }

  public boolean getRequerida(){
    return requerida;
  }

  public boolean mismoTipo(String tipoResp) {
    return tipo.equals(tipoResp);
  }
}







