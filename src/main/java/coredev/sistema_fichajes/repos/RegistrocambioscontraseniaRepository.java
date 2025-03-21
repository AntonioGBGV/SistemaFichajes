package coredev.sistema_fichajes.repos;

import coredev.sistema_fichajes.domain.Registrocambioscontrasenia;
import coredev.sistema_fichajes.domain.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RegistrocambioscontraseniaRepository extends JpaRepository<Registrocambioscontrasenia, Integer> {

    Registrocambioscontrasenia findFirstByUsuarios(Usuarios usuarios);

}
