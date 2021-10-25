package domain.Asociacion;

import domain.ClasesPersistencia.EntidadPersistente;
import domain.Exceptions.NoTodasLasPreguntasFueronRespondidas;
import domain.Mascota.Coordenadas;
import domain.Mascota.MascotaPerdida;
import domain.Notificacion.FormaDeNotificar;
import domain.Repositorios.RepositorioPreguntasGlobales;
import domain.Utils.*;
import domain.Publicaciones.*;
import domain.Repositorios.RepositorioUsuarios;
import domain.Usuario.*;
import domain.Usuario.DatoDeContacto;
//import jdk.vm.ci.meta.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "asociacion")
public class Asociacion extends EntidadPersistente {

    String nombreAsociacion;
    @ElementCollection
    List<String> caracteristicasPosibles = new ArrayList<>();
    //Listas de domain.Publicaciones
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "asociacion_id", referencedColumnName = "Id")
    List<PublicacionMascotaPerdida> listaDePublicaciones = new ArrayList<>();

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "asociacion_id", referencedColumnName = "Id")
    List<PublicacionAdopcionMascota> listaDePublicacionesParaAdoptar = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "asociacion_id", referencedColumnName = "Id")
    List<PublicacionIntencionAdopcion> listaDePublicacionesIntencionAdopcion = new ArrayList<>();

    //Lista de Preguntas para crear Publicacion
    @OneToMany(mappedBy = "asociacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Pregunta> listaDePreguntas = new ArrayList<>();
    @Embedded
    Coordenadas direccion;

    public Asociacion(String nombreAsociacion, Coordenadas direccion) {
        this.nombreAsociacion = nombreAsociacion;
        this.direccion = direccion;
    }

    //Metodo para implementar MOCKITO
    public void cambiarFormaDeNotificar(FormaDeNotificar mail) {
        RepositorioUsuarios.getInstance().setformaDeNotificar(mail);
    }

    public void agregarCarateristica(String caracteristica) {
        caracteristicasPosibles.add(caracteristica);
    }

    public RepositorioUsuarios getGestorDeAsociacion() {
        return RepositorioUsuarios.getInstance();
    }

    public void registrarUsuario(Usuario usuarioNuevo) {
        RepositorioUsuarios.getInstance().cargarNuevoUsuario(usuarioNuevo);
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
        RepositorioUsuarios.getInstance().buscarDuenioYNotificar(codigoQR, nombreAsociacion);
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
        RepositorioUsuarios.getInstance().notificarRescatista(algunContactoDelRescatista, emailSupuestoDuenio);
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
        List<Pregunta> listaDePreguntasRequeridas = this.preguntasRequeridas();
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
        RepositorioUsuarios.getInstance().notificarDuenioActual(publicacionAdopcionMascota.getDatoDeContacto(), mailDeAdoptador);
    }

    public void generarPublicacionIntencionAdopcion(PublicacionIntencionAdopcion publicacionIntencionAdopcion){
        listaDePublicacionesIntencionAdopcion.add(publicacionIntencionAdopcion);
        RepositorioUsuarios.getInstance().notificarPublicacionCreada(publicacionIntencionAdopcion.getDatoDeContactoInteresado());
    }

    public void deshacerPublicacionIntencionAdopcion(PublicacionIntencionAdopcion publicacionIntencionAdopcion) {
        listaDePublicacionesIntencionAdopcion.remove(publicacionIntencionAdopcion);
    }

    //ESTE METODO SE LLAMA SEMANALMENTE
    public void enviarRecomendaciones() {
        listaDePublicacionesIntencionAdopcion.forEach(publicacionInteresado -> {
            List<PublicacionAdopcionMascota> publicacionFiltrada = this.filtrarPublicacionesInteresadosAdopcion(publicacionInteresado);
            RepositorioUsuarios.getInstance().enviarRecomendacion(publicacionInteresado.getDatoDeContactoInteresado(),publicacionFiltrada);
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

    public Asociacion() {

    }

}










