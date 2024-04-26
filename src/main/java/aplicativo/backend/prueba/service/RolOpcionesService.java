package aplicativo.backend.prueba.service;


import aplicativo.backend.prueba.model.entities.RolOpciones;
import aplicativo.backend.prueba.response.ResponseData;

public interface RolOpcionesService {

	
	public ResponseData findAll();
	public ResponseData findById(Integer id);
	public ResponseData save(RolOpciones rolOpciones, Integer id) ;
}
