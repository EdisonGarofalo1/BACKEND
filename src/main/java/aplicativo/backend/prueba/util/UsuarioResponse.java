package aplicativo.backend.prueba.util;

import java.util.List;

import org.springframework.validation.ObjectError;

import aplicativo.backend.prueba.model.entities.Usuario;

public class UsuarioResponse {
	
	 private Usuario usuario;
	    private String mensaje;

		public String getMensaje() {
			return mensaje;
		}

		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}

		public Usuario getUsuario() {
			return usuario;
		}

		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}
	    
	    
		  public static UsuarioResponse buildBadRequestUsuarioResponse(List<ObjectError> errors) {
			    StringBuilder errorMessage = new StringBuilder("Errores de validación:");
			    for (ObjectError error : errors) {
			        errorMessage.append("\n  - ").append(error.getDefaultMessage());
			    }
			    UsuarioResponse response = new UsuarioResponse();
			    response.setMensaje(errorMessage.toString());
			    return response;
			}
	    
	    
}
