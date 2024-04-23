package aplicativo.backend.prueba.service;

import java.sql.CallableStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.swing.tree.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import aplicativo.backend.prueba.model.entities.Rol;
import aplicativo.backend.prueba.model.entities.RolOpciones;
import aplicativo.backend.prueba.util.ConvertirListaAJson;
@Service
public class RolServiceImpSP2 implements RolServiceSP2 {
	
	   private final JdbcTemplate jdbcTemplate;

	    @Autowired
	    public RolServiceImpSP2(JdbcTemplate jdbcTemplate) {
	        this.jdbcTemplate = jdbcTemplate;
	    }
	    

	    
	    
	    @Override
	    public List<Rol> findAll() throws Exception {
	        try {
	            String sql = "CALL sp_rolfindAll()"; // Nombre del procedimiento almacenado para obtener todos los roles
	            return jdbcTemplate.query(sql, rs -> {
	                Map<Integer, Rol> rolMap = new HashMap<>();
	                while (rs.next()) {
	                    Integer idRol = rs.getInt("id_rol");
	                    Rol rol = rolMap.get(idRol); // Verificamos si el rol ya existe en el mapa

	                    if (rol == null) {
	                        // Si el rol no existe en el mapa, lo creamos y lo agregamos al mapa
	                        rol = new Rol();
	                        rol.setIdRol(idRol);
	                        rol.setRolName(rs.getString("rol_name"));
	                        rol.setRolOpciones(new ArrayList<>()); // Inicializamos la lista de opciones
	                        rolMap.put(idRol, rol);
	                    }

	                    // Creamos una instancia de RolOpciones y la agregamos a la lista de opciones del rol
	                    RolOpciones opcion = new RolOpciones();
	                    opcion.setIdOpcion(rs.getInt("id_opcion"));
	                    opcion.setNombreOpcion(rs.getString("nombre_opcion"));
	                    rol.getRolOpciones().add(opcion);
	                }

	                // Devolvemos la lista de roles del mapa
	                return new ArrayList<>(rolMap.values());
	            });
	        } catch (Exception e) {
	            throw new Exception(e.getMessage());
	        }
	    }

	    

	@Override
	public Rol findById(Integer id) throws Exception {
	    try {
	        String sql = "CALL sp_rolfindById(?)"; // Nombre del procedimiento almacenado y sus parámetros
	        return jdbcTemplate.query(sql, new Object[]{id}, rs -> {
	            Rol rol = null;
	            while (rs.next()) {
	                if (rol == null) {
	                    rol = new Rol();
	                    rol.setIdRol(rs.getInt("id_rol"));
	                    rol.setRolName(rs.getString("rol_name"));
	                    rol.setRolOpciones(new ArrayList<>()); // Inicializamos la lista de opciones
	                }
	                RolOpciones opcion = new RolOpciones();
	                opcion.setIdOpcion(rs.getInt("id_opcion"));
	                opcion.setNombreOpcion(rs.getString("nombre_opcion"));
	                rol.getRolOpciones().add(opcion);
	            }
	            return rol;
	        });
	    } catch (Exception e) {
	        throw new Exception(e.getMessage());
	    }
	}




	@Override
	public String save(Rol rol) throws Exception {
		
		
	        try {
	        	  String opcionesJson = ConvertirListaAJson.convertir(rol.getRolOpciones());
	           jdbcTemplate.update("CALL sp_rol_save(?, ?, ?)", rol.getIdRol(),rol.getRolName(),opcionesJson);
	     
	        
	             return " se realizo operacion correctamente.";
	      
	      
	         
	        } catch (Exception e) {
	        	 return "Error al guardar el rol con opciones: " + e.getMessage();
	        }
	       
	}



}
