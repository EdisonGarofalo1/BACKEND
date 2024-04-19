package aplicativo.backend.prueba.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import aplicativo.backend.prueba.model.entities.Rol;
import aplicativo.backend.prueba.service.RolService;

@RestController
@RequestMapping("/rol")
public class RolController {
	
	@Autowired
	private RolService rolService;
	

	@GetMapping("/listar")
	public List<Rol> listar() {
		return rolService.findAll();
	}

	@GetMapping("/ver/{id}")
	public Rol detalle(@PathVariable Integer id) throws Exception {
		return rolService.findById(id);
	}

	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Rol crear(@RequestBody Rol rol) throws Exception {
		return rolService.save(rol);
	}
	

	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Rol editar(@RequestBody Rol rol, @PathVariable Integer id) throws Exception {

		try {
			Rol rolDb = rolService.findById(id);
			rolDb.setRolName(rol.getRolName());
			rolDb.setRolOpciones(rol.getRolOpciones());
		
			return rolService.save(rolDb);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
