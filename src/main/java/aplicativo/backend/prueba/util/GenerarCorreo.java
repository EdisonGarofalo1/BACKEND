package aplicativo.backend.prueba.util;


import java.util.Locale;



import aplicativo.backend.prueba.repository.UsuarioRepository;

public class GenerarCorreo {
	
	
	 private static final String DEFAULT_SUFFIX = "@mail.com";

	    public static String generarMailUnico(String mail, UsuarioRepository usuarioRepository) {
	        int numero = 1;
	        String mailUnico = mail;

	        // Extraer la parte antes del '@' del mail
	        String prefix = mail.substring(0, mail.indexOf('@'));

	        // Obtener el sufijo del mail (después del '@')
	        String suffix = DEFAULT_SUFFIX;

	        // Generar mail único y verificar si ya existe en la base de datos
	        while (usuarioRepository.existsByMail(mailUnico)) {
	            mailUnico = prefix + numero + suffix;
	            numero++;
	        }

	        return mailUnico;
	    }

	  public static   String generateEmail(String nombres, String apellidos, String identificacion) {
	        // Convertir nombres y apellidos a minúsculas y eliminar espacios
	        String nombresSinEspacios = nombres.toLowerCase(Locale.ROOT).replaceAll("\\s", "");
	        String apellidosSinEspacios = apellidos.toLowerCase(Locale.ROOT).replaceAll("\\s", "");
	        String identificacionSinEspacios = identificacion.replaceAll("\\s", "");
	        // Generar correo concatenando las iniciales del nombre y el apellido
	        String correo = nombresSinEspacios.charAt(0) + apellidosSinEspacios + identificacionSinEspacios.charAt(0) + "@mail.com";
	        
	        return correo;
	    }
	  
	


	  
	
	  

}
