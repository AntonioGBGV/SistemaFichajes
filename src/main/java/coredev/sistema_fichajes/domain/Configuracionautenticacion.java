package coredev.sistema_fichajes.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "Configuracionautenticacions")
@Getter
@Setter
public class Configuracionautenticacion {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idautenticacion;

    @Column
    private Integer usuarioid;

    @Column
    private String codigosecreto;

    @Column(columnDefinition = "tinyint", length = 1)
    private Boolean activado;

    @OneToOne(
            mappedBy = "configuracionautenticacion",
            fetch = FetchType.LAZY
    )
    private Usuarios usuarios;

}
