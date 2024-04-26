package aplicativo.backend.prueba.service;


import aplicativo.backend.prueba.model.entities.Usuario;
import aplicativo.backend.prueba.response.ResponseData;




public interface UsuarioService {
	
	public ResponseData findAll();
	public ResponseData findById(Integer id) ;
	public ResponseData save(Usuario usuario,Integer id) ;
	public ResponseData  eliminarUsuario(Integer idUsuario);

}
