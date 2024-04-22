package aplicativo.backend.prueba.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
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


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	

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
	
			 UsuarioResponse response =usuarioService.save(usuario,bindingResult);
			return ResponseEntity.ok(response);
			
	}
	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?>  editar(@Validated @RequestBody Usuario usuario, @PathVariable Integer id,BindingResult bindingResult) throws Exception {	
		
			
			Usuario Db = usuarioService.findById(id);
			
			Db.setPassword(usuario.getPassword());
		
			Db.setUserName(usuario.getUserName());
			Db.setSessionActive(usuario.getSessionActive());
			
			Db.setRoles(usuario.getRoles());
		
			
			 UsuarioResponse response =usuarioService.save(Db,bindingResult);
				
				
				return ResponseEntity.ok(response);
				
				
		
	}
	
	
	@DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Integer id) {
        boolean eliminado = usuarioService.eliminarUsuario(id);
        if (eliminado) {
            return ResponseEntity.ok("Usuario eliminado ");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }

}
