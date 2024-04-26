package aplicativo.backend.prueba.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        
    	registry.addMapping("/**")
        //.allowedOrigins("*") 
        .allowedOrigins("http://localhost:4200") // Permitir solo desde este origen
        //.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
       .allowedOrigins("*") // Permitir solo desde este origen
        .allowedMethods("*")
    	.allowedHeaders("*");
        //.allowCredentials(true)
        //.maxAge(3600);
    }
}