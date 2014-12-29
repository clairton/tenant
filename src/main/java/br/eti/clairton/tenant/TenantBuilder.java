package br.eti.clairton.tenant;

import java.lang.annotation.Annotation;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;

@Dependent
public class TenantBuilder {

	private final Instance<Tenant<?>> tenants;

	@Inject
	public TenantBuilder(@Any final Instance<Tenant<?>> tenants) {
		super();
		this.tenants = tenants;
	}

	public Boolean exist(final Class<?> klazz) {
		final TenantType qualifier = getType(klazz);
		final Instance<Tenant<?>> instance = tenants.select(qualifier);
		return !instance.isUnsatisfied();
	}

	public <T> Predicate run(@NotNull final CriteriaBuilder criteriaBuilder,
			final @NotNull From<?, T> from, final @NotNull Predicate appendTo) {
		final Class<?> klazz = (Class<?>) from.getJavaType();
		if (exist(klazz)) {
			final TenantType qualifier = getType(klazz);
			final Instance<Tenant<?>> instance = tenants.select(qualifier);
			@SuppressWarnings("unchecked")
			final Tenant<T> tenant = (Tenant<T>) instance.get();
			return tenant.add(criteriaBuilder, from, appendTo);
		} else {
			return appendTo;
		}
	}

	public <T> Predicate run(@NotNull final CriteriaBuilder criteriaBuilder,
			final @NotNull From<?, T> from) {
		return run(criteriaBuilder, from,
				criteriaBuilder.equal(criteriaBuilder.literal(1), 1));
	}

	private final static TenantType getType(final Class<?> klazz) {
		return new TenantType() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return TenantType.class;
			}

			@Override
			public Class<?> value() {
				return klazz;
			}
		};
	}
}
