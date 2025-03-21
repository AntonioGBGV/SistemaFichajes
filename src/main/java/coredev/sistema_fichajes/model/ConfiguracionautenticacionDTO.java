package coredev.sistema_fichajes.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ConfiguracionautenticacionDTO {

    private Integer idautenticacion;

    private Integer usuarioid;

    @Size(max = 255)
    private String codigosecreto;

    private Boolean activado;

}
