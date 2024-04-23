package aplicativo.backend.prueba.service;

import java.util.HashSet;
import java.util.List;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import aplicativo.backend.prueba.model.entities.Rol;

import aplicativo.backend.prueba.repository.RolRepository;
import aplicativo.backend.prueba.util.ConvertirListaAJson;

@Service
public class RolServiceImpSP1 implements RolServiceSP1  {

	@Autowired
	private RolRepository rolRepository;
	
	@Override
	public List<Rol> findAll() {
	    Set<Rol> rolesSet = new HashSet<>(rolRepository.rolfindAll());
        return List.copyOf(rolesSet); 
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
		
		try {
			  
			  String opcionesJson = ConvertirListaAJson.convertir(rol.getRolOpciones());
			String resp = rolRepository.guardarPrueba(rol.getIdRol(), rol.getRolName(), opcionesJson);
            return resp;
        } catch (Exception e) {
          
            return "Error al guardar el rol con opciones: " + e.getMessage();
        }
	}

}

