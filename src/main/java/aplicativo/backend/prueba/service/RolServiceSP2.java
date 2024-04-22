package aplicativo.backend.prueba.service;

import java.util.List;

import aplicativo.backend.prueba.model.entities.Rol;

public interface RolServiceSP2 {
	public List<Rol> findAll() throws Exception;
	public Rol findById(Integer id) throws Exception;
	public String save(Rol rol) throws Exception;
}
