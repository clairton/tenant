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
public class RecursoTenant extends Tenantable<Recurso> {
	private final TenantBuilder builder;

	@Inject
	public RecursoTenant(final TenantBuilder builder) {
		this.builder = builder;
	}

	@Override
	public Predicate add(@NotNull final CriteriaBuilder criteriaBuilder,
			final @NotNull From<?, Recurso> from,
			final @NotNull Object tenantValue) {
		final Join<Recurso, Aplicacao> join = from.join(Recurso_.aplicacao);
		return builder.run(criteriaBuilder, join, tenantValue);
	}
}
