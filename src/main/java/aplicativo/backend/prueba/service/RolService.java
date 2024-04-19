package aplicativo.backend.prueba.service;

import java.util.List;
import aplicativo.backend.prueba.model.entities.Rol;

public interface RolService {
	
	public List<Rol> findAll();
	public Rol findById(Integer id) throws Exception;
	public Rol save(Rol rol) throws Exception;


}
