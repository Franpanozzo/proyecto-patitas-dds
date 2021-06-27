package Utils;

public class Pregunta {

  private boolean requerida;
  private String pregunta;
  private String respuesta;
  private Comodidades comodidadesNecesarias;

  public Comodidades getComodidadesNecesarias() {
    return comodidadesNecesarias;
  }

  public boolean getRequerida(){
    return requerida;
  }

  public boolean tieneRespuesta(){
    return !respuesta.isEmpty();
  }






}
