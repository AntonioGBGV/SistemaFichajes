package coredev.sistema_fichajes.model;

import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FichajesDTO {

    private Integer idfichaje;

    private Integer usuarioid;

    private LocalDateTime fecha;

    private LocalDateTime horaentrada;

    private LocalDateTime horasalida;

    @Size(max = 255)
    private String estado;

    private Integer usuarios;

}
