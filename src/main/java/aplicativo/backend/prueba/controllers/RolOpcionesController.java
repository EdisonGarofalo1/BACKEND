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
import aplicativo.backend.prueba.model.entities.RolOpciones;
import aplicativo.backend.prueba.service.RolOpcionesService;


@RestController
@RequestMapping("/RolOpciones")
public class RolOpcionesController {
	
	
	@Autowired
	private RolOpcionesService  rolOpcionesService;
	

	@GetMapping("/listar")
	public List<RolOpciones> listar() {
		return rolOpcionesService.findAll();
	}

	@GetMapping("/ver/{id}")
	public RolOpciones detalle(@PathVariable Integer id) throws Exception {
		return rolOpcionesService.findById(id);
	}

	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public RolOpciones crear(@RequestBody RolOpciones rolOpciones) throws Exception {
		return rolOpcionesService.save(rolOpciones);
	}
	

	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public RolOpciones editar(@RequestBody RolOpciones rolOpciones, @PathVariable Integer id) throws Exception {

		try {
			RolOpciones Db = rolOpcionesService.findById(id);
			Db.setNombreOpcion(rolOpciones.getNombreOpcion());
		
			return rolOpcionesService.save(Db);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
