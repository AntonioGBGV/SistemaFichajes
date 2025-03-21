package coredev.sistema_fichajes.model;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import coredev.sistema_fichajes.service.UsuariosService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import org.springframework.web.servlet.HandlerMapping;


/**
 * Validate that the idautenticacion value isn't taken yet.
 */
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = UsuariosConfiguracionautenticacionUnique.UsuariosConfiguracionautenticacionUniqueValidator.class
)
public @interface UsuariosConfiguracionautenticacionUnique {

    String message() default "{Exists.usuarios.configuracionautenticacion}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class UsuariosConfiguracionautenticacionUniqueValidator implements ConstraintValidator<UsuariosConfiguracionautenticacionUnique, Integer> {

        private final UsuariosService usuariosService;
        private final HttpServletRequest request;

        public UsuariosConfiguracionautenticacionUniqueValidator(
                final UsuariosService usuariosService, final HttpServletRequest request) {
            this.usuariosService = usuariosService;
            this.request = request;
        }

        @Override
        public boolean isValid(final Integer value, final ConstraintValidatorContext cvContext) {
            if (value == null) {
                // no value present
                return true;
            }
            @SuppressWarnings("unchecked") final Map<String, String> pathVariables =
                    ((Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
            final String currentId = pathVariables.get("idusuario");
            if (currentId != null && value.equals(usuariosService.get(Integer.parseInt(currentId)).getConfiguracionautenticacion())) {
                // value hasn't changed
                return true;
            }
            return !usuariosService.configuracionautenticacionExists(value);
        }

    }

}
