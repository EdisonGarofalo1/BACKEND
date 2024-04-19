package aplicativo.backend.prueba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aplicativo.backend.prueba.model.entities.Persona;

import aplicativo.backend.prueba.repository.PersonaRepository;
import aplicativo.backend.prueba.util.PersonaResponse;
@Service
public class PersonaServiceImp  implements PersonaService {

	@Autowired
	private  PersonaRepository personaRepository;
	@Override
	public List<Persona> findAll() {
		return (List<Persona>) personaRepository.findAll();
	}

	@Override
	public Persona findById(Integer id) throws Exception {
		try {
			return personaRepository.findById(id).orElse(null);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public PersonaResponse save(Persona persona) throws Exception {
		try {
			PersonaResponse response = new PersonaResponse();
			personaRepository.save(persona);
			response.setPersona(persona);
			response.setMensaje("Se registro con exito");
			
			return response;
		} catch (Exception e) {
			 throw new Exception("Error al intentar registrar Persona: " + e.getMessage());
		}
	}

}
