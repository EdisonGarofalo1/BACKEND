package aplicativo.backend.prueba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aplicativo.backend.prueba.model.entities.Rol;
import aplicativo.backend.prueba.repository.RolRepository;

@Service
public class RolServiceImpSP1 implements RolServiceSP1  {

	@Autowired
	private RolRepository rolRepository;
	@Override
	public List<Rol> findAll() {
		Object obj = rolRepository.rolfindAll(); 
		List<Rol> roles = rolRepository.rolfindAll(); 
		
		return roles;
		
	
	}

	@Override
	public Rol findById(Integer id) throws Exception {
		try {
		return  rolRepository.rolfindById(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public String save(Rol rol) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
