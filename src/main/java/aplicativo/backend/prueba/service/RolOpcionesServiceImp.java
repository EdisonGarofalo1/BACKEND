package aplicativo.backend.prueba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import aplicativo.backend.prueba.model.entities.RolOpciones;
import aplicativo.backend.prueba.repository.RolOpcionesRepository;
@Service
public class RolOpcionesServiceImp  implements RolOpcionesService{

	
	@Autowired
	private RolOpcionesRepository rolOpcionesRepository;
	
	@Override
	public List<RolOpciones> findAll() {
		return (List<RolOpciones>) rolOpcionesRepository.findAll();
	}

	@Override
	public RolOpciones findById(Integer id) throws Exception {
		try {
			return rolOpcionesRepository.findById(id).orElse(null);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public RolOpciones save(RolOpciones rolOpciones) throws Exception {
		try {
			return rolOpcionesRepository.save(rolOpciones);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
