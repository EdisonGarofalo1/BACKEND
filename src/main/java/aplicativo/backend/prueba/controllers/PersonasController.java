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

import aplicativo.backend.prueba.validators.PersonaValidator;

@RestController
@RequestMapping("/persona")
public class PersonasController {
	
	
	@Autowired
	private PersonaService personaService;
	
	@Autowired
    private  PersonaValidator personaValidator;
	
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
		try {
			
			personaValidator.validate(persona, bindingResult);
			
			   if (bindingResult.hasErrors()) {
	                return ResponseEntity.badRequest().body("Errores de validación: " + bindingResult.getAllErrors());
	            }
			PersonaResponse response = personaService.save(persona);
		return ResponseEntity.ok(response);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al intentar registrar Persona: " + e.getMessage());
			
		}
	}
	

	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?>  editar(@Validated @RequestBody Persona persona, @PathVariable Integer id,BindingResult bindingResult) throws Exception {

		try {
			
			personaValidator.validate(persona, bindingResult);
			
			   if (bindingResult.hasErrors()) {
	                return ResponseEntity.badRequest().body("Errores de validación: " + bindingResult.getAllErrors());
	            }
			Persona Db = personaService.findById(id);
			Db.setNombres(persona.getNombres());
			Db.setApellidos(persona.getApellidos());
			Db.setFechaNacimiento(persona.getFechaNacimiento());
			Db.setIdentificacion(persona.getIdentificacion());
		
			PersonaResponse response = personaService.save(Db);
			return ResponseEntity.ok(response);
		
			
		} catch (Exception e) {
		
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al intentar registrar Persona: " + e.getMessage());	
		}
	}

}
