package br.com.cristal.erp.repository.candidato.specifications;

import br.com.cristal.erp.repository.candidato.filter.CandidatoFiltro;
import br.com.cristal.erp.repository.candidato.model.Candidato;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Optional;

@RequiredArgsConstructor
public class CandidatoSpecifications implements Specification<Candidato> {

    private final CandidatoFiltro filtro;

    public void verificaTrabalha(Predicate predicate, CriteriaBuilder cb, Root<Candidato> root) {
        Optional.ofNullable(filtro)
                .map(CandidatoFiltro::getTrabalha)
                .ifPresent(trabalha -> {
                    predicate.getExpressions().add(cb.equal(root.get("trabalha"), trabalha));
                });
    }

    public void verificaEstuda(Predicate predicate, CriteriaBuilder cb, Root<Candidato> root) {
        Optional.ofNullable(filtro)
                .map(CandidatoFiltro::getEstuda)
                .ifPresent(estuda -> {
                    predicate.getExpressions().add(cb.equal(root.get("estuda"), estuda));
                });
    }

    public void verificaHorasDisponiveis(Predicate predicate, CriteriaBuilder cb, Root<Candidato> root) {
        Optional.ofNullable(filtro)
                .map(CandidatoFiltro::getHrsDisponiveis)
                .ifPresent(horas -> {
                    predicate.getExpressions().add(cb.equal(root.get("hrsDisponiveis"), horas));
                });
    }

    public void verificaProgramou(Predicate predicate, CriteriaBuilder cb, Root<Candidato> root) {
        Optional.ofNullable(filtro)
                .map(CandidatoFiltro::getProgramou)
                .ifPresent(programou -> {
                    predicate.getExpressions().add(cb.equal(root.get("programou"), programou));
                });
    }

    public void verificaClasseSocial(Predicate predicate, CriteriaBuilder cb, Root<Candidato> root) {
        Optional.ofNullable(filtro)
                .map(CandidatoFiltro::getClasse)
                .ifPresent(classe -> {
                    predicate.getExpressions().add(cb.equal(root.get("classe"), classe));
                });
    }

    public void verificaStatus(Predicate predicate, CriteriaBuilder cb, Root<Candidato> root) {
        Optional.ofNullable(filtro)
                .map(CandidatoFiltro::getStatus)
                .ifPresent(status -> {
                    predicate.getExpressions().add(cb.equal(root.get("status"), status));
                });
    }



    @Override
    public Predicate toPredicate(Root<Candidato> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        Predicate predicate = criteriaBuilder.conjunction();

        verificaTrabalha(predicate, criteriaBuilder, root);
        verificaEstuda(predicate, criteriaBuilder, root);
        verificaHorasDisponiveis(predicate, criteriaBuilder, root);
        verificaProgramou(predicate, criteriaBuilder, root);
        verificaClasseSocial(predicate, criteriaBuilder, root);
        verificaStatus(predicate, criteriaBuilder, root);

        return predicate;
    }
}
