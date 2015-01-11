# Possibilita adicionar Tenant as consultas JPA através do criteria builder, fazendo uso do CDI.

   Supondo que Temos dois modelos, Recurso e Aplicação, onde uma aplicação
possui vários recursos, mas só iremos mostrar aquelas aplicações que não se
chamam "AplicaçãoQueNãoDeveAparecerNaConsulta", em um exemplo real
poderiamos mostrar para o usuário logado só os recursos da aplicação em que
ele está relacionado. Mas enfim, montamos então um tenant para aplicação.
    
```java	
package br.eti.clairton.tenant;

import javax.enterprise.context.Dependent;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;

@Dependent// para ser geneciado pelo CDI
//Qualificando ele com um Tenant para o Tipo Aplicacação
@TenantType(Aplicacao.class)
public class AplicacaoTenant extends Tenantable<Aplicacao> {
	
	//Contrato que adicionara o Predicado que desejamos
	@Override
	public Predicate add(final @NotNull CriteriaBuilder criteriaBuilder,
			final @NotNull From<?, Aplicacao> from,
			final @NotNull @TenantValue Object tenantValue) {
		final Path<String> path = from.get(Aplicacao_.nome);
		final Predicate predicate = criteriaBuilder.notEqual(path, tenantValue);
		return predicate;
	}
}
```
	O tenant de Recurso, somente irá chamar o tenant relacionado a 
    Aplicação, fica dessa forma:
```java
package br.eti.clairton.tenant;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;

@Dependent//Para ser Gerenciado pelo CDI
//Qualificado como tenant para o tipo recurso
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

	//Contrato que adicionara o Predicado que desejamos
	@Override
	public Predicate add(@NotNull final CriteriaBuilder criteriaBuilder,
			final @NotNull From<?, Recurso> from,
			final @NotNull Object tenantValue) {
       /*
        * não temos um predicado diretamente os atributos de Recurso, para
        * sim temos que pegar os Recurso relacionados a Aplicação que
        * queremos, por isso faremos o join de Recurso com Aplicação
        */
		final Join<Recurso, Aplicacao> join = from.join(Recurso_.aplicacao);
		/*
	     * e chamaremos o TenantBuilder que irá fazer a de AplicacaoTenant#add
	     * atravé do CDI e retornar o predicado necessário
	     */
		return builder.run(criteriaBuilder, join, tenantValue);
	}
}
```
Finalmente podemos fazer a consulta:

```java
@Inject EntityManager entityManager;
@Inject TenantBuilder tenant;
final String nome = "OutroTesteQueNãoDeveAparecerNaConsulta";
// cortamos o código aqui, para não ficar muito extenso
final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
final CriteriaQuery<Recurso> query = builder.createQuery(Recurso.class);
final Root<Recurso> from = query.from(Recurso.class);
//recurpera os predicados
final Predicate predicate = tenant.run(builder, from, nome);
//aplicaca os predicados
query.where(predicates);
final TypedQuery<Recurso> typedQuery = entityManager.createQuery(query);
/*
 * Retornara somente os Recursos que estão relacionados as Aplicações com nome
 * diferente de "AplicaçãoQueNãoDeveAparecerNaConsulta"
 */
final List<Recurso> result = typedQuery.getResultList();
```
Para usar será necessário adicionar os repositórios maven:

```xml
<repository>
	<id>mvn-repo-releases</id>
	<url>https://raw.github.com/clairton/mvn-repo.git/releases</url>
</repository>
<repository>
	<id>mvn-repo-snapshot</id>
	<url>https://raw.github.com/clairton/mvn-repo.git/snapshots</url>
</repository>
```
 Também adicionar as depêndencias:
```xml
<dependency>
    <groupId>br.eti.clairton</groupId>
	<artifactId>tenant</artifactId>
	<version>0.1.0</version>
</dependency>
```
