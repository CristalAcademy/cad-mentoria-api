package br.com.cristal.erp.config;

import br.com.cristal.erp.repository.usuario.UsuarioRepository;
import br.com.cristal.erp.repository.usuario.model.Usuario;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final UsuarioRepository usuarioRepository;

    @Bean
    void setAdminPerfil(){
        usuarioRepository.save(
                Usuario.builder()
                        .nomeusuario("admin")
                        .senha("$2a$12$P2QHsp/rOG7i62ow23Z.5O4VjNp0C1JubkJjc6OpLC84SurH4UeWi")
                        .perfil("ADMIN")
                        .build()
        );
    }

    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .httpBasic().and()
                .authorizeRequests()
                .antMatchers("/authenticate")
                .permitAll()
                .anyRequest()
                .authenticated();

//        http
//                .csrf().disable()
//                .httpBasic().and()
//                .authorizeRequests()
//                .antMatchers("/authenticate")
//                .permitAll()
//                .antMatchers("/candidatos" , "/candidatos/**")
//                .hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.POST,"/candidatos" , "/candidatos/**")
//                .hasAuthority("ADMIN")
//                .anyRequest()
//                .authenticated();
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
