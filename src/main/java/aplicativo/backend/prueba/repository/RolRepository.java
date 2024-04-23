package aplicativo.backend.prueba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import aplicativo.backend.prueba.model.entities.Rol;

public interface RolRepository extends  JpaRepository< Rol, Integer> {
	
	   @Query(value = "CALL sp_rolfindAll()", nativeQuery = true)
	  List<Rol> rolfindAll();
	  // List<Object[]> rolfindAll();
	   
 

	   @Query(value = "CALL sp_rolfindById(:p_id_rol)", nativeQuery = true)
	   Rol rolfindById(@Param("p_id_rol") Integer p_id_rol);
	
	 
	   @Query(value = "CALL sp_rol_save(:p_id_rol, :p_rolName, :p_opcionesList)", nativeQuery = true)
	    String guardarPrueba(
	    	 @Param("p_id_rol") Integer p_id_rol,
	        @Param("p_rolName") String p_rolName,
	        @Param("p_opcionesList") String p_opcionesList
	  
	        
	        
	    );
	
	   
	   

}
