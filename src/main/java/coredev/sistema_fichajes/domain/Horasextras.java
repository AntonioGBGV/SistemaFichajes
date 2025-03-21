package coredev.sistema_fichajes.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "Horasextrases")
@Getter
@Setter
public class Horasextras {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idhoraextra;

    @Column
    private Integer usuarioid;

    @Column
    private LocalDateTime fecha;

    @Column(precision = 4, scale = 2)
    private BigDecimal horassolicitadas;

    @Column(precision = 4, scale = 2)
    private BigDecimal horasaprobadas;

    @Column(columnDefinition = "longtext")
    private String motivo;

    @Column
    private String estado;

    @Column
    private Integer aprobadopor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarios_id")
    private Usuarios usuarios;

    @OneToMany(mappedBy = "horasextrasusuarios")
    private Set<Usuarios> usuarioroldireccion;

}
