package aplicativo.backend.prueba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import aplicativo.backend.prueba.model.entities.Rol;

public interface RolRepository extends  JpaRepository< Rol, Integer> {
	
	   @Query(value = "CALL sp_rolfindAll()", nativeQuery = true)
	    List<Rol> rolfindAll();
	   
	   
	   @Query(value = "CALL sp_rolfindById(:p_id_rol)", nativeQuery = true)
	   Rol rolfindById(@Param("p_id_rol") Integer p_id_rol);
	
	
	   
	   

}
