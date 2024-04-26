package aplicativo.backend.prueba.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aplicativo.backend.prueba.model.entities.Usuario;


public interface UsuarioRepository extends  JpaRepository<Usuario,Integer >{
	@Query("from Usuario  u  where u.mail=?1 ")
	
	Usuario findByMail (String correo);
	
	
	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END from  Usuario u where u.mail=?1 ")
     	boolean existsByMail (String mailUnico);
	

	
	
	@Query("from Usuario  u  where u.userName=?1 or u.mail=?2  ")
	
	 Usuario findByUsernameOrEmail(String userName, String mail);
	
	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Usuario u WHERE u.idUsuario = ?1 AND u.sessionActive = 'A'")
	boolean findByUsuarioIdAndActivaTrue(Integer idUsuario);

}
