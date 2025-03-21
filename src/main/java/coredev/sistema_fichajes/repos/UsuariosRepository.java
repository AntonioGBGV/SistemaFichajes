package coredev.sistema_fichajes.repos;

import coredev.sistema_fichajes.domain.Configuracionautenticacion;
import coredev.sistema_fichajes.domain.Empresas;
import coredev.sistema_fichajes.domain.Horasextras;
import coredev.sistema_fichajes.domain.Roles;
import coredev.sistema_fichajes.domain.Usuarios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {

    Usuarios findFirstByEmpresas(Empresas empresas);

    Usuarios findFirstByRoles(Roles roles);

    Usuarios findFirstByConfiguracionautenticacion(
            Configuracionautenticacion configuracionautenticacion);

    Usuarios findFirstByHorasextrasusuarios(Horasextras horasextras);

    List<Usuarios> findAllByRoles(Roles roles);

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByConfiguracionautenticacionIdautenticacion(Integer idautenticacion);

}
