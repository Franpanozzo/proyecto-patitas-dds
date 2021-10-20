package domain.Hogares;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ubicacion {
  private String direccion;
  private Double lat;
  @SerializedName("long")
  @Expose
  private Double longitud;

  public Double getLatitud() {
    if(lat == null) { return -34.631010; }
    return lat;
  }

  public Double getLongitud() {
    if(longitud == null) { return -58.49462; }
    return longitud;
  }

  public void setLongitud(Double longitud) {
    this.longitud = longitud;
  }
}
