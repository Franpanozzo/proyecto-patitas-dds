package Publicaciones;
import Utils.*;
import Usuario.DatoDeContacto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PublicacionAdopcionMascota {
  private List<Pregunta> dataPublicacion;
  DatoDeContacto datoDeContacto;
  String link;

  public String getLink() {
    return link;
  }

  public PublicacionAdopcionMascota(List<Pregunta> dataPublicacion, DatoDeContacto datoDeContacto) {
    this.dataPublicacion = dataPublicacion;
    this.datoDeContacto = datoDeContacto;
  }

  public DatoDeContacto getDatoDeContacto(){
    return datoDeContacto;
  }

  public boolean cumpleRequisitos(PublicacionIntencionAdopcion publicacionIntencionAdopcion, List<String> tiposDePregRequeridas) {
    List<String> requisitosSolicitados = new ArrayList<>();
    List<String> requisitosIntencion = new ArrayList<>();
    tiposDePregRequeridas.stream().forEach(x -> armarListaDeValuesRequeridas(x, requisitosSolicitados));
    tiposDePregRequeridas.stream().forEach(x -> armarListaDeValuesRequeridasIntencion(x,requisitosIntencion, publicacionIntencionAdopcion)); //Lista con las values que son iguales a la publicacion de adopcion
    return requisitosSolicitados.equals(requisitosIntencion);
  }

  public void armarListaDeValuesRequeridas(String key, List<String> requisitos){
    requisitos.add(dataPublicacion.get(key));
  }

  public void  armarListaDeValuesRequeridasIntencion(String key, List<String> requisitos, PublicacionIntencionAdopcion publicacionIntencionAdopcion){
    requisitos.add(publicacionIntencionAdopcion.getDataPublicacion().get(key));
  }
}




