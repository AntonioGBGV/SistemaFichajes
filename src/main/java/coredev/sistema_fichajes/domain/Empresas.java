package coredev.sistema_fichajes.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "Empresases")
@Getter
@Setter
public class Empresas {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idempresa;

    @Column(length = 100)
    private String nombre;

    @Column
    private String direccion;

    @Column(length = 20)
    private String telefono;

    @Column(length = 100)
    private String email;

    @OneToMany(mappedBy = "empresas")
    private Set<Usuarios> usuarios;

}
