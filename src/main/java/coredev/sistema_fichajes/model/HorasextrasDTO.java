package coredev.sistema_fichajes.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class HorasextrasDTO {

    private Integer idhoraextra;

    private Integer usuarioid;

    private LocalDateTime fecha;

    @Digits(integer = 4, fraction = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal horassolicitadas;

    @Digits(integer = 4, fraction = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal horasaprobadas;

    private String motivo;

    @Size(max = 255)
    private String estado;

    private Integer aprobadopor;

    private Integer usuarios;

}
