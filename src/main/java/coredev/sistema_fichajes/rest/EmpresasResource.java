package coredev.sistema_fichajes.rest;

import coredev.sistema_fichajes.model.EmpresasDTO;
import coredev.sistema_fichajes.service.EmpresasService;
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
@RequestMapping(value = "/api/empresass", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmpresasResource {

    private final EmpresasService empresasService;

    public EmpresasResource(final EmpresasService empresasService) {
        this.empresasService = empresasService;
    }

    @GetMapping
    public ResponseEntity<List<EmpresasDTO>> getAllEmpresass() {
        return ResponseEntity.ok(empresasService.findAll());
    }

    @GetMapping("/{idempresa}")
    public ResponseEntity<EmpresasDTO> getEmpresas(
            @PathVariable(name = "idempresa") final Integer idempresa) {
        return ResponseEntity.ok(empresasService.get(idempresa));
    }

    @PostMapping
    public ResponseEntity<Integer> createEmpresas(
            @RequestBody @Valid final EmpresasDTO empresasDTO) {
        final Integer createdIdempresa = empresasService.create(empresasDTO);
        return new ResponseEntity<>(createdIdempresa, HttpStatus.CREATED);
    }

    @PutMapping("/{idempresa}")
    public ResponseEntity<Integer> updateEmpresas(
            @PathVariable(name = "idempresa") final Integer idempresa,
            @RequestBody @Valid final EmpresasDTO empresasDTO) {
        empresasService.update(idempresa, empresasDTO);
        return ResponseEntity.ok(idempresa);
    }

    @DeleteMapping("/{idempresa}")
    public ResponseEntity<Void> deleteEmpresas(
            @PathVariable(name = "idempresa") final Integer idempresa) {
        final ReferencedWarning referencedWarning = empresasService.getReferencedWarning(idempresa);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        empresasService.delete(idempresa);
        return ResponseEntity.noContent().build();
    }

}
