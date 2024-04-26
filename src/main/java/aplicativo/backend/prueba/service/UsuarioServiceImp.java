package aplicativo.backend.prueba.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import aplicativo.backend.prueba.model.entities.Persona;
import aplicativo.backend.prueba.model.entities.Usuario;
import aplicativo.backend.prueba.repository.PersonaRepository;
import aplicativo.backend.prueba.repository.UsuarioRepository;
import aplicativo.backend.prueba.response.ResponseData;
import aplicativo.backend.prueba.util.GenerarCorreo;
import aplicativo.backend.prueba.util.MessageUtil;

@Service
public class UsuarioServiceImp implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PersonaRepository personaRepository;

	@Override
	public ResponseData findAll() {

		ResponseData response = new ResponseData();
		Map<String, Object> mapUsuario = new HashMap<>();
		try {

			List<Usuario> listusuario = usuarioRepository.findAll();

			if (!listusuario.isEmpty()) {
				mapUsuario.put("listusuario", listusuario);
				response.setData(mapUsuario);
				response.setCode(MessageUtil.OK.name());
				response.setMessage(MessageUtil.OK.getKey());
			} else {
				response.setCode(MessageUtil.NOTFOUND.name());
				response.setMessage(MessageUtil.NOTFOUND.getKey());
			}

		} catch (Exception e) {

			response.setCode(MessageUtil.ERRORCONSULTA.name());
			response.setMessage(MessageUtil.ERRORCONSULTA.getKey() + e.getMessage());

		}

		return response;
	}

	@Override
	public ResponseData findById(Integer id) {
		ResponseData response = new ResponseData();
		Map<String, Object> mapUsuario = new HashMap<>();

		try {
			Usuario uruario = usuarioRepository.findById(id).orElse(null);

			if (uruario != null) {

				mapUsuario.put("usuario", uruario);
				response.setData(mapUsuario);

				response.setCode(MessageUtil.OK.name());
				response.setMessage(MessageUtil.OK.getKey());
			} else {
				response.setCode(MessageUtil.NOTFOUND.name());
				response.setMessage(MessageUtil.NOTFOUND.getKey());
			}

		} catch (Exception e) {

			response.setCode(MessageUtil.ERRORCONSULTA.name());
			response.setMessage(MessageUtil.ERRORCONSULTA.getKey() + e.getMessage());

		}

		return response;
	}

	@Override
	public ResponseData save(Usuario usuario, Integer id) {

		ResponseData response = new ResponseData();

		try {

			Optional<Persona> personaOptional = personaRepository.findById(usuario.getPersona().getIdPersona());

			Usuario usuarioDb = usuarioRepository.findByUsernameOrEmail(usuario.getUserName(), usuario.getMail());

			if (id != null) {
				Usuario DbUsuario = usuarioRepository.findById(id).orElse(null);

				if (DbUsuario != null) {

					DbUsuario.setPassword(usuario.getPassword());

					DbUsuario.setUserName(usuario.getUserName());
					DbUsuario.setSessionActive(usuario.getSessionActive());

					DbUsuario.setRoles(usuario.getRoles());

					usuarioRepository.save(usuario);

					response.setCode(MessageUtil.UPDATED.name());
					response.setMessage(MessageUtil.UPDATED.getKey());

				} else {

					response.setCode(MessageUtil.NOTFOUND.name());
					response.setMessage(MessageUtil.NOTFOUND.getKey());

				}

			} else {

				if (usuarioDb != null && usuario.getIdUsuario() == null) {

					response.setCode(MessageUtil.MODULOEXIST.name());
					response.setMessage(MessageUtil.MODULOEXIST.getKey());
				}
				if (personaOptional.isPresent()) {
					Persona persona = personaOptional.get();
					String correo = GenerarCorreo.generateEmail(persona.getNombres(), persona.getApellidos(),
							persona.getIdentificacion());

					Usuario CorreoExistente = usuarioRepository.findByMail(correo);

					if (CorreoExistente != null) {
						String mailNuevo = GenerarCorreo.generarMailUnico(correo, usuarioRepository);
						usuario.setMail(mailNuevo);
					} else {

						usuario.setMail(correo);
					}

					usuarioRepository.save(usuario);

					response.setCode(MessageUtil.CREATED.name());
					response.setMessage(MessageUtil.CREATED.getKey());

				} else {
					response.setCode(MessageUtil.NOTFOUND.name());
					response.setMessage(MessageUtil.NOTFOUND.getKey());
				}

			}

		} catch (Exception e) {

			response.setCode(MessageUtil.INTERNALERROR.name());
			response.setMessage(MessageUtil.INTERNALERROR.getKey() + e.getMessage());

		}

		return response;
	}

	@Override
	public ResponseData eliminarUsuario(Integer idUsuario) {

		ResponseData response = new ResponseData();
		try {
			Optional<Usuario> optionalUsuario = usuarioRepository.findById(idUsuario);

			if (optionalUsuario.isPresent()) {
				Usuario usuario = optionalUsuario.get();
				usuario.setStatus("Eliminado");
				usuarioRepository.save(usuario);
				response.setCode(MessageUtil.DELETED.name());
				response.setMessage(MessageUtil.DELETED.getKey());
			} else {
				response.setCode(MessageUtil.NOTFOUND.name());
				response.setMessage(MessageUtil.NOTFOUND.getKey());
			}

		} catch (Exception e) {

		}

		return response;

	}

}
