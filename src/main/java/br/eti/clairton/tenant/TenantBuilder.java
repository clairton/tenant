package br.eti.clairton.tenant;

import static java.util.logging.Level.FINE;

import java.lang.annotation.Annotation;
import java.util.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;


/**
 * Tenant Builder.
 * 
 * @author Clairton Rodrigo Heinzen clairton.rodrigo@gmail.com
 */
@Dependent
public class TenantBuilder {
	private static final Logger logger = Logger.getLogger(TenantBuilder.class.getSimpleName());

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
     * @param <T>
     *.           type of entity
	 * @return {@link Predicate} of the tenant for the {@link From} param
	 */
	public <T> Predicate run(@NotNull final CriteriaBuilder builder, @NotNull final From<?, T> from, final Object tenantValue) {
		final Class<?> klazz = (Class<?>) from.getJavaType();
		final TenantType qualifier = getType(klazz);
		final Instance<Tenantable<?>> instance = tenants.select(qualifier);
		if (instance.isUnsatisfied()) {
			logger.log(FINE, "Tenant para {} não encontrado", klazz.getSimpleName());
			throw new TenantNotFound();
		} else {
			logger.log(FINE, "Adicionando Tenant para {}", klazz.getSimpleName());
			final Tenantable<T> tenant = get(instance);
			return tenant.add(builder, from, tenantValue);
		}
	}
	
	protected <T> Tenantable<T> get(final Instance<Tenantable<?>> instance){
		@SuppressWarnings("unchecked")
		final Tenantable<T> t = (Tenantable<T>) instance.get();
		final Tenantable<T> tenant = t;
		return tenant;
	}

	protected TenantType getType(final Class<?> klazz) {
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
