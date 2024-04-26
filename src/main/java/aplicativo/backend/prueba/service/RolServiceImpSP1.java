package aplicativo.backend.prueba.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import aplicativo.backend.prueba.model.entities.Rol;

import aplicativo.backend.prueba.repository.RolRepository;
import aplicativo.backend.prueba.response.ResponseData;
import aplicativo.backend.prueba.util.ConvertirListaAJson;
import aplicativo.backend.prueba.util.MessageUtil;

@Service
public class RolServiceImpSP1 implements RolServiceSP1  {

	@Autowired
	private RolRepository rolRepository;
	
	@Override
	public ResponseData findAll() {
		ResponseData response = new ResponseData();
	    
		Map<String, Object> mapRol = new HashMap<>();
		try {
		    Set<Rol> rolesSet = new HashSet<>(rolRepository.rolfindAll());
			List<Rol> lroles = new ArrayList<>(rolesSet);
		    lroles.sort(Comparator.comparing(Rol::getIdRol));

			if (!lroles.isEmpty()) {
				mapRol.put("listRol", lroles);
				response.setData(mapRol);
				response.setCode(MessageUtil.OK.name());
				response.setMessage(MessageUtil.OK.getKey());
			} else {
				response.setCode(MessageUtil.NOTFOUND.name());
				response.setMessage(MessageUtil.NOTFOUND.getKey());
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(MessageUtil.ERRORCONSULTA.name());
			response.setMessage(MessageUtil.ERRORCONSULTA.getKey() + e.getMessage());
		}
	    
	    
        return response;
        
	    }
	
	@Override
	public ResponseData findById(Integer id)  {
		
	
		
		ResponseData response = new ResponseData();

		Map<String, Object> mapRol = new HashMap<>();
		try {

			Rol rol = rolRepository.rolfindById(id);
		
			if (rol != null) {
				
				mapRol.put("Rol", rol);
				response.setData(mapRol);

				response.setCode(MessageUtil.OK.name());
				response.setMessage(MessageUtil.OK.getKey());
			} else {
				response.setCode(MessageUtil.NOTFOUND.name());
				response.setMessage(MessageUtil.NOTFOUND.getKey());
			}

		} catch (Exception e) {

			response.setCode(MessageUtil.ERRORCONSULTA.name());
			response.setMessage(MessageUtil.ERRORCONSULTA.getKey() + e.getMessage());
		}
	
	return response;

		
	}

	@Override
	public ResponseData save(Rol rol , Integer id)  {
	
			  String opcionesJson = ConvertirListaAJson.convertir(rol.getRolOpciones());
			
          
        
            
   		 
   			ResponseData response = new ResponseData();
   			try {
   				
   				
   				
   				if (id != null) {
   					
   					Rol rolreponse = rolRepository.findById(id).orElse(null);
   					
   					if (rolreponse != null) {

   						String resp = rolRepository.guardarPrueba(id, rol.getRolName(), opcionesJson);
   						response.setCode(MessageUtil.UPDATED.name());
   						response.setMessage(resp);

   					} else {
   						response.setCode(MessageUtil.NOTFOUND.name());
   						response.setMessage(MessageUtil.NOTFOUND.getKey());
   					}
   					
   				}else {
   					
   				
   					String resp = rolRepository.guardarPrueba(id, rol.getRolName(), opcionesJson);
   					response.setCode(MessageUtil.CREATED.name());
   					response.setMessage(resp);
   				}
   			} catch (Exception e) {
   				response.setCode(MessageUtil.INTERNALERROR.name());
   				response.setMessage(MessageUtil.INTERNALERROR.getKey() + e.getMessage());

   			}

   			return response;
		
		
	}

}

