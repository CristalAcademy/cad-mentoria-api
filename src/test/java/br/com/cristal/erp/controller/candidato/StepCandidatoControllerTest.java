package br.com.cristal.erp.controller.candidato;

import br.com.cristal.erp.config.usuarioConfig.CustomUserDetailsService;
import br.com.cristal.erp.controller.candidato.dto.CandidatoRequestComplemento;
import br.com.cristal.erp.controller.candidato.dto.CandidatoResponseBody;
import br.com.cristal.erp.controller.usuario.dto.UsuarioRequest;
import br.com.cristal.erp.repository.candidato.model.enums.DisponibilidadeCandidato;
import br.com.cristal.erp.service.candidato.CandidatoService;
import br.com.cristal.erp.util.JWTUtility;
import br.com.cristal.erp.util.filter.JwtFilter;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@WebMvcTest(StepCandidatoController.class)
public class StepCandidatoControllerTest {

    @Autowired
    private MockMvc mockMvc; // Faz Uma Requisição

    @MockBean
    private CandidatoService candidatoService; // Simular a classe para retornar vazio

    @MockBean
    private JWTUtility jwtUtility;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @Captor
    private ArgumentCaptor<UsuarioRequest> captorUsuario;

    @Captor
    private ArgumentCaptor<CandidatoRequestComplemento> captorComplemento;

    @Test
    public void verificarSeUsuarioFoiCriado() throws Exception {
        Mockito.when(candidatoService.userSave(Mockito.any(UsuarioRequest.class)))
                .thenReturn(new CandidatoResponseBody());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nomeCompleto", "Gean");
        jsonObject.put("email", "manu@gmail.com");
        jsonObject.put("senha", "123456789");

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/candidatos/step/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(jsonObject)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(candidatoService).userSave(captorUsuario.capture());
        UsuarioRequest usuario = captorUsuario.getValue();

        Assertions.assertEquals(usuario.getNomeCompleto(), jsonObject.get("nomeCompleto"));
        Assertions.assertEquals(usuario.getEmail(), jsonObject.get("email"));
        Assertions.assertEquals(usuario.getSenha(), jsonObject.get("senha"));

        Mockito.verify(candidatoService).userSave(Mockito.any(UsuarioRequest.class));
    }

    @Test
    public void verificarSeComplementoFoiAd() throws Exception{
        Mockito.when(candidatoService.compSave(Mockito.any(CandidatoRequestComplemento.class), Mockito.anyString()))
                .thenReturn(criarComplemento());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dtNasc", "2002-04-24");
        jsonObject.put("trabalha", true);
        jsonObject.put("estuda", true);
        jsonObject.put("hrsDisponiveis", 4);
        jsonObject.put("programou", true);
        jsonObject.put("disponibilidade", DisponibilidadeCandidato.TARDE);

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/candidatos/step/complemento")
                        .header("Authorization", "usuario")
                        .with(SecurityMockMvcRequestPostProcessors.user("usuario").roles("USER", "ADMIN"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(jsonObject)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(candidatoService).compSave(captorComplemento.capture(), Mockito.anyString());
        CandidatoRequestComplemento complemento = captorComplemento.getValue();

        Assertions.assertEquals(complemento.getDtNasc().toString(), jsonObject.get("dtNasc"));
        Assertions.assertEquals(complemento.getTrabalha(), jsonObject.get("trabalha"));
        Assertions.assertEquals(complemento.getEstuda(), jsonObject.get("estuda"));
        Assertions.assertEquals(complemento.getDisponibilidade(), jsonObject.get("disponibilidade"));
        Assertions.assertEquals(complemento.getHrsDisponiveis(), jsonObject.get("hrsDisponiveis"));
        Assertions.assertEquals(complemento.getProgramou(), jsonObject.get("programou"));

        Mockito.verify(candidatoService).compSave(Mockito.any(CandidatoRequestComplemento.class), Mockito.anyString());
    }

    public CandidatoResponseBody criarComplemento(){
        return CandidatoResponseBody.builder()
                .hrsDisponiveis(4)
                .disponibilidade(DisponibilidadeCandidato.TARDE)
                .dtNasc(LocalDate.of(2002, 04, 24))
                .estuda(true)
                .programou(true)
                .trabalha(true)
                .build();
    }
}
