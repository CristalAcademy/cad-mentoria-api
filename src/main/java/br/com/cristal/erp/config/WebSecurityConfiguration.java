package br.com.cristal.erp.config;

import br.com.cristal.erp.repository.usuario.UsuarioRepository;
import br.com.cristal.erp.repository.usuario.model.Perfil;
import br.com.cristal.erp.repository.usuario.model.Usuario;
import br.com.cristal.erp.util.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final UsuarioRepository usuarioRepository;
    private final JwtFilter jwtFilter;

    @Bean
    void setAdminPerfil(){

        Usuario usuario = usuarioRepository.findByEmail("admin@admin.com");

        if (usuario == null ) {
            usuarioRepository.save(
                    Usuario.builder()
                            .nomecompleto("admin")
                            .email("alanisemanuela950@gmail.com")
                            .senha("$2a$12$P2QHsp/rOG7i62ow23Z.5O4VjNp0C1JubkJjc6OpLC84SurH4UeWi")
                            .perfil(Perfil.ADMIN)
                            .build()
            );

        }

    }

    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
            provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/authenticate", "**/authenticate")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/recuperar-senha")
                .permitAll()
                .antMatchers(HttpMethod.PATCH,"/recuperar-senha/confirmar")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
