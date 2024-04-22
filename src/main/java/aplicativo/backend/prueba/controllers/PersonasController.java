package aplicativo.backend.prueba.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import aplicativo.backend.prueba.model.entities.Persona;
import aplicativo.backend.prueba.service.PersonaService;
import aplicativo.backend.prueba.util.PersonaResponse;



@RestController
@RequestMapping("/persona")
public class PersonasController {
	
	
	@Autowired
	private PersonaService personaService;
	
	
	
	@GetMapping("/listar")
	public List<Persona> listar() {
		return personaService.findAll();
	}

	@GetMapping("/ver/{id}")
	public Persona detalle(@PathVariable Integer id) throws Exception {
		return personaService.findById(id);
	}

	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> crear(@Validated @RequestBody Persona persona  ,BindingResult bindingResult) throws Exception {
		
			 
			PersonaResponse response = personaService.save(persona, bindingResult);
		return ResponseEntity.ok(response);
		
	}
	

	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?>  editar(@Validated @RequestBody Persona persona, @PathVariable Integer id,BindingResult bindingResult) throws Exception {

		   
			Persona Db = personaService.findById(id);
			Db.setNombres(persona.getNombres());
			Db.setApellidos(persona.getApellidos());
			Db.setFechaNacimiento(persona.getFechaNacimiento());
			Db.setIdentificacion(persona.getIdentificacion());
			PersonaResponse response = personaService.save(Db,bindingResult);
			return ResponseEntity.ok(response);
		
			
	
	}

}
