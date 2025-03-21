package coredev.sistema_fichajes.model;

import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UsuariosDTO {

    private Integer idusuario;

    @Size(max = 50)
    private String nombre;

    @Size(max = 100)
    private String apellidos;

    @Size(max = 100)
    @UsuariosEmailUnique
    private String email;

    @Size(max = 255)
    private String password;

    private Boolean activo;

    private Boolean primeracceso;

    private Integer empresas;

    private List<Integer> roles;

    @UsuariosConfiguracionautenticacionUnique
    private Integer configuracionautenticacion;

    private Integer horasextrasusuarios;

}
