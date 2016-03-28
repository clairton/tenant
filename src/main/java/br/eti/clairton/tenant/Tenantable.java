package br.eti.clairton.tenant;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;

/**
 * Constract for model entity.
 * 
 * @author Clairton Rodrigo Heinzen<clairton.rodrigo@gmail.com>
 *
 * @param <T>
 *            type of model
 */
public abstract class Tenantable<T> {

	/**
	 * Create a predicate do join de tenantable entity.
	 * 
	 * @param builder
	 *            instance of {@link CriteriaBuilder}
	 * @param from
	 *            instance of current {@link From}
	 * @param value
	 *            value of de tenant
	 * @return {@link Predicate} for the tenant
	 */
	public abstract Predicate add(@NotNull CriteriaBuilder builder, @NotNull From<?, T> from, @NotNull Object value);
}
