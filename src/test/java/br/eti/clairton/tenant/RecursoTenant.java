package br.eti.clairton.tenant;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;

@Dependent // Para ser Gerenciado pelo CDI
// Qualificado como tenant para o tipo recurso
@TenantType(Recurso.class)
public class RecursoTenant extends Tenantable<Recurso> {
	private final TenantBuilder builder;

	/*
	 * Deve injetar o Tenant Builder e Chamar o Super, isso fará com que ele
	 * possa se chamar recursivamente
	 */
	@Inject
	public RecursoTenant(final TenantBuilder builder) {
		this.builder = builder;
	}

	// Contrato que adicionara o Predicado que desejamos
	@Override
	public Predicate add(@NotNull final CriteriaBuilder criteriaBuilder, final @NotNull From<?, Recurso> from,
			final @NotNull Object tenantValue) {
		/*
		 * não temos um predicado diretamente os atributos de Recurso, para sim
		 * temos que pegar os Recurso relacionados a Aplicação que queremos, por
		 * isso faremos o join de Recurso com Aplicação
		 */
		final Join<Recurso, Aplicacao> join = from.join(Recurso_.aplicacao);
		/*
		 * e chamaremos o TenantBuilder que irá fazer a de AplicacaoTenant#add
		 * atravé do CDI e retornar o predicado necessário
		 */
		return builder.run(criteriaBuilder, join, tenantValue);
	}
}
