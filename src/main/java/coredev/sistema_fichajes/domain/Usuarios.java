package coredev.sistema_fichajes.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "Usuarioses")
@Getter
@Setter
public class Usuarios {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idusuario;

    @Column(length = 50)
    private String nombre;

    @Column(length = 100)
    private String apellidos;

    @Column(unique = true, length = 100)
    private String email;

    @Column
    private String password;

    @Column(columnDefinition = "tinyint", length = 1)
    private Boolean activo;

    @Column(columnDefinition = "tinyint", length = 1)
    private Boolean primeracceso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresas_id")
    private Empresas empresas;

    @ManyToMany
    @JoinTable(
            name = "Usuariosroleses",
            joinColumns = @JoinColumn(name = "idusuario"),
            inverseJoinColumns = @JoinColumn(name = "idrol")
    )
    private Set<Roles> roles;

    @OneToMany(mappedBy = "usuarios")
    private Set<Fichajes> fichajes;

    @OneToMany(mappedBy = "usuarios")
    private Set<Horasextras> horasextras;

    @OneToMany(mappedBy = "usuarios")
    private Set<Registrocambioscontrasenia> registrocambioscontrasenia;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "configuracionautenticacion_id", unique = true)
    private Configuracionautenticacion configuracionautenticacion;

    @OneToMany(mappedBy = "usuarios")
    private Set<Historialactividad> historialactividad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "horasextrasusuarios_id")
    private Horasextras horasextrasusuarios;

}
