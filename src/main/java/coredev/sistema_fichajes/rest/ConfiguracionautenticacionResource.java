package coredev.sistema_fichajes.rest;

import coredev.sistema_fichajes.model.ConfiguracionautenticacionDTO;
import coredev.sistema_fichajes.service.ConfiguracionautenticacionService;
import coredev.sistema_fichajes.util.ReferencedException;
import coredev.sistema_fichajes.util.ReferencedWarning;
import jakarta.validation.Valid;
import java.util.List;
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
@RequestMapping(value = "/api/configuracionautenticacions", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConfiguracionautenticacionResource {

    private final ConfiguracionautenticacionService configuracionautenticacionService;

    public ConfiguracionautenticacionResource(
            final ConfiguracionautenticacionService configuracionautenticacionService) {
        this.configuracionautenticacionService = configuracionautenticacionService;
    }

    @GetMapping
    public ResponseEntity<List<ConfiguracionautenticacionDTO>> getAllConfiguracionautenticacions() {
        return ResponseEntity.ok(configuracionautenticacionService.findAll());
    }

    @GetMapping("/{idautenticacion}")
    public ResponseEntity<ConfiguracionautenticacionDTO> getConfiguracionautenticacion(
            @PathVariable(name = "idautenticacion") final Integer idautenticacion) {
        return ResponseEntity.ok(configuracionautenticacionService.get(idautenticacion));
    }

    @PostMapping
    public ResponseEntity<Integer> createConfiguracionautenticacion(
            @RequestBody @Valid final ConfiguracionautenticacionDTO configuracionautenticacionDTO) {
        final Integer createdIdautenticacion = configuracionautenticacionService.create(configuracionautenticacionDTO);
        return new ResponseEntity<>(createdIdautenticacion, HttpStatus.CREATED);
    }

    @PutMapping("/{idautenticacion}")
    public ResponseEntity<Integer> updateConfiguracionautenticacion(
            @PathVariable(name = "idautenticacion") final Integer idautenticacion,
            @RequestBody @Valid final ConfiguracionautenticacionDTO configuracionautenticacionDTO) {
        configuracionautenticacionService.update(idautenticacion, configuracionautenticacionDTO);
        return ResponseEntity.ok(idautenticacion);
    }

    @DeleteMapping("/{idautenticacion}")
    public ResponseEntity<Void> deleteConfiguracionautenticacion(
            @PathVariable(name = "idautenticacion") final Integer idautenticacion) {
        final ReferencedWarning referencedWarning = configuracionautenticacionService.getReferencedWarning(idautenticacion);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        configuracionautenticacionService.delete(idautenticacion);
        return ResponseEntity.noContent().build();
    }

}
