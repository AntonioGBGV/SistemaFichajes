package coredev.sistema_fichajes.repos;

import coredev.sistema_fichajes.domain.Horasextras;
import coredev.sistema_fichajes.domain.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HorasextrasRepository extends JpaRepository<Horasextras, Integer> {

    Horasextras findFirstByUsuarios(Usuarios usuarios);

}
