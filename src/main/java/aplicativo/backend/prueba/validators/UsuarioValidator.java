package aplicativo.backend.prueba.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import aplicativo.backend.prueba.model.entities.Usuario;



@Component
public class UsuarioValidator  implements Validator{
	@Override
    public boolean supports(Class<?> clazz) {
        return Usuario.class.equals(clazz);
    }
	
	
	@Override
    public void validate(Object target, Errors errors) {
        Usuario usuario = (Usuario) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "field.required", "El nombre de usuario es obligatorio");

        // Validar que el nombre de usuario no contenga signos
        if (!usuario.getUserName().matches("[a-zA-Z0-9]+")) {
            errors.rejectValue("userName", "userName.invalidCharacters", "El nombre de usuario no puede contener signos");
        }

   
        
        

        // Validar que el nombre de usuario contenga al menos un número
        if (!usuario.getUserName().matches(".*\\d.*")) {
            errors.rejectValue("userName", "userName.noNumber", "El nombre de usuario debe contener al menos un número");
        }

        // Validar que el nombre de usuario contenga al menos una letra mayúscula
        if (!usuario.getUserName().matches(".*[A-Z].*")) {
            errors.rejectValue("userName", "userName.noUpperCase", "El nombre de usuario debe contener al menos una letra mayúscula");
        }

        // Validar longitud mínima y máxima del nombre de usuario
        if (usuario.getUserName().length() < 8 || usuario.getUserName().length() > 20) {
            errors.rejectValue("userName", "userName.length", "El nombre de usuario debe tener entre 8 y 20 caracteres");
        }
   
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required", "La contraseña es obligatoria");

        // Validar longitud mínima de la contraseña
        if (usuario.getPassword().length() < 8) {
            errors.rejectValue("password", "password.length", "La contraseña debe tener al menos 8 caracteres");
        }

        // Validar al menos una letra mayúscula
        if (!usuario.getPassword().matches(".*[A-Z].*")) {
            errors.rejectValue("password", "password.noUpperCase", "La contraseña debe contener al menos una letra mayúscula");
        }

        // Validar que no contenga espacios
        if (usuario.getPassword().contains(" ")) {
            errors.rejectValue("password", "password.noSpace", "La contraseña no debe contener espacios");
        }

        // Validar al menos un signo (carácter especial)
        if (!usuario.getPassword().matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            errors.rejectValue("password", "password.noSymbol", "La contraseña debe contener al menos un signo");
        }
        
      
        
        
       
    }

}
