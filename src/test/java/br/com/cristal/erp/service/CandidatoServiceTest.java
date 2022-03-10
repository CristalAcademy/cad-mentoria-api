package br.com.cristal.erp.service;

import br.com.cristal.erp.config.usuarioConfig.CustomUserDetailsService;
import br.com.cristal.erp.controller.candidato.dto.CandidatoPutRequestBody;
import br.com.cristal.erp.exception.AcessDeniedException;
import br.com.cristal.erp.repository.candidato.CandidatoRepository;
import br.com.cristal.erp.repository.candidato.model.Candidato;
import br.com.cristal.erp.repository.candidato.model.enums.ClasseCandidato;
import br.com.cristal.erp.repository.candidato.model.enums.DisponibilidadeCandidato;
import br.com.cristal.erp.repository.candidato.model.enums.StatusCandidato;
import br.com.cristal.erp.repository.usuario.model.Usuario;
import br.com.cristal.erp.service.candidato.CandidatoService;
import br.com.cristal.erp.util.JWTUtility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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

        // instancia oque será usado
        CandidatoPutRequestBody candidatoPutDto = criarDtoCandidatoPut();
        Usuario mockUsuario = criarUsuario();
        Candidato mockCandidato = criarCandidato();
        Optional<Candidato> candidatoOptional = Optional.of(mockCandidato);

        // define o comportamento dos mocks
        when(mockUsuario.getId()).thenReturn(1L);
        when(mockCandidato.getUsuario()).thenReturn(mockUsuario);
        when(mockCandidato.getId()).thenReturn(candidatoPutDto.getId());

        when(customUserDetailsService.loadUserByEmailAndReturnsUsuario(anyString()))
                .thenReturn(mockUsuario);

        when(jwtUtility.getEmailFromToken()).thenReturn(candidatoPutDto.getEmail());

        when(jwtUtility.verifyUser(ArgumentMatchers.any(Usuario.class), anyLong()))
                .thenReturn(candidatoPutDto.getId());

        when(candidatoRepository.findById(anyLong())).thenReturn(candidatoOptional);

        // chama o método a ser testado
        candidatoService.replace(candidatoPutDto, 1L);

        // faz as verificações dentro do teste
        verify(candidatoRepository).save(captorCandidato.capture());
        Candidato candidatoSaved = captorCandidato.getValue();

        verify(candidatoRepository, times(1)).save(eq(candidatoSaved));
        Assertions.assertEquals(candidatoSaved.getUsuario(), mockUsuario);
        Assertions.assertEquals(candidatoSaved.getId(), candidatoPutDto.getId());
    }

    @Test
    public void deveFalharTokenNaoEncontraUsuarioQuandoSucesso(){

        CandidatoPutRequestBody candidatoDto = criarDtoCandidatoPut();

        when(jwtUtility.getEmailFromToken()).thenReturn(candidatoDto.getEmail());

        when(customUserDetailsService.loadUserByEmailAndReturnsUsuario(anyString()))
                .thenThrow(new UsernameNotFoundException("Usuário Ínvalido!"));

        try{
            candidatoService.replace(candidatoDto, candidatoDto.getId());
        }catch (Exception e){
            Assertions.assertEquals(UsernameNotFoundException.class, e.getClass());
            Assertions.assertEquals("Usuário Ínvalido!", e.getMessage());
        }

        verify(jwtUtility, never()).verifyUser(any(), any());
        verify(candidatoRepository, never()).save(any());
    }

    @Test
    public void deveFalharComUsuarioSemPermissaoQuandoSucesso(){
        CandidatoPutRequestBody candidatoPutDto = criarDtoCandidatoPut();
        Usuario mockUsuario = criarUsuario();

        when(customUserDetailsService.loadUserByEmailAndReturnsUsuario(anyString()))
                .thenReturn(mockUsuario);

        when(jwtUtility.getEmailFromToken()).thenReturn(candidatoPutDto.getEmail());

        when(jwtUtility.verifyUser(eq(mockUsuario), anyLong()))
                .thenThrow(new AcessDeniedException("Você não tem permissão para fazer atualizações nesse candidato"));

        try{
            candidatoService.replace(candidatoPutDto, candidatoPutDto.getId());
        } catch (Exception e){
            Assertions.assertEquals(AcessDeniedException.class, e.getClass());
            Assertions.assertEquals
                    ("Você não tem permissão para fazer atualizações nesse candidato", e.getMessage());
        }

        verify(candidatoRepository, never()).save(any());
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
}
