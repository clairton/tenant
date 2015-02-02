package br.eti.clairton.tenant;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;

public abstract class Tenantable<T> {

	public abstract Predicate add(
			final @NotNull CriteriaBuilder criteriaBuilder,
			final @NotNull From<?, T> from,
			final @NotNull Object tenantValue);
}
