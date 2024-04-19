package aplicativo.backend.prueba.validators;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import aplicativo.backend.prueba.model.entities.Persona;




@Component
public class PersonaValidator  implements Validator{

	  @Override
	    public boolean supports(Class<?> clazz) {
	        return Persona.class.equals(clazz);
	    }
	  
	  @Override
	    public void validate(Object target, Errors errors) {
		  Persona persona = (Persona) target;
		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "identificacion", "identificacion.required", "La identificacion es obligatorio");
	            
	      

	        // Validar longitud de la identificación
	        if (persona.getIdentificacion().length() != 10) {
	            errors.rejectValue("identificacion", "identificacion.length", "La identificación debe tener exactamente 10 dígitos");
	        }

	        // Validar que la identificación contenga solo números
	        if (!persona.getIdentificacion().matches("[0-9]+")) {
	            errors.rejectValue("identificacion", "identificacion.invalid", "La identificación debe contener solo números");
	        }

	        // Validar que no tenga un número repetido cuatro veces seguidas
	        if (persona.getIdentificacion().matches(".*(\\d)\\1{3}.*")) {
	            errors.rejectValue("identificacion", "identificacion.repeatedNumbers", "La identificación no puede tener cuatro números iguales seguidos");
	        }
	        
		  
	  }
	  
	  
}

