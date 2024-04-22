package aplicativo.backend.prueba.util;



import java.util.List;

import org.springframework.validation.ObjectError;

import aplicativo.backend.prueba.model.entities.Persona;

public class PersonaResponse {

	private Persona persona;
	 private String mensaje;
	 
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
	  public static PersonaResponse buildBadRequestResponse(List<ObjectError> errors) {
		    StringBuilder errorMessage = new StringBuilder("Errores de validación:");
		    for (ObjectError error : errors) {
		        errorMessage.append("\n  - ").append(error.getDefaultMessage());
		    }
		    PersonaResponse response = new PersonaResponse();
		    response.setMensaje(errorMessage.toString());
		    return response;
		}
	  
	 
	 
}
