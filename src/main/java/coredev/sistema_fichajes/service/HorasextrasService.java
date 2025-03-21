package coredev.sistema_fichajes.service;

import coredev.sistema_fichajes.domain.Horasextras;
import coredev.sistema_fichajes.domain.Usuarios;
import coredev.sistema_fichajes.model.HorasextrasDTO;
import coredev.sistema_fichajes.repos.HorasextrasRepository;
import coredev.sistema_fichajes.repos.UsuariosRepository;
import coredev.sistema_fichajes.util.NotFoundException;
import coredev.sistema_fichajes.util.ReferencedWarning;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class HorasextrasService {

    private final HorasextrasRepository horasextrasRepository;
    private final UsuariosRepository usuariosRepository;

    public HorasextrasService(final HorasextrasRepository horasextrasRepository,
            final UsuariosRepository usuariosRepository) {
        this.horasextrasRepository = horasextrasRepository;
        this.usuariosRepository = usuariosRepository;
    }

    public List<HorasextrasDTO> findAll() {
        final List<Horasextras> horasextrases = horasextrasRepository.findAll(Sort.by("idhoraextra"));
        return horasextrases.stream()
                .map(horasextras -> mapToDTO(horasextras, new HorasextrasDTO()))
                .toList();
    }

    public HorasextrasDTO get(final Integer idhoraextra) {
        return horasextrasRepository.findById(idhoraextra)
                .map(horasextras -> mapToDTO(horasextras, new HorasextrasDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final HorasextrasDTO horasextrasDTO) {
        final Horasextras horasextras = new Horasextras();
        mapToEntity(horasextrasDTO, horasextras);
        return horasextrasRepository.save(horasextras).getIdhoraextra();
    }

    public void update(final Integer idhoraextra, final HorasextrasDTO horasextrasDTO) {
        final Horasextras horasextras = horasextrasRepository.findById(idhoraextra)
                .orElseThrow(NotFoundException::new);
        mapToEntity(horasextrasDTO, horasextras);
        horasextrasRepository.save(horasextras);
    }

    public void delete(final Integer idhoraextra) {
        horasextrasRepository.deleteById(idhoraextra);
    }

    private HorasextrasDTO mapToDTO(final Horasextras horasextras,
            final HorasextrasDTO horasextrasDTO) {
        horasextrasDTO.setIdhoraextra(horasextras.getIdhoraextra());
        horasextrasDTO.setUsuarioid(horasextras.getUsuarioid());
        horasextrasDTO.setFecha(horasextras.getFecha());
        horasextrasDTO.setHorassolicitadas(horasextras.getHorassolicitadas());
        horasextrasDTO.setHorasaprobadas(horasextras.getHorasaprobadas());
        horasextrasDTO.setMotivo(horasextras.getMotivo());
        horasextrasDTO.setEstado(horasextras.getEstado());
        horasextrasDTO.setAprobadopor(horasextras.getAprobadopor());
        horasextrasDTO.setUsuarios(horasextras.getUsuarios() == null ? null : horasextras.getUsuarios().getIdusuario());
        return horasextrasDTO;
    }

    private Horasextras mapToEntity(final HorasextrasDTO horasextrasDTO,
            final Horasextras horasextras) {
        horasextras.setUsuarioid(horasextrasDTO.getUsuarioid());
        horasextras.setFecha(horasextrasDTO.getFecha());
        horasextras.setHorassolicitadas(horasextrasDTO.getHorassolicitadas());
        horasextras.setHorasaprobadas(horasextrasDTO.getHorasaprobadas());
        horasextras.setMotivo(horasextrasDTO.getMotivo());
        horasextras.setEstado(horasextrasDTO.getEstado());
        horasextras.setAprobadopor(horasextrasDTO.getAprobadopor());
        final Usuarios usuarios = horasextrasDTO.getUsuarios() == null ? null : usuariosRepository.findById(horasextrasDTO.getUsuarios())
                .orElseThrow(() -> new NotFoundException("usuarios not found"));
        horasextras.setUsuarios(usuarios);
        return horasextras;
    }

    public ReferencedWarning getReferencedWarning(final Integer idhoraextra) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Horasextras horasextras = horasextrasRepository.findById(idhoraextra)
                .orElseThrow(NotFoundException::new);
        final Usuarios horasextrasusuariosUsuarios = usuariosRepository.findFirstByHorasextrasusuarios(horasextras);
        if (horasextrasusuariosUsuarios != null) {
            referencedWarning.setKey("horasextras.usuarios.horasextrasusuarios.referenced");
            referencedWarning.addParam(horasextrasusuariosUsuarios.getIdusuario());
            return referencedWarning;
        }
        return null;
    }

}
