package Asociacion;

import Exceptions.NoTodasLasPreguntasFueronRespondidas;
import Mascota.Coordenadas;
import Mascota.MascotaPerdida;
import Notificacion.FormaDeNotificar;
import Repositorios.RepositorioPreguntasGlobales;
import Utils.*;
import Publicaciones.*;
import Repositorios.RepositorioUsuarios;
import Usuario.*;
import Usuario.DatoDeContacto;
//import jdk.vm.ci.meta.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Asociacion {

    String nombreAsociacion;
    List<String> caracteristicasPosibles = new ArrayList<>();
    //Listas de Publicaciones
    List<PublicacionMascotaPerdida> listaDePublicaciones = new ArrayList<>();
    List<PublicacionAdopcionMascota> listaDePublicacionesParaAdoptar = new ArrayList<>();
    List<PublicacionIntencionAdopcion> listaDePublicacionesIntencionAdopcion = new ArrayList<>();
    //Lista de Preguntas para crear Publicacion
    List<Pregunta> listaDePreguntas = new ArrayList<>();
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
    public void aprobarPublicaciones(List<PublicacionMascotaPerdida> listaDePublicaciones) {
        listaDePublicaciones.forEach(PublicacionMascotaPerdida::validar);
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

    public void quitarPregunta(Pregunta pregunta){
        listaDePreguntas.remove(pregunta);
    }

    //Este metodo se llamaria cuando se presiona el boton de generar publicacion en la UI y genera el formulario con las preguntas
    //Anidar con las globales
    public List<Pregunta> getListaDePreguntas(){
        List<Pregunta> listaDePreguntasRequeridas = RepositorioPreguntasGlobales.getInstance().getListaDePreguntasRequeridas();
        return Stream.concat(listaDePreguntasRequeridas.stream(), listaDePreguntas.stream()).collect(Collectors.toList());
    }

    public void generarPublicacionParaAdopcion(PublicacionAdopcionMascota publicacionAdopcionMascota) {
        this.chequearRespuestas(publicacionAdopcionMascota.getDataPublicacion());
        listaDePublicacionesParaAdoptar.add(publicacionAdopcionMascota);
    }

    public List<PublicacionAdopcionMascota> getListaDePublicacionesParaAdoptar(){
        return listaDePublicacionesParaAdoptar;
    }

    // En el codigo de la UI hacemos un try catch marcando en rojo las que faltan por responder
    public void chequearRespuestas(Map<String, String> preguntasRespondidas) {
        preguntasRespondidas.forEach(this::chequearConSuPregunta);
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
        return RepositorioPreguntasGlobales.getInstance().getListaDePreguntasRequeridas();
    }

    public void adoptarMascotaPublicada(PublicacionAdopcionMascota publicacionAdopcionMascota, String mailDeAdoptador) {
        listaDePublicacionesParaAdoptar.remove(publicacionAdopcionMascota);
        repositorioUsuarios.notificarDuenioActual(publicacionAdopcionMascota.getDatoDeContacto(), mailDeAdoptador);
    }

    public void generarPublicacionIntencionAdopcion(PublicacionIntencionAdopcion publicacionIntencionAdopcion){
        listaDePublicacionesIntencionAdopcion.add(publicacionIntencionAdopcion);
        repositorioUsuarios.notificarPublicacionCreada(publicacionIntencionAdopcion.getDatoDeContactoInteresado());
    }

    public void deshacerPublicacionIntencionAdopcion(PublicacionIntencionAdopcion publicacionIntencionAdopcion) {
        listaDePublicacionesIntencionAdopcion.remove(publicacionIntencionAdopcion);
    }

    //ESTE METODO SE LLAMA SEMANALMENTE
    public void enviarRecomendaciones() {
        listaDePublicacionesIntencionAdopcion.forEach(publicacionInteresado -> {
            List<PublicacionAdopcionMascota> publicacionFiltrada = this.filtrarPublicacionesInteresadosAdopcion(publicacionInteresado);
            repositorioUsuarios.enviarRecomendacion(publicacionInteresado.getDatoDeContactoInteresado(),publicacionFiltrada);
        });
    }

    public List<PublicacionAdopcionMascota> filtrarPublicacionesInteresadosAdopcion(PublicacionIntencionAdopcion publicacionIntencionAdopcion) {
        return listaDePublicacionesParaAdoptar.stream()
            .filter(publicacion -> publicacion.cumpleRequisitos(publicacionIntencionAdopcion, this.keysDePreguntasReq()))
            .collect(Collectors.toList());
    }

    public List<String> keysDePreguntasReq() {
        return this.preguntasRequeridas().stream().map(Pregunta::getTipo).collect(Collectors.toList());
    }



}










