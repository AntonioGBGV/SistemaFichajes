package coredev.sistema_fichajes.repository;

import coredev.sistema_fichajes.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    List<Rol> findByNombreContainingIgnoreCase(String nombre);
}
