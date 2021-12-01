package controllers;

import domain.Asociacion.Asociacion;
import domain.EntidadesExternas.Rescatista;
import domain.FormasDeEncuentro.ConChapita;
import domain.FormasDeEncuentro.SinChapita;
import domain.Mascota.*;
import domain.Repositorios.RepositorioAsociaciones;
import domain.Repositorios.RepositorioUsuarios;
import domain.Usuario.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.*;

public class MascotasController {
  public ModelAndView mascotas(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    UsuarioDuenio usuario = (UsuarioDuenio) RepositorioUsuarios.getInstance().usuarioConNombre(request.session().attribute("usuario_logueado"));
    String filtroParametro = request.queryParams("likeNombre");

    List<Mascota> mascotas = Optional.ofNullable(filtroParametro)
        .map(usuario::filtrarPorNombre)
        .orElseGet(usuario::getMascotasList);

    model.put("likeNombre",filtroParametro);
    model.put("usuario",usuario);
    model.put("mascotas", mascotas);
    model.put("codigoQR", usuario.getCodigoQR());

    model.put("botonLogOut","botonLogOut");
    return new ModelAndView(model, "mascotas.hbs");

  }

  public ModelAndView guardar(Request req, Response res) {
    UsuarioDuenio usuario_logueado = (UsuarioDuenio) RepositorioUsuarios.getInstance().usuarioConNombre(req.session().attribute("usuario_logueado"));

    Mascota mascota = new Mascota(Animal.valueOf(req.queryParams("Animal")),
        req.queryParams("nombre"),
        req.queryParams("apodo"),
        Integer.parseInt(req.queryParams("edad")),
        Sexo.valueOf(req.queryParams("sexo")),
        req.queryParams("descrip"),
        req.queryParams("foto")
        );

    Map<String,String> caracteristicas = new HashMap<>();
    caracteristicas.put(req.queryParams("tipoCaract"),req.queryParams("caract"));
    if(req.queryParams("tipoCaract2") != null){
      caracteristicas.put(req.queryParams("tipoCaract2"),req.queryParams("caract2"));
    }

    System.out.println("EEE " + caracteristicas.toString());

    usuario_logueado.registrarMascota(mascota, caracteristicas);
    RepositorioUsuarios.getInstance().modificarUsuario(usuario_logueado);

    res.redirect("/mascotas");
    return null;
  }


  public Response guardarEncontrada(Request req, Response res) {
    String nombreApellido = req.queryParams("nombre") + " " + req.queryParams("apellido");
    Asociacion asociacion = RepositorioAsociaciones.getInstance().asociacionConNombre("Patitas");

    DatosPersonales datosPersonales = new DatosPersonales(nombreApellido,
        LocalDate.parse(req.queryParams("fechaNac")),
        TipoDocumento.valueOf(req.queryParams("tipoDoc")),
        Integer.parseInt(req.queryParams("numeroDoc"))
    );

    List<DatoDeContacto> listaDatosDeContacto = new ArrayList<>();

    DatoDeContacto datoDeContacto = new DatoDeContacto(nombreApellido, Integer.parseInt(req.queryParams("telefonoDC")), req.queryParams("emailDC"));
    listaDatosDeContacto.add(datoDeContacto);
    if(req.queryParams("telefonoDC2").length() != 0){
      System.out.println("Recibi este telefono " + req.queryParams("telefonoDC2"));
      DatoDeContacto datoDeContacto2 = new DatoDeContacto(nombreApellido, Integer.parseInt(req.queryParams("telefonoDC2")), req.queryParams("emailDC2"));
      listaDatosDeContacto.add(datoDeContacto2);
    }

    Coordenadas coordenadasDirResc = new Coordenadas(
        Double.parseDouble(req.queryParams("latitudDir")),
        Double.parseDouble(req.queryParams("longitudDir"))
    );

    Coordenadas coordenadasEncuentro = new Coordenadas(
        Double.parseDouble(req.queryParams("latitud")),
        Double.parseDouble(req.queryParams("longitud"))
    );

    Rescatista rescatista = new Rescatista(datosPersonales, coordenadasDirResc, listaDatosDeContacto);

    String[] descripciones = req.queryParams("descripcion").split(",");
    List<String> descripArray = Arrays.asList(descripciones);

    System.out.println("Descrip: " + descripArray);

    MascotaPerdida mascotaPerdida = new MascotaPerdida(
        rescatista,
        req.queryParams("foto"),
        descripArray,
        coordenadasEncuentro,
        LocalDate.parse(req.queryParams("fechaEnc")),
        Animal.valueOf(req.queryParams("animal")),
        Tamanio.valueOf(req.queryParams("tamanio"))
    );

    String tieneChapita = req.queryParams("chapita");
    if(tieneChapita.equals("SI")) {
      Chapita chapita = new Chapita(req.queryParams("codigoQR"), asociacion);
      mascotaPerdida.setChapita(chapita);
      rescatista.informarMascotaEncontrada(mascotaPerdida, new ConChapita());
    }
    else {
      rescatista.informarMascotaEncontrada(mascotaPerdida, new SinChapita());
    }

    RepositorioAsociaciones.getInstance().modificarAsociacion(asociacion);

    res.redirect("/");
    return null;
  }
}
