package Servicios.Hogares;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ubicacion {
  private String direccion;
  private Double lat;
  @SerializedName("long")
  @Expose
  private Double longitud;

  public Double getLatitud() {
    return lat;
  }

  public Double getLongitud() {
    return longitud;
  }

  public void setLongitud(Double longitud) {
    this.longitud = longitud;
  }
}
