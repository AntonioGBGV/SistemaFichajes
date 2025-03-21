package coredev.sistema_fichajes.service;

import coredev.sistema_fichajes.domain.Configuracionautenticacion;
import coredev.sistema_fichajes.domain.Empresas;
import coredev.sistema_fichajes.domain.Fichajes;
import coredev.sistema_fichajes.domain.Historialactividad;
import coredev.sistema_fichajes.domain.Horasextras;
import coredev.sistema_fichajes.domain.Registrocambioscontrasenia;
import coredev.sistema_fichajes.domain.Roles;
import coredev.sistema_fichajes.domain.Usuarios;
import coredev.sistema_fichajes.model.UsuariosDTO;
import coredev.sistema_fichajes.repos.ConfiguracionautenticacionRepository;
import coredev.sistema_fichajes.repos.EmpresasRepository;
import coredev.sistema_fichajes.repos.FichajesRepository;
import coredev.sistema_fichajes.repos.HistorialactividadRepository;
import coredev.sistema_fichajes.repos.HorasextrasRepository;
import coredev.sistema_fichajes.repos.RegistrocambioscontraseniaRepository;
import coredev.sistema_fichajes.repos.RolesRepository;
import coredev.sistema_fichajes.repos.UsuariosRepository;
import coredev.sistema_fichajes.util.NotFoundException;
import coredev.sistema_fichajes.util.ReferencedWarning;
import jakarta.transaction.Transactional;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UsuariosService {

    private final UsuariosRepository usuariosRepository;
    private final EmpresasRepository empresasRepository;
    private final RolesRepository rolesRepository;
    private final ConfiguracionautenticacionRepository configuracionautenticacionRepository;
    private final HorasextrasRepository horasextrasRepository;
    private final FichajesRepository fichajesRepository;
    private final RegistrocambioscontraseniaRepository registrocambioscontraseniaRepository;
    private final HistorialactividadRepository historialactividadRepository;

    public UsuariosService(final UsuariosRepository usuariosRepository,
            final EmpresasRepository empresasRepository, final RolesRepository rolesRepository,
            final ConfiguracionautenticacionRepository configuracionautenticacionRepository,
            final HorasextrasRepository horasextrasRepository,
            final FichajesRepository fichajesRepository,
            final RegistrocambioscontraseniaRepository registrocambioscontraseniaRepository,
            final HistorialactividadRepository historialactividadRepository) {
        this.usuariosRepository = usuariosRepository;
        this.empresasRepository = empresasRepository;
        this.rolesRepository = rolesRepository;
        this.configuracionautenticacionRepository = configuracionautenticacionRepository;
        this.horasextrasRepository = horasextrasRepository;
        this.fichajesRepository = fichajesRepository;
        this.registrocambioscontraseniaRepository = registrocambioscontraseniaRepository;
        this.historialactividadRepository = historialactividadRepository;
    }

    public List<UsuariosDTO> findAll() {
        final List<Usuarios> usuarioses = usuariosRepository.findAll(Sort.by("idusuario"));
        return usuarioses.stream()
                .map(usuarios -> mapToDTO(usuarios, new UsuariosDTO()))
                .toList();
    }

    public UsuariosDTO get(final Integer idusuario) {
        return usuariosRepository.findById(idusuario)
                .map(usuarios -> mapToDTO(usuarios, new UsuariosDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final UsuariosDTO usuariosDTO) {
        final Usuarios usuarios = new Usuarios();
        mapToEntity(usuariosDTO, usuarios);
        return usuariosRepository.save(usuarios).getIdusuario();
    }

    public void update(final Integer idusuario, final UsuariosDTO usuariosDTO) {
        final Usuarios usuarios = usuariosRepository.findById(idusuario)
                .orElseThrow(NotFoundException::new);
        mapToEntity(usuariosDTO, usuarios);
        usuariosRepository.save(usuarios);
    }

    public void delete(final Integer idusuario) {
        usuariosRepository.deleteById(idusuario);
    }

    private UsuariosDTO mapToDTO(final Usuarios usuarios, final UsuariosDTO usuariosDTO) {
        usuariosDTO.setIdusuario(usuarios.getIdusuario());
        usuariosDTO.setNombre(usuarios.getNombre());
        usuariosDTO.setApellidos(usuarios.getApellidos());
        usuariosDTO.setEmail(usuarios.getEmail());
        usuariosDTO.setPassword(usuarios.getPassword());
        usuariosDTO.setActivo(usuarios.getActivo());
        usuariosDTO.setPrimeracceso(usuarios.getPrimeracceso());
        usuariosDTO.setEmpresas(usuarios.getEmpresas() == null ? null : usuarios.getEmpresas().getIdempresa());
        usuariosDTO.setRoles(usuarios.getRoles().stream()
                .map(roles -> roles.getIdrol())
                .toList());
        usuariosDTO.setConfiguracionautenticacion(usuarios.getConfiguracionautenticacion() == null ? null : usuarios.getConfiguracionautenticacion().getIdautenticacion());
        usuariosDTO.setHorasextrasusuarios(usuarios.getHorasextrasusuarios() == null ? null : usuarios.getHorasextrasusuarios().getIdhoraextra());
        return usuariosDTO;
    }

    private Usuarios mapToEntity(final UsuariosDTO usuariosDTO, final Usuarios usuarios) {
        usuarios.setNombre(usuariosDTO.getNombre());
        usuarios.setApellidos(usuariosDTO.getApellidos());
        usuarios.setEmail(usuariosDTO.getEmail());
        usuarios.setPassword(usuariosDTO.getPassword());
        usuarios.setActivo(usuariosDTO.getActivo());
        usuarios.setPrimeracceso(usuariosDTO.getPrimeracceso());
        final Empresas empresas = usuariosDTO.getEmpresas() == null ? null : empresasRepository.findById(usuariosDTO.getEmpresas())
                .orElseThrow(() -> new NotFoundException("empresas not found"));
        usuarios.setEmpresas(empresas);
        final List<Roles> roles = rolesRepository.findAllById(
                usuariosDTO.getRoles() == null ? Collections.emptyList() : usuariosDTO.getRoles());
        if (roles.size() != (usuariosDTO.getRoles() == null ? 0 : usuariosDTO.getRoles().size())) {
            throw new NotFoundException("one of roles not found");
        }
        usuarios.setRoles(new HashSet<>(roles));
        final Configuracionautenticacion configuracionautenticacion = usuariosDTO.getConfiguracionautenticacion() == null ? null : configuracionautenticacionRepository.findById(usuariosDTO.getConfiguracionautenticacion())
                .orElseThrow(() -> new NotFoundException("configuracionautenticacion not found"));
        usuarios.setConfiguracionautenticacion(configuracionautenticacion);
        final Horasextras horasextrasusuarios = usuariosDTO.getHorasextrasusuarios() == null ? null : horasextrasRepository.findById(usuariosDTO.getHorasextrasusuarios())
                .orElseThrow(() -> new NotFoundException("horasextrasusuarios not found"));
        usuarios.setHorasextrasusuarios(horasextrasusuarios);
        return usuarios;
    }

    public boolean emailExists(final String email) {
        return usuariosRepository.existsByEmailIgnoreCase(email);
    }

    public boolean configuracionautenticacionExists(final Integer idautenticacion) {
        return usuariosRepository.existsByConfiguracionautenticacionIdautenticacion(idautenticacion);
    }

    public ReferencedWarning getReferencedWarning(final Integer idusuario) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Usuarios usuarios = usuariosRepository.findById(idusuario)
                .orElseThrow(NotFoundException::new);
        final Fichajes usuariosFichajes = fichajesRepository.findFirstByUsuarios(usuarios);
        if (usuariosFichajes != null) {
            referencedWarning.setKey("usuarios.fichajes.usuarios.referenced");
            referencedWarning.addParam(usuariosFichajes.getIdfichaje());
            return referencedWarning;
        }
        final Horasextras usuariosHorasextras = horasextrasRepository.findFirstByUsuarios(usuarios);
        if (usuariosHorasextras != null) {
            referencedWarning.setKey("usuarios.horasextras.usuarios.referenced");
            referencedWarning.addParam(usuariosHorasextras.getIdhoraextra());
            return referencedWarning;
        }
        final Registrocambioscontrasenia usuariosRegistrocambioscontrasenia = registrocambioscontraseniaRepository.findFirstByUsuarios(usuarios);
        if (usuariosRegistrocambioscontrasenia != null) {
            referencedWarning.setKey("usuarios.registrocambioscontrasenia.usuarios.referenced");
            referencedWarning.addParam(usuariosRegistrocambioscontrasenia.getIdregistro());
            return referencedWarning;
        }
        final Historialactividad usuariosHistorialactividad = historialactividadRepository.findFirstByUsuarios(usuarios);
        if (usuariosHistorialactividad != null) {
            referencedWarning.setKey("usuarios.historialactividad.usuarios.referenced");
            referencedWarning.addParam(usuariosHistorialactividad.getIdhistorial());
            return referencedWarning;
        }
        return null;
    }

}
