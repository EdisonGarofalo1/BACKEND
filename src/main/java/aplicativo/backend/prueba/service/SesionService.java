package aplicativo.backend.prueba.service;

import java.util.List;

import aplicativo.backend.prueba.model.entities.Sesion;
import aplicativo.backend.prueba.model.entities.Usuario;
import aplicativo.backend.prueba.util.UsuarioResponse;

public interface SesionService  {
	
	public List<Sesion> findAll();
	public Sesion findById(Integer id) throws Exception;
	public UsuarioResponse login(Usuario usuario) throws Exception;
	
	public UsuarioResponse logout(Usuario usuario) throws Exception;
	

}
