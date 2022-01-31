package br.com.cristal.erp.service.candidato;

import br.com.cristal.erp.controller.candidato.dto.*;
import br.com.cristal.erp.exception.BadRequestsException;
import br.com.cristal.erp.mapper.CandidatoMapper;
import br.com.cristal.erp.mapper.UsuarioMapper;
import br.com.cristal.erp.repository.candidato.CandidatoRepository;
import br.com.cristal.erp.repository.candidato.model.Candidato;
import br.com.cristal.erp.repository.candidato.model.enums.ClasseCandidato;
import br.com.cristal.erp.repository.candidato.model.enums.StatusCandidato;
import br.com.cristal.erp.repository.usuario.UsuarioRepository;
import br.com.cristal.erp.repository.usuario.model.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CandidatoService {

    private CandidatoRepository candidatoRepository;
    private CandidatoMapper candidatoMapper;
    private UsuarioMapper usuarioMapper;
    private UsuarioRepository usuarioRepository;

    public CandidatoResponseBody replace(CandidatoPutRequestBody candidatoToBeUpdated) {

        Candidato candidatoFound = findByIdOrThrowBadRequestException(candidatoToBeUpdated.getId());
        Candidato candidatoToBeSaved = CandidatoMapper.INSTANCE.toCandidato(candidatoToBeUpdated);
        candidatoToBeSaved.setId(candidatoFound.getId());
        Candidato savedCandidato = candidatoRepository.save(candidatoToBeSaved);
        return CandidatoMapper.INSTANCE.toResponseBody(savedCandidato);
    }

    public Candidato findByIdOrThrowBadRequestException(long id){
        return candidatoRepository.findById(id)
                .orElseThrow(() -> new BadRequestsException("Cadidato não encontrado"));
    }

    public CandidatoResponseBody findByIdOrThrowBadRequestExceptionReturnsCandidatoResponse(long id){
        Candidato candidato = findByIdOrThrowBadRequestException(id);
        return CandidatoMapper.INSTANCE.toResponseBody(candidato);
    }

    public CandidatoResponseBody socialSave(CandidatoRequestSocial candidatoRequestSocial){
        Candidato candidato = CandidatoMapper.INSTANCE.toCandidatoSocial(candidatoRequestSocial);
        candidato = candidatoRepository.save(candidato);
        return candidatoMapper.toResponseBody(candidato);
    }

    public CandidatoResponseBody compSave(CandidatoRequestComplemento candidatoRequestComplemento){
        candidatoRepository.findById();
        Candidato candidato = CandidatoMapper.INSTANCE.toCandidatoComp(candidatoRequestComplemento);
        candidato = candidatoRepository.save(candidato);
        return CandidatoMapper.INSTANCE.toResponseBody(candidato);
    }

    public CandidatoResponseBody userSave(CandidatoRequestUser candidatoRequestUser){
        Usuario usuario = usuarioMapper.toUsuario(candidatoRequestUser);
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.userToCandidato(usuario);
    }

    public void delete(Long id){
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

    public ClasseCandidato classeCandidato(long id){
        Candidato candidato = candidatoRepository
                .findById(id)
                .orElseThrow(() -> new  BadRequestsException("Candidato Não Encontrado"));

        return candidato.getClasse();
    }
}