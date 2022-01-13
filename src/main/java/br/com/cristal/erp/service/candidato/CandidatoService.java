package br.com.cristal.erp.service.candidato;

import br.com.cristal.erp.controller.candidato.dto.CandidatoPostRequestBody;
import br.com.cristal.erp.controller.candidato.dto.CandidatoPutRequestBody;
import br.com.cristal.erp.controller.candidato.dto.CandidatoResponseBody;
import br.com.cristal.erp.exception.BadRequestsException;
import br.com.cristal.erp.repository.candidato.CandidatoRepository;
import br.com.cristal.erp.repository.candidato.model.Candidato;
import br.com.cristal.erp.service.candidato.mappers.CandidatoMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CandidatoService {

    private CandidatoRepository candidatoRepository;
    private CandidatoMapper candidatoMapper;

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

    public CandidatoResponseBody save(CandidatoPostRequestBody candidatoPost){
        Candidato candidato = candidatoMapper.mapearTabelaCandidato(candidatoPost);
        candidato = candidatoRepository.save(candidato);
        return candidatoMapper.mapearCandidatoResponse(candidato);
    }

    public void delete(Long id){
        Candidato candidatoToBeDeleted = findByIdOrThrowBadRequestException(id);
        candidatoRepository.delete(candidatoToBeDeleted);
    }

    public List<Candidato> listAll(){
        return candidatoRepository.findAll();
    }

}
