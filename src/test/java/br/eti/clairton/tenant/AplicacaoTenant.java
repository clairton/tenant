package br.eti.clairton.tenant;

import javax.enterprise.context.Dependent;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;

@Dependent// para ser geneciado pelo CDI
//Qualificando ele com um Tenant para o Tipo Aplicacação
@TenantType(Aplicacao.class)
public class AplicacaoTenant extends Tenantable<Aplicacao> {
	
	//Contrato que adicionara o Predicado que desejamos
	@Override
	public Predicate add(final @NotNull CriteriaBuilder criteriaBuilder,
			final @NotNull From<?, Aplicacao> from,
			final @NotNull @TenantValue Object tenantValue) {
		final Path<String> path = from.get(Aplicacao_.nome);
		final Predicate predicate = criteriaBuilder.notEqual(path, tenantValue);
		return predicate;
	}
}
