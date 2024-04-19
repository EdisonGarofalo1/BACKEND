package aplicativo.backend.prueba.service;

import java.util.List;

import aplicativo.backend.prueba.model.entities.Usuario;
import aplicativo.backend.prueba.util.UsuarioResponse;



public interface UsuarioService {
	
	public List<Usuario> findAll();
	public Usuario findById(Integer id) throws Exception;
	public UsuarioResponse save(Usuario usuario) throws Exception;

}
