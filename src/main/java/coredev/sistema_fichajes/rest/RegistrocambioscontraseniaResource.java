package coredev.sistema_fichajes.rest;

import coredev.sistema_fichajes.domain.Usuarios;
import coredev.sistema_fichajes.model.RegistrocambioscontraseniaDTO;
import coredev.sistema_fichajes.repos.UsuariosRepository;
import coredev.sistema_fichajes.service.RegistrocambioscontraseniaService;
import coredev.sistema_fichajes.util.CustomCollectors;
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
@RequestMapping(value = "/api/registrocambioscontrasenias", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrocambioscontraseniaResource {

    private final RegistrocambioscontraseniaService registrocambioscontraseniaService;
    private final UsuariosRepository usuariosRepository;

    public RegistrocambioscontraseniaResource(
            final RegistrocambioscontraseniaService registrocambioscontraseniaService,
            final UsuariosRepository usuariosRepository) {
        this.registrocambioscontraseniaService = registrocambioscontraseniaService;
        this.usuariosRepository = usuariosRepository;
    }

    @GetMapping
    public ResponseEntity<List<RegistrocambioscontraseniaDTO>> getAllRegistrocambioscontrasenias() {
        return ResponseEntity.ok(registrocambioscontraseniaService.findAll());
    }

    @GetMapping("/{idregistro}")
    public ResponseEntity<RegistrocambioscontraseniaDTO> getRegistrocambioscontrasenia(
            @PathVariable(name = "idregistro") final Integer idregistro) {
        return ResponseEntity.ok(registrocambioscontraseniaService.get(idregistro));
    }

    @PostMapping
    public ResponseEntity<Integer> createRegistrocambioscontrasenia(
            @RequestBody @Valid final RegistrocambioscontraseniaDTO registrocambioscontraseniaDTO) {
        final Integer createdIdregistro = registrocambioscontraseniaService.create(registrocambioscontraseniaDTO);
        return new ResponseEntity<>(createdIdregistro, HttpStatus.CREATED);
    }

    @PutMapping("/{idregistro}")
    public ResponseEntity<Integer> updateRegistrocambioscontrasenia(
            @PathVariable(name = "idregistro") final Integer idregistro,
            @RequestBody @Valid final RegistrocambioscontraseniaDTO registrocambioscontraseniaDTO) {
        registrocambioscontraseniaService.update(idregistro, registrocambioscontraseniaDTO);
        return ResponseEntity.ok(idregistro);
    }

    @DeleteMapping("/{idregistro}")
    public ResponseEntity<Void> deleteRegistrocambioscontrasenia(
            @PathVariable(name = "idregistro") final Integer idregistro) {
        registrocambioscontraseniaService.delete(idregistro);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuariosValues")
    public ResponseEntity<Map<Integer, Integer>> getUsuariosValues() {
        return ResponseEntity.ok(usuariosRepository.findAll(Sort.by("idusuario"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Usuarios::getIdusuario, Usuarios::getIdusuario)));
    }

}
