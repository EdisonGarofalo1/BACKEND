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
import aplicativo.backend.prueba.model.entities.RolOpciones;
import aplicativo.backend.prueba.response.ResponseData;
import aplicativo.backend.prueba.service.RolOpcionesService;


@RestController
@RequestMapping("api/RolOpciones")
public class RolOpcionesController {
	
	
	@Autowired
	private RolOpcionesService  rolOpcionesService;
	

	@GetMapping("/listar")
	public ResponseData listar() {
		return rolOpcionesService.findAll();
	}

	@GetMapping("/ver/{id}")
	public ResponseData detalle(@PathVariable Integer id)  {
		return rolOpcionesService.findById(id);
	}

	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseData crear(@RequestBody RolOpciones rolOpciones)  {
		return rolOpcionesService.save(rolOpciones, null);
	}
	

	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseData editar(@RequestBody RolOpciones rolOpciones, @PathVariable Integer id)  {

		
	
		
			return rolOpcionesService.save(rolOpciones,id);
		
	}

}
