package br.com.cristal.erp.service.turma;

import br.com.cristal.erp.controller.turma.dto.TurmaRequest;
import br.com.cristal.erp.controller.turma.dto.TurmaResponse;
import br.com.cristal.erp.mapper.TurmaMapper;
import br.com.cristal.erp.repository.aluno.AlunoRepository;
import br.com.cristal.erp.repository.aluno.model.Aluno;
import br.com.cristal.erp.repository.mentor.Mentor;
import br.com.cristal.erp.repository.mentor.MentorRespository;
import br.com.cristal.erp.repository.turma.model.Turma;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    private AlunoRepository alunoRepository;
    private MentorRespository mentorRespository;

    public TurmaResponse criarTurma(@RequestBody TurmaRequest turmaRequest){

        Turma turma = TurmaMapper.INSTANCE.toTurma(turmaRequest);

//        Mentor mentor = mentorRespository.findById();

        List<Aluno> alunos = alunoRepository.findAllById(turmaRequest.getIdAlunos());

        turma.setAlunos(alunos);


        return TurmaMapper.INSTANCE.toResponseTurma(turma);
    }
}
