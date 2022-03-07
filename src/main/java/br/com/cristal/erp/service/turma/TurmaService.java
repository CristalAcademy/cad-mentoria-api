package br.com.cristal.erp.service.turma;

import br.com.cristal.erp.config.usuarioConfig.CustomUserDetailsService;
import br.com.cristal.erp.controller.turma.dto.TurmaRequest;
import br.com.cristal.erp.controller.turma.dto.TurmaResponse;
import br.com.cristal.erp.exception.BadRequestsException;
import br.com.cristal.erp.mapper.TurmaMapper;
import br.com.cristal.erp.repository.aluno.AlunoRepository;
import br.com.cristal.erp.repository.aluno.model.Aluno;
import br.com.cristal.erp.repository.mentor.Mentor;
import br.com.cristal.erp.repository.mentor.MentorRespository;
import br.com.cristal.erp.repository.turma.TurmaRepository;
import br.com.cristal.erp.repository.turma.model.Turma;
import br.com.cristal.erp.repository.usuario.model.Usuario;
import br.com.cristal.erp.service.notificacoes.ControleNotificacoes;
import br.com.cristal.erp.util.email.EmailNotificacaoTurma;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TurmaService {

    private AlunoRepository alunoRepository;
    private MentorRespository mentorRespository;
    private ControleNotificacoes controleNotificacoes;
    private CustomUserDetailsService customUserDetailsService;
    private TurmaRepository turmaRepository;

    public TurmaResponse criarTurma(TurmaRequest turmaRequest){

        Usuario usuario = customUserDetailsService.loadUserSession();

        Turma turma = TurmaMapper.INSTANCE.toTurma(turmaRequest);

        Mentor mentor = mentorRespository.findById(usuario.getId())
                .orElseThrow(() -> new BadRequestsException("Mentor não encontrado"));

        List<Aluno> alunos = alunoRepository.findAllById(turmaRequest.getIdAlunos());

        alunos
                .stream()
                .map(Aluno::getUsuario)
                .map(Usuario::getEmail)
                .forEach(controleNotificacoes::notificarTurma);

        turma.setAlunos(alunos);
        turma.setMentor(mentor);

        turma = turmaRepository.save(turma);

        return TurmaMapper.INSTANCE.toResponseTurma(turma);
    }
}
