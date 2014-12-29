package br.eti.clairton.tenant;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;

@Dependent
@TenantType(Recurso.class)
public class RecursoTenant extends Tenant<Recurso> {
	@Inject
	public RecursoTenant(final TenantBuilder builder) {
		super(builder);
	}

	@Override
	public Predicate add(@NotNull final CriteriaBuilder criteriaBuilder,
			final @NotNull From<?, Recurso> from,
			final @NotNull Predicate appendTo) {
		final Join<Recurso, Aplicacao> join = from.join(Recurso_.aplicacao);
		return builder.run(criteriaBuilder, join, appendTo);
	}
}
