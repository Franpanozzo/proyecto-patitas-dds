package Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Usuario {
    String nombreUsuario;
    String contraseña;
    String nombreApellido;
    LocalDate fechaNacimiento;
    String tipoDocumento;
    Integer numeroDocumento;
    List<DatoDeContacto> datoDeContactoList = new ArrayList<>();

    public Usuario(String nombreUsuario, String contraseña, String nombreApellido, LocalDate fechaNacimiento, String tipoDocumento, Integer numeroDocumento, List<DatoDeContacto> datoDeContactoList) {
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.nombreApellido = nombreApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.datoDeContactoList = datoDeContactoList;
    }

}


