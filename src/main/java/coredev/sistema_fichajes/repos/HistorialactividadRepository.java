package coredev.sistema_fichajes.repos;

import coredev.sistema_fichajes.domain.Historialactividad;
import coredev.sistema_fichajes.domain.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HistorialactividadRepository extends JpaRepository<Historialactividad, Integer> {

    Historialactividad findFirstByUsuarios(Usuarios usuarios);

}
