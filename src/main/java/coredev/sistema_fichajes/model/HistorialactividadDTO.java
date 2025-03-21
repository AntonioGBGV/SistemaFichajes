package coredev.sistema_fichajes.model;

import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class HistorialactividadDTO {

    private Integer idhistorial;

    private Integer usuarioid;

    @Size(max = 255)
    private String accion;

    @Size(max = 255)
    private String entidadafectada;

    private String descripcion;

    private LocalDateTime fecha;

    private Integer usuarios;

}
