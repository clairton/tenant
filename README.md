# Possibilita adicionar Tenant as consultas JPA através do criteria builder, fazendo uso do CDI.

	Supondo que Temos dois modelos, Recurso e Aplicação, onde uma aplicação possui vários recursos, mas o usuário logado só pode ver os recursos da aplicação em que ele está relacionado. Montamos então um tenant para aplicação. Ele deve ser qualificado como @TenantType(Aplicacao.class) e implementar o método do contrato Tenant chamado "add"
    
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
@TenantType(Aplicacao.class)//Qualificando ele com um Tenant para o Tipo //Aplicacação
public class AplicacaoTenant extends Tenant<Aplicacao> {

	@Inject
	public AplicacaoTenant(final TenantBuilder builder) {
		super(builder);
	}

	@Override
	public List<Predicate> add(@NotNull final CriteriaBuilder criteriaBuilder,
			final @NotNull From<?, Aplicacao> from,
			final @NotNull List<Predicate> appendTo) {
		final Path<String> path = from.get(Aplicacao_.nome);
		final String value = "OutroTesteQueNãoDeveAparecerNaConsulta";
		final Predicate predicate = criteriaBuilder.notEqual(path, value);
		appendTo.add(predicate);
		return appendTo;
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
