package br.eti.clairton.tenant;

import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Aplicacao.class)
public abstract class Aplicacao_ {
	public static volatile SingularAttribute<Aplicacao, Long> id;

	public static volatile CollectionAttribute<Aplicacao, Recurso> recursos;
	public static volatile SingularAttribute<Aplicacao, String> nome;

}
