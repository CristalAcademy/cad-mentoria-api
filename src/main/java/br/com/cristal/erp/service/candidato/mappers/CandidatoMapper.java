package br.com.cristal.erp.service.candidato.mappers;

import br.com.cristal.erp.controller.candidato.dto.*;
import br.com.cristal.erp.repository.candidato.model.Candidato;
import br.com.cristal.erp.repository.usuario.model.Perfil;
import br.com.cristal.erp.repository.usuario.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class CandidatoMapper {
    public Candidato mapearTabelaCandidato(CandidatoRequestSocial candidatoRequestSocial){
        Candidato mappedCandidato = new Candidato();

        mappedCandidato.setClasse(candidatoRequestSocial.getClasse());
        mappedCandidato.setMotivacao(candidatoRequestSocial.getMotivacao());
        mappedCandidato.setEntrevista(candidatoRequestSocial.getEntrevista());
        mappedCandidato.setStatus(candidatoRequestSocial.getStatus());
        return mappedCandidato;
    }

    public Candidato mapearCompCandidato(CandidatoRequestComplemento candidatoRequestComplemento){
        Candidato mappedCompCandidato = new Candidato();

        mappedCompCandidato.setDtNasc(candidatoRequestComplemento.getDtNasc());
        mappedCompCandidato.setTrabalha(candidatoRequestComplemento.getTrabalha());
        mappedCompCandidato.setEstuda(candidatoRequestComplemento.getEstuda());
        mappedCompCandidato.setHrsDisponiveis(candidatoRequestComplemento.getHrsDisponiveis());
        mappedCompCandidato.setDisponibilidade(candidatoRequestComplemento.getDisponibilidade());
        mappedCompCandidato.setProgramou(candidatoRequestComplemento.getProgramou());

        return mappedCompCandidato;
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
        savedCandidato.setStatus(candidatoPutRequestBody.getStatus());

        return savedCandidato;
    }

    public CandidatoResponseBody mapearCandidatoResponse(Candidato candidato){
        CandidatoResponseBody responseBody = new CandidatoResponseBody();
        Usuario usuarioResp = new Usuario();

        usuarioResp.setNomecompleto(candidato.getNome());
        usuarioResp.setEmail(candidato.getEmail());
        usuarioResp.setSenha(candidato.getSenha());


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
        responseBody.setStatus(candidato.getStatus());
        return responseBody;
    }


    public Usuario mapearTabelaUser(CandidatoRequestUser candidatoRequestUser) {
        Usuario mappedUsuario = new Usuario();

        mappedUsuario.setNomecompleto(candidatoRequestUser.getNome());
        mappedUsuario.setEmail(candidatoRequestUser.getEmail());
        mappedUsuario.setSenha(candidatoRequestUser.getSenha());
        mappedUsuario.setPerfil(Perfil.CANDIDATO);

        return mappedUsuario;
    }

    public CandidatoResponseBody responseUser(Usuario usuario){
        CandidatoResponseBody respUser = new CandidatoResponseBody();

        respUser.setName(usuario.getNomecompleto());
        respUser.setEmail(usuario.getEmail());
        respUser.setSenha(usuario.getSenha());
        respUser.setPerfil(usuario.getPerfil());

        return respUser;
    }

}
