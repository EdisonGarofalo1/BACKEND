package aplicativo.backend.prueba.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import aplicativo.backend.prueba.model.entities.Usuario;
import aplicativo.backend.prueba.service.UsuarioService;
import aplicativo.backend.prueba.util.UsuarioResponse;
import aplicativo.backend.prueba.validators.UsuarioValidator;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
    private UsuarioValidator usuarioValidator;

	@GetMapping("/listar")
	public List<Usuario> listar() {
		return usuarioService.findAll();
	}

	@GetMapping("/ver/{id}")
	public Usuario detalle(@PathVariable Integer id) throws Exception {
		return usuarioService.findById(id);
	}

	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> crear( @Validated @RequestBody Usuario usuario ,BindingResult bindingResult) throws Exception {
	
		try {
			
			usuarioValidator.validate(usuario, bindingResult);
			 if (bindingResult.hasErrors()) {
	                return ResponseEntity.badRequest().body("Errores de validación: " + bindingResult.getAllErrors());
	            }
			 UsuarioResponse response =usuarioService.save(usuario);
			
			
			return ResponseEntity.ok(response);
			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al intentar registrar usuario: " + e.getMessage());
			
		}
	}
	

	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?>  editar(@Validated @RequestBody Usuario usuario, @PathVariable Integer id,BindingResult bindingResult) throws Exception {

		try {
			usuarioValidator.validate(usuario, bindingResult);
			 if (bindingResult.hasErrors()) {
	                return ResponseEntity.badRequest().body("Errores de validación: " + bindingResult.getAllErrors());
	            }
			
			Usuario Db = usuarioService.findById(id);
			Db.setPassword(usuario.getPassword());
		
			Db.setUserName(usuario.getUserName());
			Db.setSessionActive(usuario.getSessionActive());
			
			Db.setRoles(usuario.getRoles());
		
			
			 UsuarioResponse response =usuarioService.save(Db);
				
				
				return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al intentar actualizar usuario: " + e.getMessage());
		}
	}

}
