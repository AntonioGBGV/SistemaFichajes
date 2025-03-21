package coredev.sistema_fichajes.service;

import coredev.sistema_fichajes.domain.Roles;
import coredev.sistema_fichajes.model.RolesDTO;
import coredev.sistema_fichajes.repos.RolesRepository;
import coredev.sistema_fichajes.repos.UsuariosRepository;
import coredev.sistema_fichajes.util.NotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class RolesService {

    private final RolesRepository rolesRepository;
    private final UsuariosRepository usuariosRepository;

    public RolesService(final RolesRepository rolesRepository,
            final UsuariosRepository usuariosRepository) {
        this.rolesRepository = rolesRepository;
        this.usuariosRepository = usuariosRepository;
    }

    public List<RolesDTO> findAll() {
        final List<Roles> roleses = rolesRepository.findAll(Sort.by("idrol"));
        return roleses.stream()
                .map(roles -> mapToDTO(roles, new RolesDTO()))
                .toList();
    }

    public RolesDTO get(final Integer idrol) {
        return rolesRepository.findById(idrol)
                .map(roles -> mapToDTO(roles, new RolesDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final RolesDTO rolesDTO) {
        final Roles roles = new Roles();
        mapToEntity(rolesDTO, roles);
        return rolesRepository.save(roles).getIdrol();
    }

    public void update(final Integer idrol, final RolesDTO rolesDTO) {
        final Roles roles = rolesRepository.findById(idrol)
                .orElseThrow(NotFoundException::new);
        mapToEntity(rolesDTO, roles);
        rolesRepository.save(roles);
    }

    public void delete(final Integer idrol) {
        final Roles roles = rolesRepository.findById(idrol)
                .orElseThrow(NotFoundException::new);
        // remove many-to-many relations at owning side
        usuariosRepository.findAllByRoles(roles)
                .forEach(usuarios -> usuarios.getRoles().remove(roles));
        rolesRepository.delete(roles);
    }

    private RolesDTO mapToDTO(final Roles roles, final RolesDTO rolesDTO) {
        rolesDTO.setIdrol(roles.getIdrol());
        rolesDTO.setNombre(roles.getNombre());
        rolesDTO.setDescripcion(roles.getDescripcion());
        return rolesDTO;
    }

    private Roles mapToEntity(final RolesDTO rolesDTO, final Roles roles) {
        roles.setNombre(rolesDTO.getNombre());
        roles.setDescripcion(rolesDTO.getDescripcion());
        return roles;
    }

}
