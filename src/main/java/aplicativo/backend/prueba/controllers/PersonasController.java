package aplicativo.backend.prueba.controllers;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import org.springframework.validation.annotation.Validated;
import aplicativo.backend.prueba.model.entities.Persona;
import aplicativo.backend.prueba.response.ResponseData;
import aplicativo.backend.prueba.service.PersonaService;




@RestController
@RequestMapping("api/persona")
public class PersonasController {
	
	
	@Autowired
	private PersonaService personaService;
	
	
	
	@GetMapping("/listar")
	public ResponseData listar() {
		return personaService.findAll();
	}


	
	@GetMapping("/ver/{id}")
	public ResponseData detalle(@PathVariable Integer id)  {
		return personaService.findById(id);
	}
	
	@PostMapping("/ver")
	public ResponseData detalle(@RequestBody Map<String, Integer> requestBody) {
	    Integer id = requestBody.get("id");
	    return personaService.findById(id);
	}

	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseData crear(  @RequestBody Persona persona )  {
		
			 
		ResponseData response = personaService.save(persona, null);
		return response;
		
	}
	

	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseData editar( @RequestBody Persona persona, @PathVariable Integer id)  {
			ResponseData response = personaService.save(persona,id);
			return response;
			
	}

}
