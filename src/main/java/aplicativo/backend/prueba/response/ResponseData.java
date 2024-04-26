package aplicativo.backend.prueba.response;

import java.util.HashMap;
import java.util.Map;

public class ResponseData {
	private String message;
	private String code;
	private Map<String, Object> data;
	
	public ResponseData() {
		super();
		this.data =  new HashMap<String, Object>();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	
	
}
