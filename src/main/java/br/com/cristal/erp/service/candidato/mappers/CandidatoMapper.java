package br.com.cristal.erp.service.candidato.mappers;

import br.com.cristal.erp.controller.candidato.dto.CandidatoPostRequestBody;
import br.com.cristal.erp.controller.candidato.dto.CandidatoPutRequestBody;
import br.com.cristal.erp.controller.candidato.dto.CandidatoResponseBody;
import br.com.cristal.erp.repository.candidato.CandidatoRepository;
import br.com.cristal.erp.repository.candidato.model.Candidato;
import org.springframework.stereotype.Service;

@Service
public class CandidatoMapper {
    public Candidato mapearTabelaCandidato(CandidatoPostRequestBody candidatoPostRequestBody){
        Candidato mappedCandidato = new Candidato();

        mappedCandidato.setDtNasc(candidatoPostRequestBody.getIdade());
        mappedCandidato.setTrabalha(candidatoPostRequestBody.getTrabalha());
        mappedCandidato.setEstuda(candidatoPostRequestBody.getEstuda());
        mappedCandidato.setHrsDisponiveis(candidatoPostRequestBody.getHrsDisponiveis());
        mappedCandidato.setProgramou(candidatoPostRequestBody.getProgramou());
        mappedCandidato.setClasse(candidatoPostRequestBody.getClasseSocial());
        mappedCandidato.setMotivacao(candidatoPostRequestBody.getDescricaoPqMereceQuer());
        mappedCandidato.setEntrevista(candidatoPostRequestBody.getMarcarEntrevista());
        mappedCandidato.setDisponibilidade(candidatoPostRequestBody.getPeriodoDisponivel());

        return mappedCandidato;
    }

    public Candidato mapearTabelaCandidato(CandidatoPutRequestBody candidatoPutRequestBody, Candidato savedCandidato){
        savedCandidato.setDtNasc(candidatoPutRequestBody.getIdade());
        savedCandidato.setTrabalha(candidatoPutRequestBody.getTrabalha());
        savedCandidato.setEstuda(candidatoPutRequestBody.getEstuda());
        savedCandidato.setHrsDisponiveis(candidatoPutRequestBody.getHrsDisponiveis());
        savedCandidato.setProgramou(candidatoPutRequestBody.getProgramou());
        savedCandidato.setClasse(candidatoPutRequestBody.getClasseSocial());
        savedCandidato.setMotivacao(candidatoPutRequestBody.getDescricaoPqMereceQuer());
        savedCandidato.setEntrevista(candidatoPutRequestBody.getMarcarEntrevista());
        savedCandidato.setDisponibilidade(candidatoPutRequestBody.getPeriodoDisponivel());

        return savedCandidato;
    }

    public CandidatoResponseBody mapearCandidatoResponse(Candidato candidato){
        CandidatoResponseBody responseBody = new CandidatoResponseBody();
        responseBody.setIdade(candidato.getDtNasc());
        responseBody.setTrabalha(candidato.getTrabalha());
        responseBody.setEstuda(candidato.getEstuda());
        responseBody.setHrsDisponiveis(candidato.getHrsDisponiveis());
        responseBody.setProgramou(candidato.getProgramou());
        responseBody.setClasseSocial(candidato.getClasse());
        responseBody.setDescricaoPqMereceQuer(candidato.getMotivacao());
        responseBody.setMarcarEntrevista(candidato.getEntrevista());
        responseBody.setPeriodoDisponivel(candidato.getDisponibilidade());
        return responseBody;
    }
}
