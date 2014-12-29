package br.eti.clairton.tenant;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.validation.constraints.NotNull;

@Dependent
@TenantType(Recurso.class)
public class RecursoTenant extends Tenant<Recurso> {
	@Inject
	public RecursoTenant(final TenantBuilder builder) {
		super(builder);
	}

	@Override
	public void add(@NotNull final CriteriaBuilder criteriaBuilder,
			@NotNull final CriteriaQuery<?> criteriaQuery,
			final @NotNull From<?, Recurso> from) {
		final Join<Recurso, Aplicacao> join = from.join(Recurso_.aplicacao);
		builder.run(criteriaBuilder, criteriaQuery, join);
	}
}
