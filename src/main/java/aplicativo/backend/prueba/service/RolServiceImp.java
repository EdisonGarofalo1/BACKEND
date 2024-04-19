package aplicativo.backend.prueba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aplicativo.backend.prueba.model.entities.Rol;

import aplicativo.backend.prueba.repository.RolRepository;


@Service
public class RolServiceImp implements RolService{
	
	@Autowired
	private RolRepository rolRepository;

	@Override
	public List<Rol> findAll() {
		return (List<Rol>) rolRepository.findAll();
	}

	@Override
	public Rol findById(Integer id) throws Exception {
	
		try {
			return rolRepository.findById(id).orElse(null);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Rol save(Rol rol) throws Exception {
		
		try {
			   
			
			return  rolRepository.save(rol);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
