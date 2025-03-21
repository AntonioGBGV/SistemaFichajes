package coredev.sistema_fichajes.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "Registrocambioscontrasenias")
@Getter
@Setter
public class Registrocambioscontrasenia {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idregistro;

    @Column
    private Integer usuarioid;

    @Column
    private LocalDateTime fechacambio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarios_id")
    private Usuarios usuarios;

}
