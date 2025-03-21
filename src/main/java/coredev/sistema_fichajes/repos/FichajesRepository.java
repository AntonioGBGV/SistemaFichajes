package coredev.sistema_fichajes.repos;

import coredev.sistema_fichajes.domain.Fichajes;
import coredev.sistema_fichajes.domain.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FichajesRepository extends JpaRepository<Fichajes, Integer> {

    Fichajes findFirstByUsuarios(Usuarios usuarios);

}
