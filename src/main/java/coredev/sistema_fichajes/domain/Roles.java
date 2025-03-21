package coredev.sistema_fichajes.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "Roleses")
@Getter
@Setter
public class Roles {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idrol;

    @Column(length = 50)
    private String nombre;

    @Column(columnDefinition = "longtext")
    private String descripcion;

    @ManyToMany(mappedBy = "roles")
    private Set<Usuarios> usuarios;

}
