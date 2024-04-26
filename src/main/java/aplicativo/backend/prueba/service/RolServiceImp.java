package aplicativo.backend.prueba.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aplicativo.backend.prueba.model.entities.Rol;

import aplicativo.backend.prueba.repository.RolRepository;
import aplicativo.backend.prueba.response.ResponseData;
import aplicativo.backend.prueba.util.MessageUtil;


@Service
public class RolServiceImp implements RolService{
	
	@Autowired
	private RolRepository rolRepository;

	@Override
	public ResponseData findAll() {
		
		
		
		ResponseData response = new ResponseData();

		Map<String, Object> mapRol = new HashMap<>();
		try {

			List<Rol> listRol = rolRepository.findAll();

			if (!listRol.isEmpty()) {
				mapRol.put("listRol", listRol);
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

				Rol rol = rolRepository.findById(id).orElse(null);
			
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
	public ResponseData save(Rol rol, Integer id)  {
		
		
			   
	
		 
		 
		 
			ResponseData response = new ResponseData();
			try {
				
				
				
				if (id != null) {
					
					Rol rolreponse = rolRepository.findById(id).orElse(null);
					
					if (rolreponse != null) {

						rolreponse.setRolName(rol.getRolName());
						rolreponse.setRolOpciones(rol.getRolOpciones());
					
						
						rolRepository.save(rolreponse);

						response.setCode(MessageUtil.UPDATED.name());
						response.setMessage(MessageUtil.UPDATED.getKey());

					} else {
						response.setCode(MessageUtil.NOTFOUND.name());
						response.setMessage(MessageUtil.NOTFOUND.getKey());
					}
					
				}else {
					
					
					rolRepository.save(rol);
					response.setCode(MessageUtil.CREATED.name());
					response.setMessage(MessageUtil.CREATED.getKey());
				}
			} catch (Exception e) {
				response.setCode(MessageUtil.INTERNALERROR.name());
				response.setMessage(MessageUtil.INTERNALERROR.getKey() + e.getMessage());

			}

			return response;
		 
		
	}

}
