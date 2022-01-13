package br.com.cristal.erp.service.candidato.mappers;

import br.com.cristal.erp.controller.candidato.dto.CandidatoPostRequestBody;
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

    public Candidato mapearTabelaCandidato(CandidatoPostRequestBody candidatoPostRequestBody, Candidato savedCandidato){
        savedCandidato.setIdade(candidatoPostRequestBody.getIdade());
        savedCandidato.setTrabalha(candidatoPostRequestBody.getTrabalha());
        savedCandidato.setEstuda(candidatoPostRequestBody.getEstuda());
        savedCandidato.setHrsDisponiveis(candidatoPostRequestBody.getHrsDisponiveis());
        savedCandidato.setProgramou(candidatoPostRequestBody.getProgramou());
        savedCandidato.setClasseSocial(candidatoPostRequestBody.getClasseSocial());
        savedCandidato.setDescricaoPqMereceQuer(candidatoPostRequestBody.getDescricaoPqMereceQuer());
        savedCandidato.setMarcarEntrevista(candidatoPostRequestBody.getMarcarEntrevista());
        savedCandidato.setPeriodoDisponivel(candidatoPostRequestBody.getPeriodoDisponivel());

        return savedCandidato;
    }
}
