package coredev.sistema_fichajes.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EmpresasDTO {

    private Integer idempresa;

    @Size(max = 100)
    private String nombre;

    @Size(max = 255)
    private String direccion;

    @Size(max = 20)
    private String telefono;

    @Size(max = 100)
    private String email;

}
