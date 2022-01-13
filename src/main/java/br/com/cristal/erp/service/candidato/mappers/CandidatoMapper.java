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

        mappedCandidato.setIdade(candidatoPostRequestBody.getIdade());
        mappedCandidato.setTrabalha(candidatoPostRequestBody.getTrabalha());
        mappedCandidato.setEstuda(candidatoPostRequestBody.getEstuda());
        mappedCandidato.setHrsDisponiveis(candidatoPostRequestBody.getHrsDisponiveis());
        mappedCandidato.setProgramou(candidatoPostRequestBody.getProgramou());
        mappedCandidato.setClasseSocial(candidatoPostRequestBody.getClasseSocial());
        mappedCandidato.setDescricaoPqMereceQuer(candidatoPostRequestBody.getDescricaoPqMereceQuer());
        mappedCandidato.setMarcarEntrevista(candidatoPostRequestBody.getMarcarEntrevista());
        mappedCandidato.setPeriodoDisponivel(candidatoPostRequestBody.getPeriodoDisponivel());

        return mappedCandidato;
    }

    public Candidato mapearTabelaCandidato(CandidatoPutRequestBody candidatoPutRequestBody, Candidato savedCandidato){
        savedCandidato.setIdade(candidatoPutRequestBody.getIdade());
        savedCandidato.setTrabalha(candidatoPutRequestBody.getTrabalha());
        savedCandidato.setEstuda(candidatoPutRequestBody.getEstuda());
        savedCandidato.setHrsDisponiveis(candidatoPutRequestBody.getHrsDisponiveis());
        savedCandidato.setProgramou(candidatoPutRequestBody.getProgramou());
        savedCandidato.setClasseSocial(candidatoPutRequestBody.getClasseSocial());
        savedCandidato.setDescricaoPqMereceQuer(candidatoPutRequestBody.getDescricaoPqMereceQuer());
        savedCandidato.setMarcarEntrevista(candidatoPutRequestBody.getMarcarEntrevista());
        savedCandidato.setPeriodoDisponivel(candidatoPutRequestBody.getPeriodoDisponivel());

        return savedCandidato;
    }

    public CandidatoResponseBody mapearCandidatoResponse(Candidato candidato){
        CandidatoResponseBody responseBody = new CandidatoResponseBody();
        responseBody.setId(candidato.getId());
        responseBody.setIdade(candidato.getIdade());
        responseBody.setTrabalha(candidato.getTrabalha());
        responseBody.setEstuda(candidato.getEstuda());
        responseBody.setHrsDisponiveis(candidato.getHrsDisponiveis());
        responseBody.setProgramou(candidato.getProgramou());
        responseBody.setClasseSocial(candidato.getClasseSocial());
        responseBody.setDescricaoPqMereceQuer(candidato.getDescricaoPqMereceQuer());
        responseBody.setMarcarEntrevista(candidato.getMarcarEntrevista());
        responseBody.setPeriodoDisponivel(candidato.getPeriodoDisponivel());
        return responseBody;
    }
}
