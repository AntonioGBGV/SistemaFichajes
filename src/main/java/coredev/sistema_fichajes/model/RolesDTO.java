package coredev.sistema_fichajes.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RolesDTO {

    private Integer idrol;

    @Size(max = 50)
    private String nombre;

    private String descripcion;

}
