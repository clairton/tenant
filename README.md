# Possibilita adicionar Tenant as consultas JPA através do criteria builder, fazendo uso do CDI.

	Supondo que Temos dois modelos, Recurso e Aplicação, onde uma aplicação possui vários recursos, mas só iremos mostrar aquelas aplicações que não se chamam "AplicaçãoQueNãoDeveAparecerNaConsulta", em um exemplo real poderiamos mostrar para o usuário logado só os recursos da aplicação em que ele está relacionado. Mas enfim, montamos então um tenant para aplicação.
    
```java	
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;

@Dependent// para ser geneciado pelo CDI
//Qualificando ele com um Tenant para o Tipo Aplicacação
@TenantType(Aplicacao.class)
//implementando o contrato necessário para poder ser invocado pelo TenantBuilder
public class AplicacaoTenant extends Tenant<Aplicacao> {
	/*
     * Deve injetar o Tenant Builder e Chamar o Super, isso fará com que ele
     * possa se chamar recursivamente
     */
	@Inject
	public AplicacaoTenant(final TenantBuilder builder) {
		super(builder);
	}

	//Contrato que adicionara o Predicado que desejamos
	@Override
	public List<Predicate> add(@NotNull final CriteriaBuilder criteriaBuilder,
			final @NotNull From<?, Aplicacao> from,
			final @NotNull List<Predicate> appendTo) {
		final Path<String> path = from.get(Aplicacao_.nome);
		final String value = "AplicaçãoQueNãoDeveAparecerNaConsulta";
		final Predicate predicate = criteriaBuilder.notEqual(path, value);
        //adiciona o predicado aos já existentes
		appendTo.add(predicate);
		return appendTo;
	}
}

```
	O tenant de Recurso, somente irá chamar o tenant relacionado a Aplicação, fica dessa forma:
```java

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
public class RecursoTenant extends Tenant<Recurso> {
	/*
     * Deve injetar o Tenant Builder e Chamar o Super, isso fará com que ele
     * possa se chamar recursivamente
     */
	@Inject
	public RecursoTenant(final TenantBuilder builder) {
		super(builder);
	}

	//Contrato que adicionara o Predicado que desejamos
	@Override
	public List<Predicate> add(@NotNull final CriteriaBuilder criteriaBuilder,
			final @NotNull From<?, Recurso> from,
			final @NotNull List<Predicate> appendTo) {
        /*
         * não temos um predicado diretamente os atributos de Recurso, para sim
         * temos que pegar os Recurso relacionados a Aplicação que queremos, por          * isso faremos o join de Recurso com Aplicação
         */
		final Join<Recurso, Aplicacao> join = from.join(Recurso_.aplicacao);
        /*
         * e chamaremo o TenantBuilder que irá fazer a de AplicacaoTenant#add
         * atravé do CDI e retornar o predicado necessário
         */
		return builder.run(criteriaBuilder, join, appendTo);
	}
}
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
	<artifactId>repository</artifactId>
	<version>0.1.0</version>
</dependency>
```
