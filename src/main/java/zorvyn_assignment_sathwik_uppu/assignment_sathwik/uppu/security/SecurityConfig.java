package zorvyn_assignment_sathwik_uppu.assignment_sathwik.uppu.security;
import org.springframework.security.config.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
    .requestMatchers("/api/records/summary").permitAll()  // FIRST
    .requestMatchers("/api/records/**").hasAnyRole("ADMIN", "ANALYST")
    .anyRequest().authenticated()
)
          .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    // 👇 ADD HERE
    @Bean
    public UserDetailsService users() {
        return new InMemoryUserDetailsManager(
            User.withUsername("admin").password("{noop}admin1234").roles("ADMIN").build(),
            User.withUsername("analyst").password("{noop}analyst1234").roles("ANALYST").build(),
            User.withUsername("viewer").password("{noop}viewer1234").roles("VIEWER").build()
        );

    }
}