package aplicativo.backend.prueba.service;

import java.sql.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aplicativo.backend.prueba.model.entities.Sesion;
import aplicativo.backend.prueba.model.entities.Usuario;
import aplicativo.backend.prueba.repository.SesionRepository;
import aplicativo.backend.prueba.repository.UsuarioRepository;
import aplicativo.backend.prueba.response.ResponseData;
import aplicativo.backend.prueba.util.MessageUtil;


@Service
public class SesionServiceImp implements  SesionService{

	
	@Autowired
	   private SesionRepository sesionRepository;
	
	@Autowired
	   private UsuarioRepository   usuarioRepository;
	

	@Override
	public ResponseData login(Usuario usuario)  {
		
		ResponseData response = new ResponseData();
		try {
			 Usuario usuarioDb = usuarioRepository.findByUsernameOrEmail(usuario.getUserName(),usuario.getMail());

                 if(usuarioDb != null ) {
			 
			 
			 if ( usuarioDb.getPassword().equals(usuario.getPassword())) {
		        	
				 if(usuarioDb.getStatus() != null && usuarioDb.getStatus().trim().equalsIgnoreCase("Bloqueado")) {
					 response.setMessage("El usuario está bloqueado. Contacte al administrador.");;
					 
					 return response;
					 
				 } else {
				 
	        	
				 boolean existeSecionActiva = usuarioRepository.findByUsuarioIdAndActivaTrue(usuarioDb.getIdUsuario());
	        	
				 if(existeSecionActiva) {
					 response.setMessage("Ya existe una sesión activa para este usuario");
					 return response;
				
				 }
				 usuarioDb.setSessionActive("A");
				 
				 Sesion sesion = new Sesion();
	        	
	             sesion.setUsuario(usuarioDb);
	           
	             sesion.setFechaIngreso(new Date(System.currentTimeMillis()));
	           
	             sesionRepository.save(sesion);
	   
	             response.setMessage("Se logio con exito");
	             response.setCode("90283989n398293893282393829328329");
	             return response;
	            
				 }
	        }else {
	        	
	        	 int intentosFallidos = usuarioDb != null ? usuarioDb.getIntentosFallidos() + 1 : 1;
	             if (intentosFallidos >= 3) {
	                 usuarioDb.setStatus("Bloqueado");
	                 response.setMessage("El usuario ha sido bloqueado después de tres intentos fallidos.");
	             
	               
	             } else {
	                 usuarioDb.setIntentosFallidos(intentosFallidos);
	                 response.setMessage("Credenciales de inicio de sesión incorrectas. Intento " + intentosFallidos + "/3");
	                 
	             }
	             usuarioRepository.save(usuarioDb);
	            
	             return response;
	           
	        }}else {
			  
			 response.setMessage("Credenciales de inicio de sesión incorrectas.");
			 return response;
	        	
	        }
			
			
		} catch (Exception e) {
	     
			response.setCode(MessageUtil.INTERNALERROR.name());
			response.setMessage(MessageUtil.INTERNALERROR.getKey() + e.getMessage());
		}
		
	     return response;
	}

	@Override
	public ResponseData logout(Usuario usuario)  {
		ResponseData response = new ResponseData();
		try {
			 Usuario usuarioDb = usuarioRepository.findByUsernameOrEmail(usuario.getUserName(),usuario.getMail());
		
			 if (usuarioDb != null) {
				 usuarioDb.setSessionActive("E");
		
				 Sesion sesionAbierta = sesionRepository.findFirstByUsuarioAndFechaCierre(usuarioDb.getIdUsuario());
				 if (sesionAbierta != null) {
					 sesionAbierta.setFechaCierre( new Date(System.currentTimeMillis()));
					 sesionRepository.save(sesionAbierta);
					 
				 }  
				 
	        usuarioDb.setSessionActive("E");
            
            usuarioRepository.save(usuarioDb);
            response.setMessage("Sesión cerrada  corretamente.");
            response.setCode("201");
			 
			 }else {
				 
				  response.setMessage("Usuario no encontrado.");
				 
			 }
			 
		
			
			
	   
	        
		} catch (Exception e) {
			response.setCode(MessageUtil.INTERNALERROR.name());
			response.setMessage(MessageUtil.INTERNALERROR.getKey() + e.getMessage());
		}
	     return response;
		
	}

}
