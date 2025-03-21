package coredev.sistema_fichajes.rest;

import coredev.sistema_fichajes.domain.Usuarios;
import coredev.sistema_fichajes.model.HorasextrasDTO;
import coredev.sistema_fichajes.repos.UsuariosRepository;
import coredev.sistema_fichajes.service.HorasextrasService;
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
@RequestMapping(value = "/api/horasextrass", produces = MediaType.APPLICATION_JSON_VALUE)
public class HorasextrasResource {

    private final HorasextrasService horasextrasService;
    private final UsuariosRepository usuariosRepository;

    public HorasextrasResource(final HorasextrasService horasextrasService,
            final UsuariosRepository usuariosRepository) {
        this.horasextrasService = horasextrasService;
        this.usuariosRepository = usuariosRepository;
    }

    @GetMapping
    public ResponseEntity<List<HorasextrasDTO>> getAllHorasextrass() {
        return ResponseEntity.ok(horasextrasService.findAll());
    }

    @GetMapping("/{idhoraextra}")
    public ResponseEntity<HorasextrasDTO> getHorasextras(
            @PathVariable(name = "idhoraextra") final Integer idhoraextra) {
        return ResponseEntity.ok(horasextrasService.get(idhoraextra));
    }

    @PostMapping
    public ResponseEntity<Integer> createHorasextras(
            @RequestBody @Valid final HorasextrasDTO horasextrasDTO) {
        final Integer createdIdhoraextra = horasextrasService.create(horasextrasDTO);
        return new ResponseEntity<>(createdIdhoraextra, HttpStatus.CREATED);
    }

    @PutMapping("/{idhoraextra}")
    public ResponseEntity<Integer> updateHorasextras(
            @PathVariable(name = "idhoraextra") final Integer idhoraextra,
            @RequestBody @Valid final HorasextrasDTO horasextrasDTO) {
        horasextrasService.update(idhoraextra, horasextrasDTO);
        return ResponseEntity.ok(idhoraextra);
    }

    @DeleteMapping("/{idhoraextra}")
    public ResponseEntity<Void> deleteHorasextras(
            @PathVariable(name = "idhoraextra") final Integer idhoraextra) {
        final ReferencedWarning referencedWarning = horasextrasService.getReferencedWarning(idhoraextra);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        horasextrasService.delete(idhoraextra);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuariosValues")
    public ResponseEntity<Map<Integer, Integer>> getUsuariosValues() {
        return ResponseEntity.ok(usuariosRepository.findAll(Sort.by("idusuario"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Usuarios::getIdusuario, Usuarios::getIdusuario)));
    }

}
