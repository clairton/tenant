package br.eti.clairton.tenant;

import java.lang.annotation.Annotation;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.validation.constraints.NotNull;

@Dependent
public class TenantBuilder {

	private final Instance<Tenant<?>> tenants;

	@Inject
	public TenantBuilder(@Any final Instance<Tenant<?>> tenants) {
		super();
		this.tenants = tenants;
	}

	public <T> void run(@NotNull final CriteriaBuilder criteriaBuilder,
			@NotNull final CriteriaQuery<?> criteriaQuery,
			final @NotNull From<?, T> from) {
		final Class<?> klazz = (Class<?>) from.getJavaType();
		final TenantType type = new TenantType() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return TenantType.class;
			}

			@Override
			public Class<?> value() {
				return klazz;
			}
		};
		final Instance<Tenant<?>> instance = tenants.select(type);
		if (instance.isUnsatisfied()) {
			return;
		}
		@SuppressWarnings("unchecked")
		final Tenant<T> tenant = (Tenant<T>) instance.get();
		tenant.add(criteriaBuilder, criteriaQuery, from);
	}
}
