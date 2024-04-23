package aplicativo.backend.prueba.controllers;

import java.util.List;

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
import  aplicativo.backend.prueba.service.RolServiceSP2;

@RestController
@RequestMapping("/rolsp2")
public class RolSP2Controller {
	
	@Autowired
	private RolServiceSP2 RolServiceSP2;
	@GetMapping("/listar")
	public List<Rol> listar() throws Exception{
		return RolServiceSP2.findAll();
	}

	
	@GetMapping("/ver/{id}")
	public Rol detalle(@PathVariable Integer id) throws Exception {
		return RolServiceSP2.findById(id);
	}
	
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public String crear(@RequestBody Rol rol) throws Exception {
		return RolServiceSP2.save(rol);
	}
	
	
	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public String editar(@RequestBody Rol rol, @PathVariable Integer id) throws Exception {

	
			Rol rolDb = RolServiceSP2.findById(id);
			rolDb.setRolName(rol.getRolName());
			rolDb.setRolOpciones(rol.getRolOpciones());
		
			return RolServiceSP2.save(rolDb);
	}
}
