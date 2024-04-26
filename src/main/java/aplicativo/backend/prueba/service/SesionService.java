package aplicativo.backend.prueba.service;


import aplicativo.backend.prueba.model.entities.Usuario;
import aplicativo.backend.prueba.response.ResponseData;


public interface SesionService  {
	

	public ResponseData login(Usuario usuario) ;
	
	public ResponseData logout(Usuario usuario) ;
	

}
