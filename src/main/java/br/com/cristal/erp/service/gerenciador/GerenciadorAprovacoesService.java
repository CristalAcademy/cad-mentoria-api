package br.com.cristal.erp.service.gerenciador;

import br.com.cristal.erp.controller.aluno.dto.AlunoResponse;
import br.com.cristal.erp.exception.BadRequestsException;
import br.com.cristal.erp.mapper.AlunoMapper;
import br.com.cristal.erp.repository.aluno.AlunoRepository;
import br.com.cristal.erp.repository.aluno.model.Aluno;
import br.com.cristal.erp.repository.candidato.CandidatoRepository;
import br.com.cristal.erp.repository.candidato.model.Candidato;
import br.com.cristal.erp.service.candidato.CandidatoService;
import br.com.cristal.erp.service.notificacoes.ControleNotificacoes;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GerenciadorAprovacoesService {

    private CandidatoService candidatoService;
    private CandidatoRepository candidatoRepository;
    private AlunoRepository alunoRepository;

    private ControleNotificacoes controleNotificacoes;

    public void desativarCandidato(Candidato candidato){
        candidato.setAtivo(Boolean.FALSE);
        candidatoRepository.save(candidato);
    }

    public void isCandidatoAtivo(Candidato candidato) throws BadRequestsException {
        if(candidato.getAtivo().equals(Boolean.FALSE)){
            throw new BadRequestsException("Candidato Está Desativado no Momento");
        }
    }

    public AlunoResponse aprovar(Long id){
        Candidato candidato = candidatoService.findByIdOrThrowBadRequestException(id);
        isCandidatoAtivo(candidato);
        Aluno aluno = AlunoMapper.INSTANCE.toAluno(candidato);
        aluno.setId(candidato.getId());

        alunoRepository.save(aluno);
        desativarCandidato(candidato);
        controleNotificacoes.notificarAceite(candidato.getUsuario().getEmail());
        return AlunoMapper.INSTANCE.toAlunoResponse(aluno);
    }

    public void recusar(Long id) {
        Candidato candidato = candidatoService.findByIdOrThrowBadRequestException(id);
        isCandidatoAtivo(candidato);
        desativarCandidato(candidato);
        controleNotificacoes.notificarRecusa(candidato.getUsuario().getEmail());
    }
}
