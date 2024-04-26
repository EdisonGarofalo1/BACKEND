package aplicativo.backend.prueba.service;



import java.util.List;

import aplicativo.backend.prueba.model.entities.Rol;
import aplicativo.backend.prueba.response.ResponseData;

public interface RolServiceSP2 {
	public List<Rol> findAll() throws Exception ;
	public Rol  findById(Integer id)throws Exception  ;
	public ResponseData save(Rol rol , Integer id) ;
}
