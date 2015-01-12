package br.eti.clairton.tenant;

import java.lang.annotation.Annotation;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.Logger;

@Dependent
public class TenantBuilder {

	private final Instance<Tenantable<?>> tenants;

	private final Logger logger;

	@Inject
	public TenantBuilder(@Any final Instance<Tenantable<?>> tenants,
			@Default final Logger logger) {
		super();
		this.tenants = tenants;
		this.logger = logger;
	}

	public Boolean exist(final Class<?> klazz) {
		final TenantType qualifier = getType(klazz);
		final Instance<Tenantable<?>> instance = tenants.select(qualifier);
		return !instance.isUnsatisfied();
	}

	public <T, Y> Predicate run(@NotNull final CriteriaBuilder criteriaBuilder,
			final @NotNull From<?, T> from, final @TenantValue Y tenantValue)
			throws TenantNotFound {
		final Class<?> klazz = (Class<?>) from.getJavaType();
		final TenantType qualifier = getType(klazz);
		final Instance<Tenantable<?>> instance = tenants.select(qualifier);
		if (!instance.isUnsatisfied()) {
			logger.debug("Adicionando Tenant para {}", klazz.getSimpleName());
			final Tenantable<T> tenant = cast(instance.get());
			return tenant.add(criteriaBuilder, from, tenantValue);
		} else {
			logger.debug("Tenant para {} não encontrado", klazz.getSimpleName());
			throw new TenantNotFound();
		}
	}

	@SuppressWarnings("unchecked")
	private <T> Tenantable<T> cast(@NotNull final Tenantable<?> tenant) {
		return (Tenantable<T>) tenant;
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
