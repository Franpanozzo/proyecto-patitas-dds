package Asociacion;

import Exceptions.NoTodasLasPreguntasFueronRespondidas;
import Mascota.Coordenadas;
import Mascota.MascotaPerdida;
import Notificacion.FormaDeNotificar;
import Utils.*;
import Publicaciones.*;
import Repositorios.RepositorioUsuarios;
import Usuario.*;
import Usuario.DatoDeContacto;
//import jdk.vm.ci.meta.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Asociacion {
    String nombreAsociacion;
    List<String> caracteristicasPosibles = new ArrayList<>();
    List<PublicacionMascotaPerdida> listaDePublicaciones = new ArrayList<>();
    List<Pregunta> listaDePreguntas = new ArrayList<>();
    List<PublicacionAdopcionMascota> listaDePublicacionesParaAdoptar = new ArrayList<>();
    List<PublicacionIntencionAdopcion> listaDePublicacionesIntencionAdopcion = new ArrayList<>();
    RepositorioUsuarios repositorioUsuarios;
    Coordenadas direccion;

    public Asociacion(String nombreAsociacion, Coordenadas direccion) {
        this.nombreAsociacion = nombreAsociacion;
        this.repositorioUsuarios = new RepositorioUsuarios();
        this.direccion = direccion;
    }

    //Metodo para implementar MOCKITO
    public void cambiarFormaDeNotificar(FormaDeNotificar mail) {
        repositorioUsuarios.setformaDeNotificar(mail);
    }

    public void agregarCarateristica(String caracteristica) {
        caracteristicasPosibles.add(caracteristica);
    }

    public RepositorioUsuarios getGestorDeAsociacion() {
        return repositorioUsuarios;
    }

    public void registrarUsuario(Usuario usuarioNuevo) {
        repositorioUsuarios.cargarNuevoUsuario(usuarioNuevo);
    }

    public List<String> getCaracteristicasPosibles() {
        return caracteristicasPosibles;
    }

    public List<PublicacionIntencionAdopcion> getListaDePublicacionesIntencionAdopcion() {
        return listaDePublicacionesIntencionAdopcion;
    }

    public void quitarPublicacion(PublicacionMascotaPerdida publicacionMascotaPerdida) {
       listaDePublicaciones.remove(publicacionMascotaPerdida);
    }

    public List<PublicacionMascotaPerdida> obtenerPublicacionesDeLosUltimosDias() {
        LocalDate fechaMin = LocalDate.now().minusDays(10);
        return this.publicacionesValidadas().stream().
            filter(publi -> publi.encontradaDespuesDe(fechaMin)).
            collect(Collectors.toList());
    }

    public double distanciaALugarDeEncuentro(MascotaPerdida mascotaPerdida) {
        return mascotaPerdida.distanciaAEncuentro(direccion);
    }

    public void buscarDuenioYNotificar(String codigoQR) {
        repositorioUsuarios.buscarDuenioYNotificar(codigoQR, nombreAsociacion);
    }

    public void registrarPublicacion(PublicacionMascotaPerdida publicacionMascotaPerdida) {
        listaDePublicaciones.add(publicacionMascotaPerdida);
    }

    public List<PublicacionMascotaPerdida> publicacionesValidadas() {
        return listaDePublicaciones.stream().filter(publicacionMascotaPerdida -> publicacionMascotaPerdida.validada()).collect(Collectors.toList());
    }

    //
    public void aprobarPublicacion(List<PublicacionMascotaPerdida> listaDePublicaciones) {
        listaDePublicaciones.forEach(publicacionMascotaPerdida -> publicacionMascotaPerdida.validar());
    }

    public List<PublicacionMascotaPerdida> getListaDePublicaciones() {
        return listaDePublicaciones;
    }

    public void encuentroDeMascotaEnPublicacion(PublicacionMascotaPerdida publicacionMascotaPerdidaElegida, String emailSupuestoDuenio) {
        List<DatoDeContacto> datosDeContRescatista = publicacionMascotaPerdidaElegida.getDatoDeContactoDelRescatista();
        DatoDeContacto algunContactoDelRescatista = datosDeContRescatista.stream().findAny().get();

        this.quitarPublicacion(publicacionMascotaPerdidaElegida);
        repositorioUsuarios.notificarRescatista(algunContactoDelRescatista, emailSupuestoDuenio);
        // Coordina entrega con el siguiente mail: tataa
    }
    
    public void agregarPregunta(Pregunta preguntaNueva){
        listaDePreguntas.add(preguntaNueva);
    }

    //Este metodo se llamaria cuando se presiona el boton de generar publicacion en la UI y genera el formulario con las preguntas
    public List<Pregunta> getListaDePreguntas(){
        return listaDePreguntas;
    }


    public void generarPublicacionParaAdopcion(Map<String, String> preguntasRespondidas, DatoDeContacto contacto) {
        this.chequearRespuestas(preguntasRespondidas);
        PublicacionAdopcionMascota publicacionAdopcionMascota = new PublicacionAdopcionMascota(preguntasRespondidas, contacto);
        listaDePublicacionesParaAdoptar.add(publicacionAdopcionMascota);
    }

    // En el codigo de la UI hacemos un try catch marcando en rojo las que faltan por responder
    public void chequearRespuestas(List<Pregunta> preguntasRespondidas) {
        if(!this.todasLasPreguntasRespondidas(preguntasRespondidas)) {
            throw new NoTodasLasPreguntasFueronRespondidas("Faltan preguntas por responder");
        }
    }

    public void chequearConSuPregunta(String tipoResp, String resp) {
        Optional<Pregunta> pregunta = this.preguntasRequeridas().stream().filter(preguntaReq -> preguntaReq.mismoTipo(tipoResp) ).findAny();
        if(pregunta.isPresent()) {
            if(resp.equals("NULL")){
                throw new NoTodasLasPreguntasFueronRespondidas("No todas las preguntas fueron respondidas, vuelve a chequear");
            }
        }
    }

    public List<Pregunta> preguntasRequeridas() {
        return listaDePreguntas.stream().filter(Pregunta::getRequerida).collect(Collectors.toList());
    }

    public void adoptarMascotaPublicada(PublicacionAdopcionMascota publicacionAdopcionMascota, String mailDeAdoptador) {
        listaDePublicacionesParaAdoptar.remove(publicacionAdopcionMascota);
        repositorioUsuarios.notificarDuenioActual(publicacionAdopcionMascota.getDatoDeContacto(), mailDeAdoptador);
    }

    public void generarPublicacionIntencionAdopcion(DatosDeMascotaEnAdopcion datosDeMascotaEnAdopcion, DatoDeContacto datoDeContacto) {
        PublicacionIntencionAdopcion publicacionIntencionAdopcion = new PublicacionIntencionAdopcion(datoDeContacto, datosDeMascotaEnAdopcion);
        listaDePublicacionesIntencionAdopcion.add(publicacionIntencionAdopcion);
        repositorioUsuarios.notificarPublicacionCreada(datoDeContacto);
    }

    public void deshacerPublicacionIntencionAdopcion(PublicacionIntencionAdopcion publicacionIntencionAdopcion) {
        listaDePublicacionesIntencionAdopcion.remove(publicacionIntencionAdopcion);
    }

    //ESTE METODO SE LLAMA SEMANALMENTE
    public void enviarRecomendaciones() {
        listaDePublicacionesIntencionAdopcion.forEach(this::filtrarYMandar);
    }

    public void filtrarYMandar(PublicacionIntencionAdopcion publicacionIntencionAdopcion) {
        List<PublicacionAdopcionMascota> publicacionesQueCumplen = listaDePublicacionesParaAdoptar.stream().
                                           filter(publicacion -> publicacion.cumpleRequisitos(publicacionIntencionAdopcion, this.keysDePreguntasReq()))
                                               .collect(Collectors.toList());
        repositorioUsuarios.enviarRecomendacion(publicacionIntencionAdopcion.getDatoDeContactoInteresado(), publicacionesQueCumplen);
    }

    public List<String> keysDePreguntasReq() {
        return this.preguntasRequeridas().stream().map(Pregunta::getTipo).collect(Collectors.toList());
    }

}










