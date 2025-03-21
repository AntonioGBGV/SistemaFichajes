package coredev.sistema_fichajes.service;

import coredev.sistema_fichajes.domain.Historialactividad;
import coredev.sistema_fichajes.domain.Usuarios;
import coredev.sistema_fichajes.model.HistorialactividadDTO;
import coredev.sistema_fichajes.repos.HistorialactividadRepository;
import coredev.sistema_fichajes.repos.UsuariosRepository;
import coredev.sistema_fichajes.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class HistorialactividadService {

    private final HistorialactividadRepository historialactividadRepository;
    private final UsuariosRepository usuariosRepository;

    public HistorialactividadService(
            final HistorialactividadRepository historialactividadRepository,
            final UsuariosRepository usuariosRepository) {
        this.historialactividadRepository = historialactividadRepository;
        this.usuariosRepository = usuariosRepository;
    }

    public List<HistorialactividadDTO> findAll() {
        final List<Historialactividad> historialactividads = historialactividadRepository.findAll(Sort.by("idhistorial"));
        return historialactividads.stream()
                .map(historialactividad -> mapToDTO(historialactividad, new HistorialactividadDTO()))
                .toList();
    }

    public HistorialactividadDTO get(final Integer idhistorial) {
        return historialactividadRepository.findById(idhistorial)
                .map(historialactividad -> mapToDTO(historialactividad, new HistorialactividadDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final HistorialactividadDTO historialactividadDTO) {
        final Historialactividad historialactividad = new Historialactividad();
        mapToEntity(historialactividadDTO, historialactividad);
        return historialactividadRepository.save(historialactividad).getIdhistorial();
    }

    public void update(final Integer idhistorial,
            final HistorialactividadDTO historialactividadDTO) {
        final Historialactividad historialactividad = historialactividadRepository.findById(idhistorial)
                .orElseThrow(NotFoundException::new);
        mapToEntity(historialactividadDTO, historialactividad);
        historialactividadRepository.save(historialactividad);
    }

    public void delete(final Integer idhistorial) {
        historialactividadRepository.deleteById(idhistorial);
    }

    private HistorialactividadDTO mapToDTO(final Historialactividad historialactividad,
            final HistorialactividadDTO historialactividadDTO) {
        historialactividadDTO.setIdhistorial(historialactividad.getIdhistorial());
        historialactividadDTO.setUsuarioid(historialactividad.getUsuarioid());
        historialactividadDTO.setAccion(historialactividad.getAccion());
        historialactividadDTO.setEntidadafectada(historialactividad.getEntidadafectada());
        historialactividadDTO.setDescripcion(historialactividad.getDescripcion());
        historialactividadDTO.setFecha(historialactividad.getFecha());
        historialactividadDTO.setUsuarios(historialactividad.getUsuarios() == null ? null : historialactividad.getUsuarios().getIdusuario());
        return historialactividadDTO;
    }

    private Historialactividad mapToEntity(final HistorialactividadDTO historialactividadDTO,
            final Historialactividad historialactividad) {
        historialactividad.setUsuarioid(historialactividadDTO.getUsuarioid());
        historialactividad.setAccion(historialactividadDTO.getAccion());
        historialactividad.setEntidadafectada(historialactividadDTO.getEntidadafectada());
        historialactividad.setDescripcion(historialactividadDTO.getDescripcion());
        historialactividad.setFecha(historialactividadDTO.getFecha());
        final Usuarios usuarios = historialactividadDTO.getUsuarios() == null ? null : usuariosRepository.findById(historialactividadDTO.getUsuarios())
                .orElseThrow(() -> new NotFoundException("usuarios not found"));
        historialactividad.setUsuarios(usuarios);
        return historialactividad;
    }

}
