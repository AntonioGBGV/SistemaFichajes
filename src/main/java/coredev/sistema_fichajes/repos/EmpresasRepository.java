package coredev.sistema_fichajes.repos;

import coredev.sistema_fichajes.domain.Empresas;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmpresasRepository extends JpaRepository<Empresas, Integer> {
}
