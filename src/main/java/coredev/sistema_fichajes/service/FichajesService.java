package coredev.sistema_fichajes.service;

import coredev.sistema_fichajes.domain.Fichajes;
import coredev.sistema_fichajes.domain.Usuarios;
import coredev.sistema_fichajes.model.FichajesDTO;
import coredev.sistema_fichajes.repos.FichajesRepository;
import coredev.sistema_fichajes.repos.UsuariosRepository;
import coredev.sistema_fichajes.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class FichajesService {

    private final FichajesRepository fichajesRepository;
    private final UsuariosRepository usuariosRepository;

    public FichajesService(final FichajesRepository fichajesRepository,
            final UsuariosRepository usuariosRepository) {
        this.fichajesRepository = fichajesRepository;
        this.usuariosRepository = usuariosRepository;
    }

    public List<FichajesDTO> findAll() {
        final List<Fichajes> fichajeses = fichajesRepository.findAll(Sort.by("idfichaje"));
        return fichajeses.stream()
                .map(fichajes -> mapToDTO(fichajes, new FichajesDTO()))
                .toList();
    }

    public FichajesDTO get(final Integer idfichaje) {
        return fichajesRepository.findById(idfichaje)
                .map(fichajes -> mapToDTO(fichajes, new FichajesDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final FichajesDTO fichajesDTO) {
        final Fichajes fichajes = new Fichajes();
        mapToEntity(fichajesDTO, fichajes);
        return fichajesRepository.save(fichajes).getIdfichaje();
    }

    public void update(final Integer idfichaje, final FichajesDTO fichajesDTO) {
        final Fichajes fichajes = fichajesRepository.findById(idfichaje)
                .orElseThrow(NotFoundException::new);
        mapToEntity(fichajesDTO, fichajes);
        fichajesRepository.save(fichajes);
    }

    public void delete(final Integer idfichaje) {
        fichajesRepository.deleteById(idfichaje);
    }

    private FichajesDTO mapToDTO(final Fichajes fichajes, final FichajesDTO fichajesDTO) {
        fichajesDTO.setIdfichaje(fichajes.getIdfichaje());
        fichajesDTO.setUsuarioid(fichajes.getUsuarioid());
        fichajesDTO.setFecha(fichajes.getFecha());
        fichajesDTO.setHoraentrada(fichajes.getHoraentrada());
        fichajesDTO.setHorasalida(fichajes.getHorasalida());
        fichajesDTO.setEstado(fichajes.getEstado());
        fichajesDTO.setUsuarios(fichajes.getUsuarios() == null ? null : fichajes.getUsuarios().getIdusuario());
        return fichajesDTO;
    }

    private Fichajes mapToEntity(final FichajesDTO fichajesDTO, final Fichajes fichajes) {
        fichajes.setUsuarioid(fichajesDTO.getUsuarioid());
        fichajes.setFecha(fichajesDTO.getFecha());
        fichajes.setHoraentrada(fichajesDTO.getHoraentrada());
        fichajes.setHorasalida(fichajesDTO.getHorasalida());
        fichajes.setEstado(fichajesDTO.getEstado());
        final Usuarios usuarios = fichajesDTO.getUsuarios() == null ? null : usuariosRepository.findById(fichajesDTO.getUsuarios())
                .orElseThrow(() -> new NotFoundException("usuarios not found"));
        fichajes.setUsuarios(usuarios);
        return fichajes;
    }

}
