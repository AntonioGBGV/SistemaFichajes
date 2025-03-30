package coredev.sistema_fichajes.repository;

import coredev.sistema_fichajes.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
    List<Empresa> findByNombreContainingIgnoreCase(String nombre);
}
