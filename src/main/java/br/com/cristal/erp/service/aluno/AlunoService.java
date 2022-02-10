package br.com.cristal.erp.service.aluno;

import br.com.cristal.erp.config.usuarioConfig.CustomUserDetailsService;
import br.com.cristal.erp.controller.Aluno.dto.AlunoRequest;
import br.com.cristal.erp.controller.Aluno.dto.AlunoResponse;
import br.com.cristal.erp.controller.usuario.dto.UsuarioResponseBody;
import br.com.cristal.erp.exception.AcessDeniedException;
import br.com.cristal.erp.exception.NotFound;
import br.com.cristal.erp.mapper.AlunoMapper;
import br.com.cristal.erp.repository.aluno.model.Aluno;
import br.com.cristal.erp.repository.aluno.AlunoRepository;
import br.com.cristal.erp.repository.aluno.filter.AlunoFiltro;
import br.com.cristal.erp.repository.aluno.specifications.AlunoSpecifications;
import br.com.cristal.erp.repository.usuario.model.Perfil;
import br.com.cristal.erp.repository.usuario.model.Usuario;
import br.com.cristal.erp.service.usuario.UsuarioService;
import br.com.cristal.erp.util.JWTUtility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AlunoService {

    private AlunoRepository alunoRepository;
    private AlunoMapper alunoMapper;
    private JWTUtility jwtUtility;
    private CustomUserDetailsService customUserDetailsService;
    private UsuarioService usuarioService;


    public AlunoResponse alunoSave(AlunoRequest alunoRequest) {

        Usuario usuario = customUserDetailsService
                .loadUserSession();

        Aluno aluno = alunoRepository.findById(usuario.getId())
                .orElse(AlunoMapper.INSTANCE.toAluno(alunoRequest));


        AlunoMapper.INSTANCE.updateAlunoFromDto(alunoRequest, aluno);


        aluno.setUsuario(usuario);

        aluno = alunoRepository.save(aluno);

        return alunoMapper.INSTANCE.toAlunoResponse(aluno);
    }

    public List<AlunoResponse> buscarComFiltro(AlunoFiltro filtro) {
        return alunoRepository
                .findAll(new AlunoSpecifications(filtro))
                .stream()
                .map(AlunoMapper.INSTANCE::toAlunoResponse)
                .collect(Collectors.toList());
    }


    public Aluno findByIdOrThrowBadRequestException(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new NotFound("Aluno não encontrado"));
    }

    private Long verifyUser(Usuario usuario, Long id) {
        Long idAluno = null;

        if (!usuario.getPerfil().equals(Perfil.ADMIN) && !usuario.getId().equals(id)) {
            throw new AcessDeniedException("Você não tem permissão para fazer atualizações nesse candidato");
        }
        if (usuario.getPerfil().equals(Perfil.ADMIN)) {
            idAluno = id;
        } else {
            idAluno = usuario.getId();
        }
        return idAluno;
    }

    public AlunoResponse updateAluno(AlunoRequest alunoRequest, Long id) {

        Usuario usuario = customUserDetailsService
                .loadUserSession();

        Long idToBeUpdated = verifyUser(usuario, id);

        Aluno alunoFound = findByIdOrThrowBadRequestException(idToBeUpdated);
        AlunoMapper.INSTANCE.updateAlunoFromDto(alunoRequest, alunoFound);

        Aluno updateAluno = alunoRepository.save(alunoFound);

        return AlunoMapper.INSTANCE.toAlunoResponse(updateAluno);
    }

    public void delete(Long id) {
        Usuario usuario = customUserDetailsService.loadUserSession();

        if (!id.equals(usuario.getId())) {
            throw new AcessDeniedException("Permissão Negada");
        }
    }
}
