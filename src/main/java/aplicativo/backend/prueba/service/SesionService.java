package aplicativo.backend.prueba.service;


import aplicativo.backend.prueba.model.entities.Usuario;
import aplicativo.backend.prueba.util.UsuarioResponse;

public interface SesionService  {
	

	public UsuarioResponse login(Usuario usuario) throws Exception;
	
	public UsuarioResponse logout(Usuario usuario) throws Exception;
	

}
