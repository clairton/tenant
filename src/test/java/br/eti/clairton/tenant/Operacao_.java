package br.eti.clairton.tenant;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Operacao.class)
public abstract class Operacao_ {
	public static volatile SingularAttribute<Operacao, Long> id;

	public static volatile SingularAttribute<Operacao, String> nome;
	public static volatile SingularAttribute<Operacao, Recurso> recurso;

}
