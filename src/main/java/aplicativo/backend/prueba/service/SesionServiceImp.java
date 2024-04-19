package aplicativo.backend.prueba.service;

import java.sql.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aplicativo.backend.prueba.model.entities.Sesion;
import aplicativo.backend.prueba.model.entities.Usuario;
import aplicativo.backend.prueba.repository.SesionRepository;
import aplicativo.backend.prueba.repository.UsuarioRepository;
import aplicativo.backend.prueba.util.UsuarioResponse;

@Service
public class SesionServiceImp implements  SesionService{

	
	@Autowired
	   private SesionRepository sesionRepository;
	
	@Autowired
	   private UsuarioRepository   usuarioRepository;
	

	@Override
	public UsuarioResponse login(Usuario usuario) throws Exception {
		
		try {
			 Usuario usuarioDb = usuarioRepository.findByUsernameOrEmail(usuario.getUserName(),usuario.getMail());
			 UsuarioResponse  response = new UsuarioResponse();
			 
			 if (usuarioDb != null && usuarioDb.getPassword().equals(usuario.getPassword())) {
		        	
				 if(usuarioDb.getStatus() != null && usuarioDb.getStatus().trim().equalsIgnoreCase("Bloqueado")) {
					 response.setMensaje("El usuario está bloqueado. Contacte al administrador.");
					 return response; 
					 
				 } else {
				 
	        	
				 boolean existeSecionActiva = usuarioRepository.findByUsuarioIdAndActivaTrue(usuarioDb.getIdUsuario());
	        	
				 if(existeSecionActiva) {
					 response.setMensaje("Ya existe una sesión activa para este usuario");
					 
					 return response;
				 }
				 usuarioDb.setSessionActive("A");
				 
				 Sesion sesion = new Sesion();
	        	
	             sesion.setUsuario(usuarioDb);
	           
	             sesion.setFechaIngreso(new Date(System.currentTimeMillis()));
	            // usuarioRepository.save(usuarioDb);
	             sesionRepository.save(sesion);
	            
	             //response.setUsuario(usuarioDb);
	             response.setMensaje("Se logio con exito");
	             return response; 
				 }
	        }else {
	        	
	        	 int intentosFallidos = usuarioDb != null ? usuarioDb.getIntentosFallidos() + 1 : 1;
	             if (intentosFallidos >= 3) {
	                 usuarioDb.setStatus("Bloqueado");
	                 response.setMensaje("El usuario ha sido bloqueado después de tres intentos fallidos.");
	                 return response; 
	             } else {
	                 usuarioDb.setIntentosFallidos(intentosFallidos);
	                 response.setMensaje("Credenciales de inicio de sesión incorrectas. Intento " + intentosFallidos + "/3");
	             }
	             usuarioRepository.save(usuarioDb);
	           
	        }
			  
			 response.setMensaje("Credenciales de inicio de sesión incorrectas.");
			   return response; 
			
		} catch (Exception e) {
	        throw new Exception("Error al intentar iniciar sesión: " + e.getMessage());
		}
	}

	@Override
	public UsuarioResponse logout(Usuario usuario) throws Exception {
		try {
			 Usuario usuarioDb = usuarioRepository.findById(usuario.getIdUsuario()).orElse(null);
			 UsuarioResponse  response = new UsuarioResponse();
			 if (usuarioDb != null) {
				 usuarioDb.setSessionActive("E");
		
				 Sesion sesionAbierta = sesionRepository.findFirstByUsuarioAndFechaCierre(usuario.getIdUsuario());
				 if (sesionAbierta != null) {
					 sesionAbierta.setFechaCierre( new Date(System.currentTimeMillis()));
					 sesionRepository.save(sesionAbierta);
					 
				 }  
				 
	        usuarioDb.setSessionActive("E");
            
            usuarioRepository.save(usuarioDb);
			 }
			 
		
			 response.setMensaje("Sesión cerrada  corretamente.");
			 
			
	        return response;
	        
		} catch (Exception e) {
			 throw new Exception("Error al cerrar sesión: " + e.getMessage());
		}
			
		
	}

}
