package br.com.cristal.erp.service.candidato;

import br.com.cristal.erp.controller.candidato.dto.*;
import br.com.cristal.erp.config.usuarioConfig.CustomUserDetailsService;
import br.com.cristal.erp.controller.candidato.dto.CandidatoPutRequestBody;
import br.com.cristal.erp.controller.candidato.dto.CandidatoResponseBody;
import br.com.cristal.erp.controller.usuario.dto.UsuarioRequest;
import br.com.cristal.erp.controller.usuario.dto.UsuarioResponse;
import br.com.cristal.erp.exception.AcessDeniedException;
import br.com.cristal.erp.exception.NotFound;
import br.com.cristal.erp.mapper.CandidatoMapper;
import br.com.cristal.erp.repository.candidato.CandidatoRepository;
import br.com.cristal.erp.repository.candidato.filter.CandidatoFiltro;
import br.com.cristal.erp.repository.candidato.model.Candidato;
import br.com.cristal.erp.repository.candidato.model.enums.ClasseCandidato;
import br.com.cristal.erp.repository.candidato.model.enums.StatusCandidato;
import br.com.cristal.erp.repository.usuario.model.Perfil;
import br.com.cristal.erp.repository.usuario.model.Usuario;
import br.com.cristal.erp.repository.candidato.specifications.CandidatoSpecifications;
import br.com.cristal.erp.service.usuario.UsuarioService;
import br.com.cristal.erp.util.JWTUtility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CandidatoService {

    private final CandidatoMapper candidatoMapper;
    private final CandidatoRepository candidatoRepository;
    private final JWTUtility jwtUtility;
    private final CustomUserDetailsService customUserDetailsService;

    private final UsuarioService usuarioService;


    public CandidatoResponseBody replace(CandidatoPutRequestBody requestPutCandidato, Long id) {
        // pega usuário pelo email
        Usuario usuario = customUserDetailsService
                .loadUserByEmailAndReturnsUsuario(
                        jwtUtility.getEmailFromToken()
                );

        Long idToBeUpdated = jwtUtility.verifyUser(usuario, id);

        Candidato candidatoFound = findByIdOrThrowBadRequestException(idToBeUpdated);
        Candidato candidatoToBeUpdated = CandidatoMapper.INSTANCE.toCandidato(requestPutCandidato);

        candidatoToBeUpdated.setId(candidatoFound.getId());
        candidatoToBeUpdated.setUsuario(candidatoFound.getUsuario());

        Candidato updatedCandidato = candidatoRepository.save(candidatoToBeUpdated);

        return CandidatoMapper.INSTANCE.toResponseBody(updatedCandidato);
    }

    public Candidato findByIdOrThrowBadRequestException(long id) {
        return candidatoRepository.findById(id)
                .orElseThrow(() -> new NotFound("Cadidato não encontrado"));
    }

    public CandidatoResponseBody findByIdOrThrowBadRequestExceptionReturnsCandidatoResponse(long id) {
        Candidato candidato = findByIdOrThrowBadRequestException(id);
        return CandidatoMapper.INSTANCE.toResponseBody(candidato);
    }

    public CandidatoResponseBody socialSave(
            CandidatoRequestSocial candidatoRequestSocial,
            String token) {

        String email = jwtUtility.getEmailFromToken();

        Usuario user = customUserDetailsService.loadUserByEmailAndReturnsUsuario(email);

        Candidato candidato = candidatoRepository.findById(user.getId())
                .orElseThrow(() -> new NotFound("Candidato não cadastrado"));

        Candidato candidatoSocial = CandidatoMapper.INSTANCE.toCandidatoSocial(candidatoRequestSocial);

        mapSocial(candidato, candidatoSocial);

        candidato = candidatoRepository.save(candidato);
        return CandidatoMapper.INSTANCE.toResponseBody(candidato);
    }

    private void mapSocial(Candidato candidato, Candidato candidatoSocial) {
        candidato.setEntrevista(candidatoSocial.getEntrevista());
        candidato.setMotivacao(candidatoSocial.getMotivacao());
        candidato.setClasse(candidatoSocial.getClasse());
        candidato.setStatus(StatusCandidato.AGUARDANDO);
    }

    public CandidatoResponseBody compSave(
            CandidatoRequestComplemento candidatoRequestComplemento,
            String token) {

        String email = jwtUtility.getEmailFromToken(token.substring(7));

        Usuario user = customUserDetailsService.loadUserByEmailAndReturnsUsuario(email);

        Candidato candidatoComp = CandidatoMapper.INSTANCE.toCandidatoComp(candidatoRequestComplemento);

        Candidato candidato = candidatoRepository.findById(user.getId())
                .orElse(CandidatoMapper.INSTANCE.toCandidatoComp(candidatoRequestComplemento));

        // TODO verificar um modo de por no mapstruct
        mapComplemento(candidato, candidatoComp);

        user.setPerfil(Perfil.CANDIDATO);

        candidato.setUsuario(user);

        candidato = candidatoRepository.save(candidato);

        return CandidatoMapper.INSTANCE.toResponseBody(candidato);
    }

    private void mapComplemento(Candidato candidato, Candidato candidatoComp) {
        candidato.setDtNasc(candidatoComp.getDtNasc());
        candidato.setTrabalha(candidatoComp.getTrabalha());
        candidato.setEstuda(candidatoComp.getEstuda());
        candidato.setHrsDisponiveis(candidatoComp.getHrsDisponiveis());
        candidato.setProgramou(candidatoComp.getProgramou());
        candidato.setDisponibilidade(candidatoComp.getDisponibilidade());
        candidato.setStatus(StatusCandidato.CADASTRO_SOCIAL);
    }


    public void delete(String headerToken, Long id) {
        String userEmail = jwtUtility.getEmailFromToken(headerToken);
        Usuario usuario = customUserDetailsService.loadUserByEmailAndReturnsUsuario(userEmail);

        if (!id.equals(usuario.getId())) {
            throw new AcessDeniedException("Permissão Negada");
        }
    }

    public CandidatoResponseBody userSave(UsuarioRequest candidatoUser) {

        UsuarioResponse usuarioResponse = usuarioService.cadastrarUsuario(candidatoUser);
        return candidatoMapper.toCandidato(usuarioResponse);
    }

    public void delete(Long id) {
        Candidato candidatoToBeDeleted = findByIdOrThrowBadRequestException(id);
        candidatoRepository.delete(candidatoToBeDeleted);
    }

    public StatusCandidato statusCandidato(long id) {
        Candidato candidato = candidatoRepository
                .findById(id)
                .orElseThrow(() -> new NotFound("Candidato Não Encontrado"));

        return candidato.getStatus();
    }

    public List<CandidatoResponseBody> buscaComFiltro(CandidatoFiltro filtro) {
        return candidatoRepository
                .findAll(new CandidatoSpecifications(filtro))
                .stream()
                .map(CandidatoMapper.INSTANCE::toResponseBody)
                .collect(Collectors.toList());
    }

    public ClasseCandidato classeCandidato(long id) {
        Candidato candidato = candidatoRepository
                .findById(id)
                .orElseThrow(() -> new NotFound("Candidato Não Encontrado"));

        return candidato.getClasse();
    }
}