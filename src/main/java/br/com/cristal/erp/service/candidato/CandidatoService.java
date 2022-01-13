package br.com.cristal.erp.service.candidato;

import br.com.cristal.erp.controller.candidato.dto.CandidatoPostRequestBody;
import br.com.cristal.erp.controller.candidato.dto.CandidatoPutRequestBody;
import br.com.cristal.erp.controller.candidato.dto.CandidatoResponseBody;
import br.com.cristal.erp.repository.candidato.CandidatoRepository;
import br.com.cristal.erp.repository.candidato.model.Candidato;
import br.com.cristal.erp.service.candidato.mappers.CandidatoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CandidatoService {

    private CandidatoRepository candidatoRepository;
    private CandidatoMapper candidatoMapper;

    public CandidatoResponseBody replace(CandidatoPutRequestBody candidatoToBeUpdated) {

        Candidato savedCandidato = findByIdOrThrowBadRequestException(candidatoToBeUpdated.getId());

        Candidato candidatoToBeSaved = candidatoMapper.mapearTabelaCandidato(candidatoToBeUpdated, savedCandidato);
        candidatoToBeSaved.setId(savedCandidato.getId());
        return candidatoMapper.mapearCandidatoResponse(candidatoRepository.save(candidatoToBeSaved));
    }

    public Candidato findByIdOrThrowBadRequestException(long id){
        return candidatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anime not Found!"));
    }

    public void save(CandidatoPostRequestBody candidatoPost){

    }

    public void delete(Long id){

    }

    public void listAll(){

    }

}
