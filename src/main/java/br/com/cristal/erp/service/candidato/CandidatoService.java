package br.com.cristal.erp.service.candidato;

import br.com.cristal.erp.config.usuarioConfig.CustomUserDetailsService;
import br.com.cristal.erp.controller.candidato.dto.CandidatoPostRequestBody;
import br.com.cristal.erp.controller.candidato.dto.CandidatoPutRequestBody;
import br.com.cristal.erp.controller.candidato.dto.CandidatoResponseBody;
import br.com.cristal.erp.exception.AcessDeniedException;
import br.com.cristal.erp.exception.BadRequestsException;
import br.com.cristal.erp.mapper.CandidatoMapper;
import br.com.cristal.erp.repository.candidato.CandidatoRepository;
import br.com.cristal.erp.repository.candidato.model.Candidato;
import br.com.cristal.erp.repository.candidato.model.enums.StatusCandidato;
import br.com.cristal.erp.repository.usuario.model.Usuario;
import br.com.cristal.erp.util.JWTUtility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CandidatoService {

    private CandidatoRepository candidatoRepository;
    private CandidatoMapper candidatoMapper;
    private JWTUtility jwtUtility;
    private CustomUserDetailsService customUserDetailsService;


    public CandidatoResponseBody replace(String headerToken, CandidatoPutRequestBody requestPutCandidato) {

        // pega usuário pelo email
        Usuario usuario = customUserDetailsService
                .loadUserByEmailAndReturnsUsuario(
                        jwtUtility.getEmailFromToken(headerToken.substring(7))
                );

        Candidato candidatoFound = findByIdOrThrowBadRequestException(usuario.getId());
        Candidato candidatoToBeUpdated = CandidatoMapper.INSTANCE.toCandidato(requestPutCandidato);

        candidatoToBeUpdated.setId(candidatoFound.getId());

        Candidato updatedCandidato = candidatoRepository.save(candidatoToBeUpdated);

        return CandidatoMapper.INSTANCE.toResponseBody(updatedCandidato);

    }

    public Candidato findByIdOrThrowBadRequestException(long id){
        return candidatoRepository.findById(id)
                .orElseThrow(() -> new BadRequestsException("Cadidato não encontrado"));
    }

    public CandidatoResponseBody findByIdOrThrowBadRequestExceptionReturnsCandidatoResponse(long id){
        Candidato candidato = findByIdOrThrowBadRequestException(id);
        return CandidatoMapper.INSTANCE.toResponseBody(candidato);
    }

    public CandidatoResponseBody save(String headerToken, CandidatoPostRequestBody candidatoPost){

        // pega usuário pelo email
        Usuario usuario = customUserDetailsService
                .loadUserByEmailAndReturnsUsuario(
                    jwtUtility.getEmailFromToken(headerToken.substring(7))
                );

        // excessão caso já esteja cadastrado
        if(candidatoRepository.findById(usuario.getId()).isPresent()){
            throw new BadRequestsException("Candidato Já Cadastrado");
        }

        Candidato candidatoToBeSaved = CandidatoMapper.INSTANCE.toCandidato(candidatoPost);
        candidatoToBeSaved.setId(usuario.getId());

        Candidato candidato = candidatoRepository.save(candidatoToBeSaved);

        return CandidatoMapper.INSTANCE.toResponseBody(candidato);
    }

    public void delete(String headerToken, Long id){
        String userEmail = jwtUtility.getEmailFromToken(headerToken.substring(7));
        Usuario usuario = customUserDetailsService.loadUserByEmailAndReturnsUsuario(userEmail);

        if(!id.equals(usuario.getId())){
            throw new AcessDeniedException("Permissão Negada");
        }

        Candidato candidatoToBeDeleted = findByIdOrThrowBadRequestException(id);
        candidatoRepository.delete(candidatoToBeDeleted);
    }

    public List<CandidatoResponseBody> listAll() {
        return candidatoRepository
                .findAll()
                .stream()
                .map(candidatoMapper::toResponseBody)
                .collect(Collectors.toList());
    }

    public StatusCandidato statusCandidato(long id){
        Candidato candidato = candidatoRepository
                .findById(id)
                .orElseThrow(() -> new BadRequestsException("Candidato Não Encontrado"));

        return candidato.getStatus();
    }
}