package br.eti.clairton.tenant;

import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Recurso.class)
public abstract class Recurso_ {

	public static volatile SingularAttribute<Recurso, Long> id;
	public static volatile SingularAttribute<Recurso, Aplicacao> aplicacao;
	public static volatile SingularAttribute<Recurso, String> nome;
	public static volatile CollectionAttribute<Recurso, Operacao> operacoes;

}
