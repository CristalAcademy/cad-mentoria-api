package br.com.cristal.erp.service.candidato.mappers;

import br.com.cristal.erp.controller.candidato.dto.CandidatoPostRequestBody;
import br.com.cristal.erp.controller.candidato.dto.CandidatoPutRequestBody;
import br.com.cristal.erp.controller.candidato.dto.CandidatoResponseBody;
import br.com.cristal.erp.repository.candidato.model.Candidato;
import org.springframework.stereotype.Service;

@Service
public class CandidatoMapper {
    public Candidato mapearTabelaCandidato(CandidatoPostRequestBody candidatoPostRequestBody){
        Candidato mappedCandidato = new Candidato();

        mappedCandidato.setDtNasc(candidatoPostRequestBody.getDtNasc());
        mappedCandidato.setTrabalha(candidatoPostRequestBody.getTrabalha());
        mappedCandidato.setEstuda(candidatoPostRequestBody.getEstuda());
        mappedCandidato.setHrsDisponiveis(candidatoPostRequestBody.getHrsDisponiveis());
        mappedCandidato.setProgramou(candidatoPostRequestBody.getProgramou());
        mappedCandidato.setClasse(candidatoPostRequestBody.getClasse());
        mappedCandidato.setMotivacao(candidatoPostRequestBody.getMotivacao());
        mappedCandidato.setEntrevista(candidatoPostRequestBody.getEntrevista());
        mappedCandidato.setDisponibilidade(candidatoPostRequestBody.getDisponibilidade());

        return mappedCandidato;
    }

    public Candidato mapearTabelaCandidato(CandidatoPutRequestBody candidatoPutRequestBody, Candidato savedCandidato){
        savedCandidato.setDtNasc(candidatoPutRequestBody.getDtNasc());
        savedCandidato.setTrabalha(candidatoPutRequestBody.getTrabalha());
        savedCandidato.setEstuda(candidatoPutRequestBody.getEstuda());
        savedCandidato.setHrsDisponiveis(candidatoPutRequestBody.getHrsDisponiveis());
        savedCandidato.setProgramou(candidatoPutRequestBody.getProgramou());
        savedCandidato.setClasse(candidatoPutRequestBody.getClasse());
        savedCandidato.setMotivacao(candidatoPutRequestBody.getMotivacao());
        savedCandidato.setEntrevista(candidatoPutRequestBody.getEntrevista());
        savedCandidato.setDisponibilidade(candidatoPutRequestBody.getDisponibilidade());

        return savedCandidato;
    }

    public CandidatoResponseBody mapearCandidatoResponse(Candidato candidato){
        CandidatoResponseBody responseBody = new CandidatoResponseBody();
        responseBody.setId(candidato.getId());
        responseBody.setDtNasc(candidato.getDtNasc());
        responseBody.setTrabalha(candidato.getTrabalha());
        responseBody.setEstuda(candidato.getEstuda());
        responseBody.setHrsDisponiveis(candidato.getHrsDisponiveis());
        responseBody.setProgramou(candidato.getProgramou());
        responseBody.setClasse(candidato.getClasse());
        responseBody.setMotivacao(candidato.getMotivacao());
        responseBody.setEntrevista(candidato.getEntrevista());
        responseBody.setDisponibilidade(candidato.getDisponibilidade());
        return responseBody;
    }
}
