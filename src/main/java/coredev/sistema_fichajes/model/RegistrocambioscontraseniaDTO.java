package coredev.sistema_fichajes.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RegistrocambioscontraseniaDTO {

    private Integer idregistro;
    private Integer usuarioid;
    private LocalDateTime fechacambio;
    private Integer usuarios;

}
