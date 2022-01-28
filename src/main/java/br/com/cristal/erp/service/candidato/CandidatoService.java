package br.com.cristal.erp.service.candidato;

import br.com.cristal.erp.controller.candidato.dto.*;
import br.com.cristal.erp.exception.BadRequestsException;
import br.com.cristal.erp.repository.candidato.CandidatoRepository;
import br.com.cristal.erp.repository.candidato.model.Candidato;
import br.com.cristal.erp.repository.candidato.model.enums.ClasseCandidato;
import br.com.cristal.erp.repository.candidato.model.enums.StatusCandidato;
import br.com.cristal.erp.repository.usuario.UsuarioRepository;
import br.com.cristal.erp.repository.usuario.model.Usuario;
import br.com.cristal.erp.service.candidato.mappers.CandidatoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CandidatoService {

    private CandidatoRepository candidatoRepository;
    private CandidatoMapper candidatoMapper;
    private UsuarioRepository usuarioRepository;

    public CandidatoResponseBody replace(CandidatoPutRequestBody candidatoToBeUpdated) {

        Candidato savedCandidato = findByIdOrThrowBadRequestException(candidatoToBeUpdated.getId());
        Candidato candidatoToBeSaved = candidatoMapper.mapearTabelaCandidato(candidatoToBeUpdated, savedCandidato);
        return candidatoMapper.mapearCandidatoResponse(candidatoRepository.save(candidatoToBeSaved));
    }

    public Candidato findByIdOrThrowBadRequestException(long id){
        return candidatoRepository.findById(id)
                .orElseThrow(() -> new BadRequestsException("Cadidato não encontrado"));
    }

    public CandidatoResponseBody findByIdOrThrowBadRequestExceptionReturnsCandidatoResponse(long id){
        Candidato candidato = findByIdOrThrowBadRequestException(id);
        return candidatoMapper.mapearCandidatoResponse(candidato);
    }

    public CandidatoResponseBody socialSave(CandidatoRequestSocial candidatoRequestSocial){
        Candidato candidato = candidatoMapper.mapearTabelaCandidato(candidatoRequestSocial);
        candidato = candidatoRepository.save(candidato);
        return candidatoMapper.mapearCandidatoResponse(candidato);
    }

    public CandidatoResponseBody compSave(CandidatoRequestComplemento candidatoRequestComplemento){
        Candidato candidato = candidatoMapper.mapearCompCandidato(candidatoRequestComplemento);
        candidato = candidatoRepository.save(candidato);
        return candidatoMapper.mapearCandidatoResponse(candidato);
    }

    public CandidatoResponseBody userSave(CandidatoRequestUser candidatoRequestUser){
        Usuario usuario = candidatoMapper.mapearTabelaUser(candidatoRequestUser);
        usuario = usuarioRepository.save(usuario);
        return  candidatoMapper.responseUser(usuario);
    }

    public void delete(Long id){
        Candidato candidatoToBeDeleted = findByIdOrThrowBadRequestException(id);
        candidatoRepository.delete(candidatoToBeDeleted);
    }

    public List<CandidatoResponseBody> listAll() {
        return candidatoRepository
                .findAll()
                .stream()
                .map(candidatoMapper::mapearCandidatoResponse)
                .collect(Collectors.toList());
    }

    public StatusCandidato statusCandidato(long id){
        Candidato candidato = candidatoRepository
                .findById(id)
                .orElseThrow(() -> new BadRequestsException("Candidato Não Encontrado"));

        return candidato.getStatus();
    }

    public ClasseCandidato classeCandidato(long id){
        Candidato candidato = candidatoRepository
                .findById(id)
                .orElseThrow(() -> new  BadRequestsException("Candidato Não Encontrado"));

        return candidato.getClasse();
    }
}