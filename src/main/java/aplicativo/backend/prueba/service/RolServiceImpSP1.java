package aplicativo.backend.prueba.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import aplicativo.backend.prueba.model.entities.Rol;
import aplicativo.backend.prueba.model.entities.RolOpciones;
import aplicativo.backend.prueba.repository.RolRepository;
import aplicativo.backend.prueba.util.ConvertirListaAJson;

@Service
public class RolServiceImpSP1 implements RolServiceSP1  {

	@Autowired
	private RolRepository rolRepository;
	
	@Override
	public List<Rol> findAll() {
		
		
	       List<Object[]> resultados = rolRepository.rolfindAll();
	      
	     
	        Map<Integer, Rol> rolMap = new HashMap<>();

	        for (Object[] resultado : resultados) {
	            Integer idRol = Objects.nonNull(resultado[0]) ? Integer.parseInt(resultado[0].toString()) : null;
	            String rolName = Objects.nonNull(resultado[1]) ? resultado[1].toString() : null;
	           
	            Rol rol = rolMap.get(idRol);
	            
	            if (rol == null) {  
	            	
                    rol = new Rol();
                    rol.setIdRol(idRol);
                    rol.setRolName(rolName);
                    rol.setRolOpciones(new ArrayList<>()); 
                    rolMap.put(idRol, rol);
                }
             
	            Integer idOpcion = Objects.nonNull(resultado[2]) ? Integer.parseInt(resultado[2].toString()) : null;
                String nombreOpcion = Objects.nonNull(resultado[3]) ? resultado[3].toString() : null;
               
	            
	            RolOpciones opcion = new RolOpciones();
                opcion.setIdOpcion(idOpcion);
                opcion.setNombreOpcion(nombreOpcion);
                rol.getRolOpciones().add(opcion);
                /*
	           for (int i = 2; i < resultado.length; i += 2) {
	                Integer idOpcion = Objects.nonNull(resultado[i]) ? Integer.parseInt(resultado[i].toString()) : null;
	                String nombreOpcion = Objects.nonNull(resultado[i + 1]) ? resultado[i + 1].toString() : null;
	               
	                
	                RolOpciones opcion = new RolOpciones();
	                opcion.setIdOpcion(idOpcion);
	                opcion.setNombreOpcion(nombreOpcion);
	           
	                rol.getRolOpciones().add(opcion);
	            }
	            */

	          
	        }
	        return new ArrayList<>(rolMap.values());
	      
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
            // Manejo de excepciones si ocurre un error al guardar
            return "Error al guardar el rol con opciones: " + e.getMessage();
        }
	}

}

