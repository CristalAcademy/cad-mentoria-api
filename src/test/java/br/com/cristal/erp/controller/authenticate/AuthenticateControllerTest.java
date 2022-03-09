package br.com.cristal.erp.controller.authenticate;

import br.com.cristal.erp.config.usuarioConfig.CustomUserDetails;
import br.com.cristal.erp.config.usuarioConfig.CustomUserDetailsService;
import br.com.cristal.erp.exception.AcessDeniedException;
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
    private ArgumentCaptor<UsernamePasswordAuthenticationToken> captorAuthentication;

    @MockBean
    private JWTUtility jwtUtility;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @Test
    public void deveCriarAuthenticateObjectQuandoSucesso() throws Exception {

        Usuario usuario = criarUsuario();

        Mockito.when(customUserDetailsService.loadUserByEmail(Mockito.any(String.class)))
                        .thenReturn(criarCustomUserDetails());

        Mockito.when(customUserDetailsService
                .loadUserByEmailAndReturnsUsuario(Mockito.any())).thenReturn(usuario);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "usuario@gmail.com");
        jsonObject.put("senha", "senha");

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(jsonObject)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(authenticationManager).authenticate(captorAuthentication.capture());
        UsernamePasswordAuthenticationToken authenticationCreated = captorAuthentication.getValue();

        Mockito.verify(authenticationManager).authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class));
        Assertions.assertEquals(authenticationCreated.getPrincipal(), usuario.getEmail());
        Assertions.assertEquals(authenticationCreated.getCredentials(), usuario.getSenha());
    }

    @Test
    public void deveLancarExceptionEmAuthenticateQuandoSucesso() throws Exception {

        Mockito.when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new AcessDeniedException("Falha na autenticação"));

       mockMvc.perform(MockMvcRequestBuilders.post("/authenticate")
                .content(String.valueOf(new JSONObject()))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is4xxClientError())
               .andExpect(MockMvcResultMatchers.status().isForbidden());

        Mockito.verifyNoInteractions(customUserDetailsService);
        Mockito.verifyNoInteractions(jwtUtility);
    }

    public CustomUserDetails criarCustomUserDetails() {
        CustomUserDetails customUserDetails = new CustomUserDetails
                (criarUsuario());
        return customUserDetails;
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
