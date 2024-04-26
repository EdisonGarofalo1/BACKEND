package aplicativo.backend.prueba.service;

import aplicativo.backend.prueba.model.entities.Rol;
import aplicativo.backend.prueba.response.ResponseData;

public interface RolService {
	
	public ResponseData findAll();
	public ResponseData findById(Integer id) ;
	public ResponseData save(Rol rol, Integer id) ;
	
	
	

}
