package aplicativo.backend.prueba.util;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import aplicativo.backend.prueba.model.entities.RolOpciones;

public class ConvertirListaAJson {
	

	   public static  String convertir(List<RolOpciones> rolOpciones) {
	        // Implementa la conversión de la lista a JSON según necesites
	        ObjectMapper objectMapper = new ObjectMapper();
	        try {
	            return objectMapper.writeValueAsString(rolOpciones);
	        } catch (JsonProcessingException e) {
	            // Manejar la excepción si ocurre un error al convertir a JSON
	            e.printStackTrace(); // O cualquier otra acción que desees
	            return null; // O un valor por defecto según tu lógica de negocio
	        }
	    }


}
