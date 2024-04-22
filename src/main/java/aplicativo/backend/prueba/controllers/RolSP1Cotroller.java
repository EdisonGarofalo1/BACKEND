package aplicativo.backend.prueba.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aplicativo.backend.prueba.model.entities.Rol;
import aplicativo.backend.prueba.service.RolServiceSP1;

@RestController
@RequestMapping("/rolsp1")
public class RolSP1Cotroller {
	
	@Autowired
	private RolServiceSP1 RolServiceSP;
	
	
	@GetMapping("/listar")
	public List<Rol> listar() {
		return RolServiceSP.findAll();
	}
	
	
	@GetMapping("/ver/{id}")
	public Rol detalle(@PathVariable Integer id) throws Exception {
		return RolServiceSP.findById(id);
	}

}
