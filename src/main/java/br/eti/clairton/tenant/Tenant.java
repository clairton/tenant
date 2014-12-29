package br.eti.clairton.tenant;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.validation.constraints.NotNull;

public abstract class Tenant<T> {
	protected final TenantBuilder builder;

	public Tenant(final TenantBuilder builder) {
		super();
		this.builder = builder;
	}

	public abstract void add(@NotNull final CriteriaBuilder criteriaBuilder,
			@NotNull final CriteriaQuery<?> criteriaQuery,
			final @NotNull From<?, T> from);
}
