package coredev.sistema_fichajes.service;

import coredev.sistema_fichajes.domain.Configuracionautenticacion;
import coredev.sistema_fichajes.domain.Usuarios;
import coredev.sistema_fichajes.model.ConfiguracionautenticacionDTO;
import coredev.sistema_fichajes.repos.ConfiguracionautenticacionRepository;
import coredev.sistema_fichajes.repos.UsuariosRepository;
import coredev.sistema_fichajes.util.NotFoundException;
import coredev.sistema_fichajes.util.ReferencedWarning;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ConfiguracionautenticacionService {

    private final ConfiguracionautenticacionRepository configuracionautenticacionRepository;
    private final UsuariosRepository usuariosRepository;

    public ConfiguracionautenticacionService(
            final ConfiguracionautenticacionRepository configuracionautenticacionRepository,
            final UsuariosRepository usuariosRepository) {
        this.configuracionautenticacionRepository = configuracionautenticacionRepository;
        this.usuariosRepository = usuariosRepository;
    }

    public List<ConfiguracionautenticacionDTO> findAll() {
        final List<Configuracionautenticacion> configuracionautenticacions = configuracionautenticacionRepository.findAll(Sort.by("idautenticacion"));
        return configuracionautenticacions.stream()
                .map(configuracionautenticacion -> mapToDTO(configuracionautenticacion, new ConfiguracionautenticacionDTO()))
                .toList();
    }

    public ConfiguracionautenticacionDTO get(final Integer idautenticacion) {
        return configuracionautenticacionRepository.findById(idautenticacion)
                .map(configuracionautenticacion -> mapToDTO(configuracionautenticacion, new ConfiguracionautenticacionDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final ConfiguracionautenticacionDTO configuracionautenticacionDTO) {
        final Configuracionautenticacion configuracionautenticacion = new Configuracionautenticacion();
        mapToEntity(configuracionautenticacionDTO, configuracionautenticacion);
        return configuracionautenticacionRepository.save(configuracionautenticacion).getIdautenticacion();
    }

    public void update(final Integer idautenticacion,
            final ConfiguracionautenticacionDTO configuracionautenticacionDTO) {
        final Configuracionautenticacion configuracionautenticacion = configuracionautenticacionRepository.findById(idautenticacion)
                .orElseThrow(NotFoundException::new);
        mapToEntity(configuracionautenticacionDTO, configuracionautenticacion);
        configuracionautenticacionRepository.save(configuracionautenticacion);
    }

    public void delete(final Integer idautenticacion) {
        configuracionautenticacionRepository.deleteById(idautenticacion);
    }

    private ConfiguracionautenticacionDTO mapToDTO(
            final Configuracionautenticacion configuracionautenticacion,
            final ConfiguracionautenticacionDTO configuracionautenticacionDTO) {
        configuracionautenticacionDTO.setIdautenticacion(configuracionautenticacion.getIdautenticacion());
        configuracionautenticacionDTO.setUsuarioid(configuracionautenticacion.getUsuarioid());
        configuracionautenticacionDTO.setCodigosecreto(configuracionautenticacion.getCodigosecreto());
        configuracionautenticacionDTO.setActivado(configuracionautenticacion.getActivado());
        return configuracionautenticacionDTO;
    }

    private Configuracionautenticacion mapToEntity(
            final ConfiguracionautenticacionDTO configuracionautenticacionDTO,
            final Configuracionautenticacion configuracionautenticacion) {
        configuracionautenticacion.setUsuarioid(configuracionautenticacionDTO.getUsuarioid());
        configuracionautenticacion.setCodigosecreto(configuracionautenticacionDTO.getCodigosecreto());
        configuracionautenticacion.setActivado(configuracionautenticacionDTO.getActivado());
        return configuracionautenticacion;
    }

    public ReferencedWarning getReferencedWarning(final Integer idautenticacion) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Configuracionautenticacion configuracionautenticacion = configuracionautenticacionRepository.findById(idautenticacion)
                .orElseThrow(NotFoundException::new);
        final Usuarios configuracionautenticacionUsuarios = usuariosRepository.findFirstByConfiguracionautenticacion(configuracionautenticacion);
        if (configuracionautenticacionUsuarios != null) {
            referencedWarning.setKey("configuracionautenticacion.usuarios.configuracionautenticacion.referenced");
            referencedWarning.addParam(configuracionautenticacionUsuarios.getIdusuario());
            return referencedWarning;
        }
        return null;
    }

}
