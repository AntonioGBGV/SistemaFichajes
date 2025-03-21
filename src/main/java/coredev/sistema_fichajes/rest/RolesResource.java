package coredev.sistema_fichajes.rest;

import coredev.sistema_fichajes.model.RolesDTO;
import coredev.sistema_fichajes.service.RolesService;
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
@RequestMapping(value = "/api/roless", produces = MediaType.APPLICATION_JSON_VALUE)
public class RolesResource {

    private final RolesService rolesService;

    public RolesResource(final RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping
    public ResponseEntity<List<RolesDTO>> getAllRoless() {
        return ResponseEntity.ok(rolesService.findAll());
    }

    @GetMapping("/{idrol}")
    public ResponseEntity<RolesDTO> getRoles(@PathVariable(name = "idrol") final Integer idrol) {
        return ResponseEntity.ok(rolesService.get(idrol));
    }

    @PostMapping
    public ResponseEntity<Integer> createRoles(@RequestBody @Valid final RolesDTO rolesDTO) {
        final Integer createdIdrol = rolesService.create(rolesDTO);
        return new ResponseEntity<>(createdIdrol, HttpStatus.CREATED);
    }

    @PutMapping("/{idrol}")
    public ResponseEntity<Integer> updateRoles(@PathVariable(name = "idrol") final Integer idrol,
            @RequestBody @Valid final RolesDTO rolesDTO) {
        rolesService.update(idrol, rolesDTO);
        return ResponseEntity.ok(idrol);
    }

    @DeleteMapping("/{idrol}")
    public ResponseEntity<Void> deleteRoles(@PathVariable(name = "idrol") final Integer idrol) {
        rolesService.delete(idrol);
        return ResponseEntity.noContent().build();
    }

}
