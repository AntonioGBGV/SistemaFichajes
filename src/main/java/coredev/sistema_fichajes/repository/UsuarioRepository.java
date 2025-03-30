package coredev.sistema_fichajes.repository;

import coredev.sistema_fichajes.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    List<Usuario> findByNombreContainingIgnoreCase(String nombre);
}
