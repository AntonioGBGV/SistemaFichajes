package coredev.sistema_fichajes.rest;

import coredev.sistema_fichajes.domain.Configuracionautenticacion;
import coredev.sistema_fichajes.domain.Empresas;
import coredev.sistema_fichajes.domain.Horasextras;
import coredev.sistema_fichajes.domain.Roles;
import coredev.sistema_fichajes.model.UsuariosDTO;
import coredev.sistema_fichajes.repos.ConfiguracionautenticacionRepository;
import coredev.sistema_fichajes.repos.EmpresasRepository;
import coredev.sistema_fichajes.repos.HorasextrasRepository;
import coredev.sistema_fichajes.repos.RolesRepository;
import coredev.sistema_fichajes.service.UsuariosService;
import coredev.sistema_fichajes.util.CustomCollectors;
import coredev.sistema_fichajes.util.ReferencedException;
import coredev.sistema_fichajes.util.ReferencedWarning;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/usuarioss", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuariosResource {

    private final UsuariosService usuariosService;
    private final EmpresasRepository empresasRepository;
    private final RolesRepository rolesRepository;
    private final ConfiguracionautenticacionRepository configuracionautenticacionRepository;
    private final HorasextrasRepository horasextrasRepository;

    public UsuariosResource(final UsuariosService usuariosService,
            final EmpresasRepository empresasRepository, final RolesRepository rolesRepository,
            final ConfiguracionautenticacionRepository configuracionautenticacionRepository,
            final HorasextrasRepository horasextrasRepository) {
        this.usuariosService = usuariosService;
        this.empresasRepository = empresasRepository;
        this.rolesRepository = rolesRepository;
        this.configuracionautenticacionRepository = configuracionautenticacionRepository;
        this.horasextrasRepository = horasextrasRepository;
    }

    @GetMapping
    public ResponseEntity<List<UsuariosDTO>> getAllUsuarioss() {
        return ResponseEntity.ok(usuariosService.findAll());
    }

    @GetMapping("/{idusuario}")
    public ResponseEntity<UsuariosDTO> getUsuarios(
            @PathVariable(name = "idusuario") final Integer idusuario) {
        return ResponseEntity.ok(usuariosService.get(idusuario));
    }

    @PostMapping
    public ResponseEntity<Integer> createUsuarios(
            @RequestBody @Valid final UsuariosDTO usuariosDTO) {
        final Integer createdIdusuario = usuariosService.create(usuariosDTO);
        return new ResponseEntity<>(createdIdusuario, HttpStatus.CREATED);
    }

    @PutMapping("/{idusuario}")
    public ResponseEntity<Integer> updateUsuarios(
            @PathVariable(name = "idusuario") final Integer idusuario,
            @RequestBody @Valid final UsuariosDTO usuariosDTO) {
        usuariosService.update(idusuario, usuariosDTO);
        return ResponseEntity.ok(idusuario);
    }

    @DeleteMapping("/{idusuario}")
    public ResponseEntity<Void> deleteUsuarios(
            @PathVariable(name = "idusuario") final Integer idusuario) {
        final ReferencedWarning referencedWarning = usuariosService.getReferencedWarning(idusuario);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        usuariosService.delete(idusuario);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/empresasValues")
    public ResponseEntity<Map<Integer, Integer>> getEmpresasValues() {
        return ResponseEntity.ok(empresasRepository.findAll(Sort.by("idempresa"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Empresas::getIdempresa, Empresas::getIdempresa)));
    }

    @GetMapping("/rolesValues")
    public ResponseEntity<Map<Integer, Integer>> getRolesValues() {
        return ResponseEntity.ok(rolesRepository.findAll(Sort.by("idrol"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Roles::getIdrol, Roles::getIdrol)));
    }

    @GetMapping("/configuracionautenticacionValues")
    public ResponseEntity<Map<Integer, Integer>> getConfiguracionautenticacionValues() {
        return ResponseEntity.ok(configuracionautenticacionRepository.findAll(Sort.by("idautenticacion"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Configuracionautenticacion::getIdautenticacion, Configuracionautenticacion::getIdautenticacion)));
    }

    @GetMapping("/horasextrasusuariosValues")
    public ResponseEntity<Map<Integer, Integer>> getHorasextrasusuariosValues() {
        return ResponseEntity.ok(horasextrasRepository.findAll(Sort.by("idhoraextra"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Horasextras::getIdhoraextra, Horasextras::getIdhoraextra)));
    }

}
