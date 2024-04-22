package aplicativo.backend.prueba.service;

import java.util.List;

import org.springframework.validation.BindingResult;

import aplicativo.backend.prueba.model.entities.Persona;
import aplicativo.backend.prueba.util.PersonaResponse;



public interface PersonaService {
	
	
	public List<Persona> findAll();
	public Persona findById(Integer id) throws Exception;
	public PersonaResponse save(Persona persona, BindingResult result ) throws Exception;

}
