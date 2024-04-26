package aplicativo.backend.prueba.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import aplicativo.backend.prueba.model.entities.Rol;
import aplicativo.backend.prueba.response.ResponseData;
import aplicativo.backend.prueba.service.RolServiceSP1;

@RestController
@RequestMapping("api/rolsp1")
public class RolSP1Cotroller {
	
	@Autowired
	private RolServiceSP1 RolServiceSP;
	
	
	@GetMapping("/listar")
	public ResponseData listar() {
		return RolServiceSP.findAll();
	}
	
	
	
	@GetMapping("/ver/{id}")
	public ResponseData detalle(@PathVariable Integer id) {
		return RolServiceSP.findById(id);
	}

	
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseData crear(@RequestBody Rol rol)  {
		return RolServiceSP.save(rol, null);
	}
	
	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseData editar(@RequestBody Rol rol, @PathVariable Integer id)  {

		
		
			return RolServiceSP.save(rol,id);
		
	}
	
	
}
