package br.eti.clairton.tenant;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Representa uma ação.
 * 
 * @author Clairton Rodrigo Heinzen<clairton.rodrigo@gmail.com>
 */
@Entity
@Table(name = "operacoes")
public class Operacao {
	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}

	@NotNull
	@Size(min = 1, max = 50)
	private String nome;

	@ManyToOne(cascade = CascadeType.ALL)
	@NotNull
	private Recurso recurso;

	/**
	 * Construtor padrão.
	 */
	@Deprecated
	public Operacao() {
		this(null, null);
	}

	/**
	 * Construtor com parametros.
	 * 
	 * @param recurso
	 *            recurso ao qual a operacao pertence
	 * @param nome
	 *            nome da ação
	 */
	public Operacao(final Recurso recurso, final String nome) {
		super();
		this.nome = nome;
		this.recurso = recurso;
		if (recurso != null) {
			recurso.adicionar(this);
		}
	}

	public String getNome() {
		return nome;
	}

	public Recurso getRecurso() {
		return recurso;
	}
}
