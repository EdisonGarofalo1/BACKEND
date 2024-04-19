package aplicativo.backend.prueba.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aplicativo.backend.prueba.model.entities.Persona;
import aplicativo.backend.prueba.model.entities.Usuario;
import aplicativo.backend.prueba.repository.PersonaRepository;
import aplicativo.backend.prueba.repository.UsuarioRepository;
import aplicativo.backend.prueba.util.GenerarCorreo;
import aplicativo.backend.prueba.util.UsuarioResponse;
@Service
public class UsuarioServiceImp  implements UsuarioService{


	@Autowired
	private  UsuarioRepository usuarioRepository;
	
	
	@Autowired
	private  PersonaRepository personaRepository;
	
	@Override
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioRepository.findAll();
	}

	@Override
	public Usuario findById(Integer id) throws Exception {
		try {
			return usuarioRepository.findById(id).orElse(null);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	

	@Override
	public UsuarioResponse save(Usuario usuario) throws Exception {
		try {   
            Optional<Persona> personaOptional = personaRepository.findById(usuario.getPersona().getIdPersona());
         	UsuarioResponse  response = new UsuarioResponse();
            Usuario usuarioDb = usuarioRepository.findByUsernameOrEmail(usuario.getUserName(),usuario.getMail());
            
            if(usuarioDb !=null && usuario.getIdUsuario() == null )  {
           
            	 response.setUsuario(usuarioDb);
            	 response.setMensaje(" ya tiene una cuenta con es  Usuario");
            	return response;
            }
            
            if (personaOptional.isPresent()) {
                Persona persona = personaOptional.get();
                String correo = GenerarCorreo.generateEmail(persona.getNombres(), persona.getApellidos(), persona.getIdentificacion());
               
                Usuario CorreoExistente = usuarioRepository.findByMail(correo);
                
                if (CorreoExistente != null) {
                    String mailNuevo = GenerarCorreo.generarMailUnico(correo, usuarioRepository);
                    usuario.setMail(mailNuevo);
                }else {

                usuario.setMail(correo);
                }
                
                usuarioRepository.save(usuario);
                response.setUsuario(usuario);
           	    response.setMensaje("Se registro corretamente");
                
                return response;
            } else {
            	 response.setMensaje("Persona no encontrada con ese id:"+ usuario.getPersona().getIdPersona());
                return response;
            }
        } catch (Exception e) {
            throw new Exception("Error al intentar registrar Usuario: " + e.getMessage());
        }
	}

	@Override
	public boolean  eliminarUsuario(Integer idUsuario) {
		  Optional<Usuario> optionalUsuario = usuarioRepository.findById(idUsuario);
	        if (optionalUsuario.isPresent()) {
	            Usuario usuario = optionalUsuario.get();
	            usuario.setStatus("Eliminado");
	            usuarioRepository.save(usuario);
	            return true;
	        } else {
	        	return false;
	        }
		
	}

}
