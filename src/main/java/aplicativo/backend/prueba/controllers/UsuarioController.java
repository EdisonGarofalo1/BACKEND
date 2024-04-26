package aplicativo.backend.prueba.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

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
import aplicativo.backend.prueba.response.ResponseData;
import aplicativo.backend.prueba.service.UsuarioService;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/listar")
	public ResponseData listar() {
		return usuarioService.findAll();
	}

	@GetMapping("/ver/{id}")
	public ResponseData detalle(@PathVariable Integer id) {
		return usuarioService.findById(id);
	}

	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseData crear(@Validated @RequestBody Usuario usuario) {

		ResponseData response = usuarioService.save(usuario, null);
		return response;

	}

	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseData editar(@Validated @RequestBody Usuario usuario, @PathVariable Integer id) {

		ResponseData response = usuarioService.save(usuario, id);
		return response;

	}

	@DeleteMapping("/{id}")
	public ResponseData eliminarUsuario(@PathVariable Integer id) {
		ResponseData response = usuarioService.eliminarUsuario(id);
		return response;
	}

}
