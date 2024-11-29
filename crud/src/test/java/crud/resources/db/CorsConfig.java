package crud.resources.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.time.Duration;
import java.util.List;

@Configuration
public class CorsConfig {
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cc = new CorsConfiguration();

        // Set allowed origins (specify your frontend URL)
        cc.setAllowedOrigins(List.of("/*"));

        // Allow headers
        cc.setAllowedHeaders(List.of(
                "Origin",
                "Accept",
                "X-Requested-With",
                "Content-Type",
                "Access-Control-Request-Method",
                "Access-Control-Request-Headers",
                "Authorization"
        ));

        // Expose headers
        cc.setExposedHeaders(List.of("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));

        // Allow methods
        cc.setAllowedMethods(List.of("GET", "POST", "OPTIONS", "PUT", "PATCH"));

        // Allow credentials
        cc.setAllowCredentials(true);

        // Set max age
        cc.setMaxAge(Duration.ofHours(1));

        // Register configuration
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cc);
        return source;
    }
}
