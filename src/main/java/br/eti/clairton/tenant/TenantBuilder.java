package br.eti.clairton.tenant;

import static org.apache.logging.log4j.LogManager.getLogger;

import java.lang.annotation.Annotation;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.Logger;

/**
 * Tenant Builder.
 * 
 * @author Clairton Rodrigo Heinzen<clairton.rodrigo@gmail.com>
 */
@Dependent
public class TenantBuilder {
	private static final Logger logger = getLogger(TenantBuilder.class);

	private final Instance<Tenantable<?>> tenants;

	/**
	 * Constructor for CDI only.
	 */
	@Deprecated
	public TenantBuilder() {
		this(null);
	}

	/**
	 * Default constructor.
	 * 
	 * @param tenants
	 *            instance of tenants
	 */
	@Inject
	public TenantBuilder(@Any final Instance<Tenantable<?>> tenants) {
		super();
		this.tenants = tenants;
	}

	/**
	 * Verify if exist a tenant for type.
	 * 
	 * @param klazz
	 *            type to verify
	 * @return true/false
	 */
	public Boolean exist(final Class<?> klazz) {
		final TenantType qualifier = getType(klazz);
		final Instance<Tenantable<?>> instance = tenants.select(qualifier);
		return !instance.isUnsatisfied();
	}

	/**
	 * Add the tenant for de {@link From}, id exists.
	 * 
	 * @param builder
	 *            instance of {@link CriteriaBuilder}
	 * @param from
	 *            instance of de current {@link From}
	 * @param tenantValue
	 *            value of the tenant
	 * @return {@link Predicate} of the tenant for the {@link From} param
	 */
	public <T, Y> Predicate run(@NotNull final CriteriaBuilder builder, @NotNull final From<?, T> from,
			final Object tenantValue) {
		final Class<?> klazz = (Class<?>) from.getJavaType();
		final TenantType qualifier = getType(klazz);
		final Instance<Tenantable<?>> instance = tenants.select(qualifier);
		if (instance.isUnsatisfied()) {
			logger.debug("Tenant para {} não encontrado", klazz.getSimpleName());
			throw new TenantNotFound();
		} else {
			logger.debug("Adicionando Tenant para {}", klazz.getSimpleName());
			@SuppressWarnings("unchecked")
			final Tenantable<T> t = (Tenantable<T>) instance.get();
			final Tenantable<T> tenant = t;
			return tenant.add(builder, from, tenantValue);
		}
	}

	private TenantType getType(final Class<?> klazz) {
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
