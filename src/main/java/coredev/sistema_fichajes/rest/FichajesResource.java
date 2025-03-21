package coredev.sistema_fichajes.rest;

import coredev.sistema_fichajes.domain.Usuarios;
import coredev.sistema_fichajes.model.FichajesDTO;
import coredev.sistema_fichajes.repos.UsuariosRepository;
import coredev.sistema_fichajes.service.FichajesService;
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
@RequestMapping(value = "/api/fichajess", produces = MediaType.APPLICATION_JSON_VALUE)
public class FichajesResource {

    private final FichajesService fichajesService;
    private final UsuariosRepository usuariosRepository;

    public FichajesResource(final FichajesService fichajesService,
            final UsuariosRepository usuariosRepository) {
        this.fichajesService = fichajesService;
        this.usuariosRepository = usuariosRepository;
    }

    @GetMapping
    public ResponseEntity<List<FichajesDTO>> getAllFichajess() {
        return ResponseEntity.ok(fichajesService.findAll());
    }

    @GetMapping("/{idfichaje}")
    public ResponseEntity<FichajesDTO> getFichajes(
            @PathVariable(name = "idfichaje") final Integer idfichaje) {
        return ResponseEntity.ok(fichajesService.get(idfichaje));
    }

    @PostMapping
    public ResponseEntity<Integer> createFichajes(
            @RequestBody @Valid final FichajesDTO fichajesDTO) {
        final Integer createdIdfichaje = fichajesService.create(fichajesDTO);
        return new ResponseEntity<>(createdIdfichaje, HttpStatus.CREATED);
    }

    @PutMapping("/{idfichaje}")
    public ResponseEntity<Integer> updateFichajes(
            @PathVariable(name = "idfichaje") final Integer idfichaje,
            @RequestBody @Valid final FichajesDTO fichajesDTO) {
        fichajesService.update(idfichaje, fichajesDTO);
        return ResponseEntity.ok(idfichaje);
    }

    @DeleteMapping("/{idfichaje}")
    public ResponseEntity<Void> deleteFichajes(
            @PathVariable(name = "idfichaje") final Integer idfichaje) {
        fichajesService.delete(idfichaje);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuariosValues")
    public ResponseEntity<Map<Integer, Integer>> getUsuariosValues() {
        return ResponseEntity.ok(usuariosRepository.findAll(Sort.by("idusuario"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Usuarios::getIdusuario, Usuarios::getIdusuario)));
    }

}
