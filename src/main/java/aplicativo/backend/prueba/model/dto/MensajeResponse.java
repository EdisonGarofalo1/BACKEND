package aplicativo.backend.prueba.model.dto;

public class MensajeResponse {

	 private String codigo;
	    private String mensaje;
	    private Object datos;
	    private int codigoHTTP;

	    // Constructor con código y mensaje
	    public MensajeResponse(String codigo, String mensaje) {
	        this.codigo = codigo;
	        this.mensaje = mensaje;
	    }

	    // Constructor con código, mensaje y datos
	    public MensajeResponse(String codigo, String mensaje, Object datos) {
	        this.codigo = codigo;
	        this.mensaje = mensaje;
	        this.datos = datos;
	    }

	    // Getters y Setters
	    public String getCodigo() {
	        return codigo;
	    }

	    public void setCodigo(String codigo) {
	        this.codigo = codigo;
	    }

	    public String getMensaje() {
	        return mensaje;
	    }

	    public void setMensaje(String mensaje) {
	        this.mensaje = mensaje;
	    }

	    public Object getDatos() {
	        return datos;
	    }

	    public void setDatos(Object datos) {
	        this.datos = datos;
	    }
	    

	    public int getCodigoHTTP() {
	        return codigoHTTP;
	    }

	    public void setCodigoHTTP(int codigoHTTP) {
	        this.codigoHTTP = codigoHTTP;
	    }
}
