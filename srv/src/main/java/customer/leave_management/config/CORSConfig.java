package customer.leave_management.config;

// import java.beans.Customizer; // Remove this import
import org.springframework.security.config.Customizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import customer.leave_management.Utilis.JwtFilter;

@Configuration
public class CORSConfig {
    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("*")  // ✅ Use this instead of allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
                      
            }
        };
    }

    @Bean
    public SecurityFilterChain allOpenFilterChain(HttpSecurity http) throws Exception {
        // http
        // .securityMatcher("/**") // ✅ covers all endpoints
        // .csrf(csrf -> csrf.disable())
        // .authorizeHttpRequests(auth -> auth
        // .anyRequest().permitAll()
        // );
        // return http.build();
        http.securityMatcher("/**")
                .csrf(csrf -> csrf.disable())
            .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/Login/signin", "/Login/signup").permitAll()
                        .anyRequest().authenticated() // everything else secured
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
