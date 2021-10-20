package domain.Mascota;

import javax.persistence.Embeddable;

@Embeddable
public class Coordenadas {
    Double latitud;
    Double longitud;

    public Coordenadas(Double latitud,Double longitud){
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Double getLatitud(){
        return latitud;
    }

    public Double getLongitud(){
        return longitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public double distanciaA(Coordenadas coordenadas){
        double radioTierra = 6371;//en kilómetros
        double dLat = Math.toRadians(latitud - coordenadas.getLatitud());
        double dLng = Math.toRadians(longitud - coordenadas.getLongitud());
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
            * Math.cos(Math.toRadians(latitud)) * Math.cos(Math.toRadians(coordenadas.getLatitud()));
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
        double distancia = radioTierra * va2;

        return distancia;
    }

    public Coordenadas() {

    }
}
