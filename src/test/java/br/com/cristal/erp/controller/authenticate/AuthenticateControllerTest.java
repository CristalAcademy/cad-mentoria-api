package br.com.cristal.erp.controller.authenticate;

import br.com.cristal.erp.config.usuarioConfig.CustomUserDetails;
import br.com.cristal.erp.config.usuarioConfig.CustomUserDetailsService;
import br.com.cristal.erp.repository.usuario.model.Perfil;
import br.com.cristal.erp.repository.usuario.model.Usuario;
import br.com.cristal.erp.util.JWTUtility;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(AuthenticateController.class)
public class AuthenticateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Captor
    private ArgumentCaptor<UsernamePasswordAuthenticationToken> captor_authentication;

    @MockBean
    private JWTUtility jwtUtility;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @Test
    public void deveRetornarJWTResponse_QuandoSucesso() throws Exception {

        Usuario usuario = criarUsuario();

        Mockito.when(customUserDetailsService.loadUserByEmail(Mockito.any(String.class)))
                        .thenReturn(criarCustomUserDetails());
        Mockito.when(customUserDetailsService
                .loadUserByEmailAndReturnsUsuario(Mockito.any())).thenReturn(usuario);

        JSONObject json_object = new JSONObject();
        json_object.put("email", "usuario@gmail.com");
        json_object.put("senha", "senha");

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(json_object)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(authenticationManager).authenticate(captor_authentication.capture());
        UsernamePasswordAuthenticationToken authentication_created = captor_authentication.getValue();

        Mockito.verify(authenticationManager).authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class));
        Assertions.assertEquals(authentication_created.getPrincipal(), usuario.getEmail());
        Assertions.assertEquals(authentication_created.getCredentials(), usuario.getSenha());
    }

    public CustomUserDetails criarCustomUserDetails(){
        CustomUserDetails custom_details = new CustomUserDetails
                (criarUsuario());

        return custom_details;
    }

    public Usuario criarUsuario(){
        Usuario usuario = new Usuario(1L,
                "Usuario dos users",
                "usuario@gmail.com",
                "senha",
                Perfil.CANDIDATO);

        return usuario;
    }

}
