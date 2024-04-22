package aplicativo.backend.prueba.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.RestController;


import aplicativo.backend.prueba.model.entities.Usuario;
import aplicativo.backend.prueba.service.SesionService;
import aplicativo.backend.prueba.util.UsuarioResponse;

@RestController
@RequestMapping("/sesion")
public class SesionController {
	
	@Autowired
	private  SesionService sesionService;
	
	
	@PostMapping("/login")
	 @ResponseBody
	public UsuarioResponse login(@RequestBody Usuario usuario) throws Exception {
		
		
	
            return sesionService.login(usuario);
            
    
	}
	
	
	
	@PostMapping("/logout")
	 @ResponseBody
	public UsuarioResponse logout(@RequestBody Usuario usuario) throws Exception {
		
		
	
            return sesionService.logout(usuario);
            
     
	}

}
