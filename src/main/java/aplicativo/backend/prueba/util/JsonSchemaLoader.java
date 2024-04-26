package aplicativo.backend.prueba.util;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

public class JsonSchemaLoader {
	

	   public static JsonSchema loadSchemaFromInputStream(InputStream inputStream) throws IOException, ProcessingException {
	        ObjectMapper objectMapper = new ObjectMapper();
	        JsonNode schemaNode = objectMapper.readTree(inputStream);
	        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
	        return factory.getJsonSchema(schemaNode);

}

}
