package aplicativo.backend.prueba.service;

import java.util.List;

import aplicativo.backend.prueba.model.entities.RolOpciones;

public interface RolOpcionesService {

	
	public List<RolOpciones> findAll();
	public RolOpciones findById(Integer id) throws Exception;
	public RolOpciones save(RolOpciones rolOpciones) throws Exception;
}
