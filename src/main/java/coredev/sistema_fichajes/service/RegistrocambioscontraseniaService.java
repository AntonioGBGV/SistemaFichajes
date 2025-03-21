package coredev.sistema_fichajes.service;

import coredev.sistema_fichajes.domain.Registrocambioscontrasenia;
import coredev.sistema_fichajes.domain.Usuarios;
import coredev.sistema_fichajes.model.RegistrocambioscontraseniaDTO;
import coredev.sistema_fichajes.repos.RegistrocambioscontraseniaRepository;
import coredev.sistema_fichajes.repos.UsuariosRepository;
import coredev.sistema_fichajes.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class RegistrocambioscontraseniaService {

    private final RegistrocambioscontraseniaRepository registrocambioscontraseniaRepository;
    private final UsuariosRepository usuariosRepository;

    public RegistrocambioscontraseniaService(
            final RegistrocambioscontraseniaRepository registrocambioscontraseniaRepository,
            final UsuariosRepository usuariosRepository) {
        this.registrocambioscontraseniaRepository = registrocambioscontraseniaRepository;
        this.usuariosRepository = usuariosRepository;
    }

    public List<RegistrocambioscontraseniaDTO> findAll() {
        final List<Registrocambioscontrasenia> registrocambioscontrasenias = registrocambioscontraseniaRepository.findAll(Sort.by("idregistro"));
        return registrocambioscontrasenias.stream()
                .map(registrocambioscontrasenia -> mapToDTO(registrocambioscontrasenia, new RegistrocambioscontraseniaDTO()))
                .toList();
    }

    public RegistrocambioscontraseniaDTO get(final Integer idregistro) {
        return registrocambioscontraseniaRepository.findById(idregistro)
                .map(registrocambioscontrasenia -> mapToDTO(registrocambioscontrasenia, new RegistrocambioscontraseniaDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final RegistrocambioscontraseniaDTO registrocambioscontraseniaDTO) {
        final Registrocambioscontrasenia registrocambioscontrasenia = new Registrocambioscontrasenia();
        mapToEntity(registrocambioscontraseniaDTO, registrocambioscontrasenia);
        return registrocambioscontraseniaRepository.save(registrocambioscontrasenia).getIdregistro();
    }

    public void update(final Integer idregistro,
            final RegistrocambioscontraseniaDTO registrocambioscontraseniaDTO) {
        final Registrocambioscontrasenia registrocambioscontrasenia = registrocambioscontraseniaRepository.findById(idregistro)
                .orElseThrow(NotFoundException::new);
        mapToEntity(registrocambioscontraseniaDTO, registrocambioscontrasenia);
        registrocambioscontraseniaRepository.save(registrocambioscontrasenia);
    }

    public void delete(final Integer idregistro) {
        registrocambioscontraseniaRepository.deleteById(idregistro);
    }

    private RegistrocambioscontraseniaDTO mapToDTO(
            final Registrocambioscontrasenia registrocambioscontrasenia,
            final RegistrocambioscontraseniaDTO registrocambioscontraseniaDTO) {
        registrocambioscontraseniaDTO.setIdregistro(registrocambioscontrasenia.getIdregistro());
        registrocambioscontraseniaDTO.setUsuarioid(registrocambioscontrasenia.getUsuarioid());
        registrocambioscontraseniaDTO.setFechacambio(registrocambioscontrasenia.getFechacambio());
        registrocambioscontraseniaDTO.setUsuarios(registrocambioscontrasenia.getUsuarios() == null ? null : registrocambioscontrasenia.getUsuarios().getIdusuario());
        return registrocambioscontraseniaDTO;
    }

    private Registrocambioscontrasenia mapToEntity(
            final RegistrocambioscontraseniaDTO registrocambioscontraseniaDTO,
            final Registrocambioscontrasenia registrocambioscontrasenia) {
        registrocambioscontrasenia.setUsuarioid(registrocambioscontraseniaDTO.getUsuarioid());
        registrocambioscontrasenia.setFechacambio(registrocambioscontraseniaDTO.getFechacambio());
        final Usuarios usuarios = registrocambioscontraseniaDTO.getUsuarios() == null ? null : usuariosRepository.findById(registrocambioscontraseniaDTO.getUsuarios())
                .orElseThrow(() -> new NotFoundException("usuarios not found"));
        registrocambioscontrasenia.setUsuarios(usuarios);
        return registrocambioscontrasenia;
    }

}
