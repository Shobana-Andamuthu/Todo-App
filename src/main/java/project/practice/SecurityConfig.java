package project.practice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() throws  Exception{
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http, jwtFilter jwtFilter) throws  Exception
    {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors->cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests((auth )-> auth
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()

                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource()
    {
        CorsConfiguration Configuration=new CorsConfiguration();
        Configuration.setAllowedOrigins(List.of("*"));
        Configuration.setAllowedMethods(List.of("GET","POST","PUT","DELETE"));
        Configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source= new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",Configuration);

        return source;
    }
}
