package aplicativo.backend.prueba.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aplicativo.backend.prueba.model.entities.Sesion;

public interface SesionRepository  extends  JpaRepository<Sesion,Integer >{
	

	@Query("SELECT s FROM Sesion s JOIN s.usuario u " +
		       "WHERE u.sessionActive = 'A' AND u.status = 'Activo' AND s.fechaCierre IS NULL AND u.idUsuario = ?1 " +
		       "ORDER BY s.idSesion DESC")
	Sesion findFirstByUsuarioAndFechaCierre(Integer id_usuario);

}
