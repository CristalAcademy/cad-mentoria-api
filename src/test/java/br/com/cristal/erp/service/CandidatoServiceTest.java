package br.com.cristal.erp.service;

import br.com.cristal.erp.config.usuarioConfig.CustomUserDetailsService;
import br.com.cristal.erp.controller.candidato.dto.CandidatoPutRequestBody;
import br.com.cristal.erp.mapper.CandidatoMapper;
import br.com.cristal.erp.repository.candidato.CandidatoRepository;
import br.com.cristal.erp.repository.candidato.model.Candidato;
import br.com.cristal.erp.repository.candidato.model.enums.ClasseCandidato;
import br.com.cristal.erp.repository.candidato.model.enums.DisponibilidadeCandidato;
import br.com.cristal.erp.repository.candidato.model.enums.StatusCandidato;
import br.com.cristal.erp.repository.usuario.model.Usuario;
import br.com.cristal.erp.service.candidato.CandidatoService;
import br.com.cristal.erp.util.JWTUtility;
import br.com.cristal.erp.util.filter.JwtFilter;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.expression.ExpressionException;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CandidatoServiceTest {

    @Captor
    private ArgumentCaptor<Candidato> captorCandidato;

    @MockBean
    private CandidatoRepository candidatoRepository;

    @MockBean
    private JWTUtility jwtUtility;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private CandidatoService candidatoService;

    @Test
    public void deveAtualizarCandidatoPeloUsuarioQuandoSucesso(){
        CandidatoPutRequestBody candidatoPutDto = criarDtoCandidatoPut();

        Usuario mockUsuario = criarUsuario();
        Candidato mockCandidato = criarCandidato();
        Optional<Candidato> candidatoOptional = Optional.of(mockCandidato);

        when(mockUsuario.getId()).thenReturn(1L);
        when(mockCandidato.getUsuario()).thenReturn(mockUsuario);
        when(mockCandidato.getId()).thenReturn(candidatoPutDto.getId());

        when(customUserDetailsService.loadUserByEmailAndReturnsUsuario(anyString()))
                .thenReturn(mockUsuario);

        when(jwtUtility.getEmailFromToken()).thenReturn(candidatoPutDto.getEmail());

        when(jwtUtility.verifyUser(ArgumentMatchers.any(Usuario.class), anyLong()))
                .thenReturn(candidatoPutDto.getId());

        when(candidatoRepository.findById(anyLong())).thenReturn(candidatoOptional);

        candidatoService.replace(candidatoPutDto, 1L);

        verify(candidatoRepository).save(captorCandidato.capture());
        Candidato candidatoSaved = captorCandidato.getValue();

        verify(candidatoRepository, times(1)).save(any(Candidato.class));
        Assertions.assertEquals(candidatoSaved.getUsuario(), mockUsuario);
        Assertions.assertEquals(candidatoSaved.getId(), candidatoPutDto.getId());
    }

    public CandidatoPutRequestBody criarDtoCandidatoPut(){
        CandidatoPutRequestBody candidatoPutRequestBody =
                CandidatoPutRequestBody
                        .builder()
                        .id(1L)
                        .nome("candidato")
                        .email("candidato@gmail.com")
                        .senha("novasenha")
                        .dtNasc(LocalDate.of(2001, 01, 01))
                        .trabalha(Boolean.TRUE)
                        .estuda(Boolean.TRUE)
                        .hrsDisponiveis(4)
                        .programou(Boolean.TRUE)
                        .classe(ClasseCandidato.MEDIO)
                        .motivacao("motivação")
                        .status(StatusCandidato.ENTREVISTA)
                        .entrevista(LocalDate.now().plusDays(1))
                        .disponibilidade(DisponibilidadeCandidato.NOITE)
                        .build();
        return candidatoPutRequestBody;
    }

    public Usuario criarUsuario(){
        return mock(Usuario.class);
    }

    public Candidato criarCandidato(){
        return mock(Candidato.class);
    }

    public CandidatoPutRequestBody criarDtoCandidatoPutVazio(){
        CandidatoPutRequestBody candidatoPutRequestBody =
                CandidatoPutRequestBody
                        .builder()
                        .id(1L)
                        .build();
        return candidatoPutRequestBody;
    }
}
