package br.com.cristal.erp.repository.aluno.specifications;

import br.com.cristal.erp.repository.aluno.filter.AlunoFiltro;
import br.com.cristal.erp.repository.aluno.model.Aluno;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

@RequiredArgsConstructor
public class AlunoSpecifications implements Specification<Aluno> {

    private final AlunoFiltro filtro;

    public void verificaTrabalha(Predicate predicate, CriteriaBuilder cb, Root<Aluno> root) {
        Optional.ofNullable(filtro)
                .map(AlunoFiltro::getTrabalha)
                .ifPresent(trabalha -> {
                    predicate.getExpressions().add(cb.equal(root.get("trabalha"), trabalha));
                });
    }

    public void verificaEstuda(Predicate predicate, CriteriaBuilder cb, Root<Aluno> root) {
        Optional.ofNullable(filtro)
                .map(AlunoFiltro::getEstuda)
                .ifPresent(estuda -> {
                    predicate.getExpressions().add(cb.equal(root.get("estuda"), estuda));
                });
    }

    public void verificaHorasDisponiveis(Predicate predicate, CriteriaBuilder cb, Root<Aluno> root) {
        Optional.ofNullable(filtro)
                .map(AlunoFiltro::getHrsDisponiveis)
                .ifPresent(horas -> {
                    predicate.getExpressions().add(cb.equal(root.get("hrsDisponiveis"), horas));
                });
    }

    public void verificaProgramou(Predicate predicate, CriteriaBuilder cb, Root<Aluno> root) {
        Optional.ofNullable(filtro)
                .map(AlunoFiltro::getProgramou)
                .ifPresent(programou -> {
                    predicate.getExpressions().add(cb.equal(root.get("programou"), programou));
                });
    }

    @Override
    public Predicate toPredicate(Root<Aluno> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        Predicate predicate = criteriaBuilder.conjunction();

        verificaTrabalha(predicate, criteriaBuilder, root);
        verificaEstuda(predicate, criteriaBuilder, root);
        verificaHorasDisponiveis(predicate, criteriaBuilder, root);
        verificaProgramou(predicate, criteriaBuilder, root);

        return predicate;
    }
}
