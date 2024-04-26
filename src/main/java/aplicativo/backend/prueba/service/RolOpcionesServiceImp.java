package aplicativo.backend.prueba.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import aplicativo.backend.prueba.model.entities.RolOpciones;
import aplicativo.backend.prueba.repository.RolOpcionesRepository;
import aplicativo.backend.prueba.response.ResponseData;
import aplicativo.backend.prueba.util.MessageUtil;
@Service
public class RolOpcionesServiceImp  implements RolOpcionesService{

	
	@Autowired
	private RolOpcionesRepository rolOpcionesRepository;
	
	@Override
	public ResponseData findAll() {
	
		
		
		ResponseData response = new ResponseData();

		Map<String, Object> mapOpciones = new HashMap<>();
		try {

			List<RolOpciones> listOpciones = rolOpcionesRepository.findAll();

			if (!listOpciones.isEmpty()) {
				mapOpciones.put("listopciones", listOpciones);
				response.setData(mapOpciones);
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

			Map<String, Object> mapOpciones = new HashMap<>();
			try {

				RolOpciones rolOpciones = rolOpcionesRepository.findById(id).orElse(null);
			
				if (rolOpciones != null) {
					
					mapOpciones.put("opcionesRol", rolOpciones);
					response.setData(mapOpciones);

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
	public ResponseData save(RolOpciones rolOpciones, Integer id)  {
		ResponseData response = new ResponseData();
		try {
			
			
			
			if (id != null) {
				
				RolOpciones rolopcionesresponse = rolOpcionesRepository.findById(id).orElse(null);
				
				if (rolopcionesresponse != null) {

					rolopcionesresponse.setNombreOpcion(rolOpciones.getNombreOpcion());
					

				
					
					rolOpcionesRepository.save(rolopcionesresponse);

					response.setCode(MessageUtil.UPDATED.name());
					response.setMessage(MessageUtil.UPDATED.getKey());

				} else {
					response.setCode(MessageUtil.NOTFOUND.name());
					response.setMessage(MessageUtil.NOTFOUND.getKey());
				}
				
			}else {
				
				
				rolOpcionesRepository.save(rolOpciones);
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
