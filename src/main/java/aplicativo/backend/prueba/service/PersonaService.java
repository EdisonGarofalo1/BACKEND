package aplicativo.backend.prueba.service;



import aplicativo.backend.prueba.model.entities.Persona;
import aplicativo.backend.prueba.response.ResponseData;




public interface PersonaService {
	
	
	public ResponseData findAll();
	public ResponseData findById(Integer id);
	public ResponseData save(Persona persona ,Integer id);
	
}

