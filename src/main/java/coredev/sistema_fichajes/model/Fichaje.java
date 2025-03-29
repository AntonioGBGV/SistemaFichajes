package coredev.sistema_fichajes.model;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "fichajes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Fichaje implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(name = "hora_entrada", nullable = false)
    private LocalDateTime horaEntrada;

    @Column(name = "hora_salida")
    private LocalDateTime horaSalida;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoFichaje estado;

    public enum EstadoFichaje {
        EN_PROGRESO,
        FINALIZADO,
        PENDIENTE
    }
}
