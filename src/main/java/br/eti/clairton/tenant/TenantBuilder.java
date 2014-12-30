package br.eti.clairton.tenant;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	private final Instance<Tenant<?>> tenants;

	private final Logger logger;

	@Inject
	public TenantBuilder(@Any final Instance<Tenant<?>> tenants,
			@Default final Logger logger) {
		super();
		this.tenants = tenants;
		this.logger = logger;
	}

	public Boolean exist(final Class<?> klazz) {
		final TenantType qualifier = getType(klazz);
		final Instance<Tenant<?>> instance = tenants.select(qualifier);
		return !instance.isUnsatisfied();
	}

	public <T> Predicate[] run(@NotNull final CriteriaBuilder criteriaBuilder,
			final @NotNull From<?, T> from,
			final @NotNull Predicate... appendTo) {
		final List<Predicate> in = new ArrayList<>();
		in.addAll(Arrays.asList(appendTo));
		final List<Predicate> predicates = run(criteriaBuilder, from, in);
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	public <T> List<Predicate> run(
			@NotNull final CriteriaBuilder criteriaBuilder,
			final @NotNull From<?, T> from,
			final @NotNull List<Predicate> appendTo) {
		final Class<?> klazz = (Class<?>) from.getJavaType();
		final TenantType qualifier = getType(klazz);
		final Instance<Tenant<?>> instance = tenants.select(qualifier);
		if (!instance.isUnsatisfied()) {
			logger.debug("Adicionando Tenant para {}", klazz.getSimpleName());
			final Tenant<T> tenant = cast(instance.get());
			return tenant.add(criteriaBuilder, from, appendTo);
		} else {
			logger.debug("Tenant para {} não encontrado", klazz.getSimpleName());
			return appendTo;
		}
	}

	public <T> Predicate[] run(@NotNull final CriteriaBuilder criteriaBuilder,
			final @NotNull From<?, T> from) {
		final List<Predicate> predicates = run(criteriaBuilder, from,
				new ArrayList<>());
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	@SuppressWarnings("unchecked")
	private <T> Tenant<T> cast(@NotNull final Tenant<?> tenant) {
		return (Tenant<T>) tenant;
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
