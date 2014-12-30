package br.eti.clairton.tenant;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;

public abstract class Tenant<T> {
	protected final TenantBuilder builder;

	public Tenant(final TenantBuilder builder) {
		super();
		this.builder = builder;
	}

	public abstract List<Predicate> add(
			@NotNull final CriteriaBuilder criteriaBuilder,
			final @NotNull From<?, T> from,
			final @NotNull List<Predicate> appendTo);
}
