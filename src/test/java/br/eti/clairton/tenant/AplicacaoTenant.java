package br.eti.clairton.tenant;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;

@Dependent
@TenantType(Aplicacao.class)
public class AplicacaoTenant extends Tenant<Aplicacao> {

	@Inject
	public AplicacaoTenant(final TenantBuilder builder) {
		super(builder);
	}

	@Override
	public void add(@NotNull final CriteriaBuilder criteriaBuilder,
			@NotNull final CriteriaQuery<?> criteriaQuery,
			final @NotNull From<?, Aplicacao> from) {
		final Path<String> path = from.get(Aplicacao_.nome);
		final Predicate notEqual = criteriaBuilder.notEqual(path,
				"OutroTesteQueNãoDeveAparecerNaConsulta");
		criteriaQuery.where(notEqual);
	}
}
