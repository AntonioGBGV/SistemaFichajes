package coredev.sistema_fichajes.service;

import coredev.sistema_fichajes.domain.Empresas;
import coredev.sistema_fichajes.domain.Usuarios;
import coredev.sistema_fichajes.model.EmpresasDTO;
import coredev.sistema_fichajes.repos.EmpresasRepository;
import coredev.sistema_fichajes.repos.UsuariosRepository;
import coredev.sistema_fichajes.util.NotFoundException;
import coredev.sistema_fichajes.util.ReferencedWarning;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class EmpresasService {

    private final EmpresasRepository empresasRepository;
    private final UsuariosRepository usuariosRepository;

    public EmpresasService(final EmpresasRepository empresasRepository,
            final UsuariosRepository usuariosRepository) {
        this.empresasRepository = empresasRepository;
        this.usuariosRepository = usuariosRepository;
    }

    public List<EmpresasDTO> findAll() {
        final List<Empresas> empresases = empresasRepository.findAll(Sort.by("idempresa"));
        return empresases.stream()
                .map(empresas -> mapToDTO(empresas, new EmpresasDTO()))
                .toList();
    }

    public EmpresasDTO get(final Integer idempresa) {
        return empresasRepository.findById(idempresa)
                .map(empresas -> mapToDTO(empresas, new EmpresasDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final EmpresasDTO empresasDTO) {
        final Empresas empresas = new Empresas();
        mapToEntity(empresasDTO, empresas);
        return empresasRepository.save(empresas).getIdempresa();
    }

    public void update(final Integer idempresa, final EmpresasDTO empresasDTO) {
        final Empresas empresas = empresasRepository.findById(idempresa)
                .orElseThrow(NotFoundException::new);
        mapToEntity(empresasDTO, empresas);
        empresasRepository.save(empresas);
    }

    public void delete(final Integer idempresa) {
        empresasRepository.deleteById(idempresa);
    }

    private EmpresasDTO mapToDTO(final Empresas empresas, final EmpresasDTO empresasDTO) {
        empresasDTO.setIdempresa(empresas.getIdempresa());
        empresasDTO.setNombre(empresas.getNombre());
        empresasDTO.setDireccion(empresas.getDireccion());
        empresasDTO.setTelefono(empresas.getTelefono());
        empresasDTO.setEmail(empresas.getEmail());
        return empresasDTO;
    }

    private Empresas mapToEntity(final EmpresasDTO empresasDTO, final Empresas empresas) {
        empresas.setNombre(empresasDTO.getNombre());
        empresas.setDireccion(empresasDTO.getDireccion());
        empresas.setTelefono(empresasDTO.getTelefono());
        empresas.setEmail(empresasDTO.getEmail());
        return empresas;
    }

    public ReferencedWarning getReferencedWarning(final Integer idempresa) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Empresas empresas = empresasRepository.findById(idempresa)
                .orElseThrow(NotFoundException::new);
        final Usuarios empresasUsuarios = usuariosRepository.findFirstByEmpresas(empresas);
        if (empresasUsuarios != null) {
            referencedWarning.setKey("empresas.usuarios.empresas.referenced");
            referencedWarning.addParam(empresasUsuarios.getIdusuario());
            return referencedWarning;
        }
        return null;
    }

}
