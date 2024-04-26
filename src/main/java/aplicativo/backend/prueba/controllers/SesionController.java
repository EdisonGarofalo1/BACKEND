package aplicativo.backend.prueba.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.RestController;


import aplicativo.backend.prueba.model.entities.Usuario;
import aplicativo.backend.prueba.response.ResponseData;
import aplicativo.backend.prueba.service.SesionService;


@RestController
@RequestMapping("api/sesion")
public class SesionController {
	
	@Autowired
	private  SesionService sesionService;
	
	
	@PostMapping("/login")
	 @ResponseBody
	public ResponseData login(@RequestBody Usuario usuario)  {
		
		
	
            return sesionService.login(usuario);
            
    
	}
	
	
	
	@PostMapping("/logout")
	 @ResponseBody
	public ResponseData logout(@RequestBody Usuario usuario) {
		
		
	
            return sesionService.logout(usuario);
            
     
	}

}
