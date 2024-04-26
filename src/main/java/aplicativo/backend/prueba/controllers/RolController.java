package aplicativo.backend.prueba.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import aplicativo.backend.prueba.model.entities.Rol;
import aplicativo.backend.prueba.response.ResponseData;
import aplicativo.backend.prueba.service.RolService;

@RestController
@RequestMapping("api/rol")
public class RolController {
	
	@Autowired
	private RolService rolService;
	

	@GetMapping("/listar")
	public ResponseData listar() {
		return rolService.findAll();
	}

	
	
	@GetMapping("/ver/{id}")
	public ResponseData detalle(@PathVariable Integer id)  {
		return rolService.findById(id);
	}

	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseData crear(@RequestBody Rol rol)  {
		return rolService.save(rol, null);
	}
	

	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseData editar(@RequestBody Rol rol, @PathVariable Integer id)  {

	
			
		
			return rolService.save(rol, id);
		
	}

}
