package coredev.sistema_fichajes.rest;

import coredev.sistema_fichajes.domain.Usuarios;
import coredev.sistema_fichajes.model.HistorialactividadDTO;
import coredev.sistema_fichajes.repos.UsuariosRepository;
import coredev.sistema_fichajes.service.HistorialactividadService;
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
@RequestMapping(value = "/api/historialactividads", produces = MediaType.APPLICATION_JSON_VALUE)
public class HistorialactividadResource {

    private final HistorialactividadService historialactividadService;
    private final UsuariosRepository usuariosRepository;

    public HistorialactividadResource(final HistorialactividadService historialactividadService,
            final UsuariosRepository usuariosRepository) {
        this.historialactividadService = historialactividadService;
        this.usuariosRepository = usuariosRepository;
    }

    @GetMapping
    public ResponseEntity<List<HistorialactividadDTO>> getAllHistorialactividads() {
        return ResponseEntity.ok(historialactividadService.findAll());
    }

    @GetMapping("/{idhistorial}")
    public ResponseEntity<HistorialactividadDTO> getHistorialactividad(
            @PathVariable(name = "idhistorial") final Integer idhistorial) {
        return ResponseEntity.ok(historialactividadService.get(idhistorial));
    }

    @PostMapping
    public ResponseEntity<Integer> createHistorialactividad(
            @RequestBody @Valid final HistorialactividadDTO historialactividadDTO) {
        final Integer createdIdhistorial = historialactividadService.create(historialactividadDTO);
        return new ResponseEntity<>(createdIdhistorial, HttpStatus.CREATED);
    }

    @PutMapping("/{idhistorial}")
    public ResponseEntity<Integer> updateHistorialactividad(
            @PathVariable(name = "idhistorial") final Integer idhistorial,
            @RequestBody @Valid final HistorialactividadDTO historialactividadDTO) {
        historialactividadService.update(idhistorial, historialactividadDTO);
        return ResponseEntity.ok(idhistorial);
    }

    @DeleteMapping("/{idhistorial}")
    public ResponseEntity<Void> deleteHistorialactividad(
            @PathVariable(name = "idhistorial") final Integer idhistorial) {
        historialactividadService.delete(idhistorial);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuariosValues")
    public ResponseEntity<Map<Integer, Integer>> getUsuariosValues() {
        return ResponseEntity.ok(usuariosRepository.findAll(Sort.by("idusuario"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Usuarios::getIdusuario, Usuarios::getIdusuario)));
    }

}
