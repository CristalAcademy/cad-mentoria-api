package br.com.cristal.erp.service.candidato.mappers;

import br.com.cristal.erp.controller.candidato.dto.CandidatoRequest;
import br.com.cristal.erp.repository.candidato.model.Candidato;
import org.springframework.stereotype.Service;

@Service
public class CandidatoMapper {
    public Candidato mapearTabelaCandidato(CandidatoRequest candidatoRequest){
        Candidato mappedCandidato = new Candidato();

        mappedCandidato.setIdade(candidatoRequest.getIdade());
        mappedCandidato.setTrabalha(candidatoRequest.getTrabalha());
        mappedCandidato.setEstuda(candidatoRequest.getEstuda());
        mappedCandidato.setHrsDisponiveis(candidatoRequest.getHrsDisponiveis());
        mappedCandidato.setProgramou(candidatoRequest.getProgramou());
        mappedCandidato.setClasseSocial(candidatoRequest.getClasseSocial());
        mappedCandidato.setDescricaoPqMereceQuer(candidatoRequest.getDescricaoPqMereceQuer());
        mappedCandidato.setMarcarEntrevista(candidatoRequest.getMarcarEntrevista());
        mappedCandidato.setPeriodoDisponivel(candidatoRequest.getPeriodoDisponivel());

        return mappedCandidato;
    }

    public Candidato mapearTabelaCandidato(CandidatoRequest candidatoRequest, Candidato savedCandidato){
        savedCandidato.setIdade(candidatoRequest.getIdade());
        savedCandidato.setTrabalha(candidatoRequest.getTrabalha());
        savedCandidato.setEstuda(candidatoRequest.getEstuda());
        savedCandidato.setHrsDisponiveis(candidatoRequest.getHrsDisponiveis());
        savedCandidato.setProgramou(candidatoRequest.getProgramou());
        savedCandidato.setClasseSocial(candidatoRequest.getClasseSocial());
        savedCandidato.setDescricaoPqMereceQuer(candidatoRequest.getDescricaoPqMereceQuer());
        savedCandidato.setMarcarEntrevista(candidatoRequest.getMarcarEntrevista());
        savedCandidato.setPeriodoDisponivel(candidatoRequest.getPeriodoDisponivel());

        return savedCandidato;
    }
}
