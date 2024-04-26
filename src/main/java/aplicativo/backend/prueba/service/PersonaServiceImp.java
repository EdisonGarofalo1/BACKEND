package aplicativo.backend.prueba.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;




import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;

import aplicativo.backend.prueba.model.entities.Persona;

import aplicativo.backend.prueba.repository.PersonaRepository;
import aplicativo.backend.prueba.response.ResponseData;
import aplicativo.backend.prueba.util.JsonSchemaLoader;
import aplicativo.backend.prueba.util.MessageUtil;




@Service
public class PersonaServiceImp implements PersonaService {

	@Autowired
	private PersonaRepository personaRepository;
	 
	private final JsonSchema userSchema;
   private final ObjectMapper objectMapper;
   
   @Autowired
   public PersonaServiceImp(ObjectMapper objectMapper) throws Exception {
       this.userSchema = JsonSchemaLoader.loadSchemaFromInputStream(getClass().getResourceAsStream("/json/persona-schema.json"));
       this.objectMapper = objectMapper;
   }

	@Override
	public ResponseData findAll() {
		ResponseData response = new ResponseData();

		Map<String, Object> mapPersonas = new HashMap<>();
		try {

			List<Persona> listpersonas = personaRepository.findAll();

			if (!listpersonas.isEmpty()) {
				mapPersonas.put("listpersonas", listpersonas);
				response.setData(mapPersonas);
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
	public ResponseData findById(Integer id) {

		ResponseData response = new ResponseData();

		Map<String, Object> mapPersonas = new HashMap<>();
		try {

			Persona persona = personaRepository.findById(id).orElse(null);
		
			if (persona != null) {
			
				mapPersonas.put("persona", persona);
				response.setData(mapPersonas);

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
	public ResponseData save( Persona persona, Integer id) {

		ResponseData response = new ResponseData();	 
	        
		try {

			   String json = objectMapper.writeValueAsString(persona);
		        ProcessingReport report = userSchema.validate(objectMapper.readTree(json));
		        
		        if (report.isSuccess()) {
			
			if (id != null) {
				Persona personaresponse = personaRepository.findById(id).orElse(null);

				if (personaresponse != null) {

					personaresponse.setNombres(persona.getNombres());
					personaresponse.setApellidos(persona.getApellidos());
					personaresponse.setFechaNacimiento(persona.getFechaNacimiento());
					personaresponse.setIdentificacion(persona.getIdentificacion());

					personaRepository.save(personaresponse);

					response.setCode(MessageUtil.UPDATED.name());
					response.setMessage(MessageUtil.UPDATED.getKey());

				} else {
					response.setCode(MessageUtil.NOTFOUND.name());
					response.setMessage(MessageUtil.NOTFOUND.getKey());
				}

			} else {
				personaRepository.save(persona);
				response.setCode(MessageUtil.CREATED.name());
				response.setMessage(MessageUtil.CREATED.getKey());

			}
			
		        } else {
		        	response.setCode(MessageUtil.JSONSCHEMA.name());
					response.setMessage(MessageUtil.JSONSCHEMA.getKey() + report.toString());

		         
		        }
		} catch (Exception e) {
			response.setCode(MessageUtil.INTERNALERROR.name());
			response.setMessage(MessageUtil.INTERNALERROR.getKey() + "\n" + e.getMessage());

		}

		return response;

	}

	
	

}
