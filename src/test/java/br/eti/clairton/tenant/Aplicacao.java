package br.eti.clairton.tenant;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Representa uma Aplicação.
 * 
 * @author Clairton Rodrigo Heinzen<clairton.rodrigo@gmail.com>
 */
@Entity
@Table(name = "aplicacoes")
public class Aplicacao {

	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}

	@NotNull
	@OneToMany(mappedBy = "aplicacao")
	private Collection<Recurso> recursos = new HashSet<>();

	@NotNull
	@Size(min = 1, max = 250)
	private String nome;

	/*
	 * Atributo que server somente para testar os metodos equals, hashCode e
	 * toString
	 */
	private final @Transient Long transientField = new Date().getTime();

	/**
	 * Construtor padrão.
	 */
	@Deprecated
	protected Aplicacao() {
	}

	/**
	 * Construtor com argumentos.
	 * 
	 * @param nome
	 *            nome da aplicação
	 * @param recursos
	 *            recursos da aplicação
	 */
	public Aplicacao(final String nome, final Collection<Recurso> recursos) {
		super();
		this.nome = nome;
		adicionar(recursos);
	}

	/**
	 * Construtor com argumentos.
	 * 
	 * @param nome
	 *            nome da aplicação
	 * @param recurso
	 *            recurso da aplicação
	 */
	public Aplicacao(final String nome, final Recurso recurso) {
		this(nome, Arrays.asList(recurso));
	}

	/**
	 * Construtor com parametros.
	 * 
	 * @param nome
	 *            da aplicação
	 */
	public Aplicacao(final String nome) {
		this(nome, Collections.<Recurso> emptyList());
	}

	public void adicionar(Recurso recurso) {
		recursos.add(recurso);
	}

	public void adicionar(Collection<Recurso> recursos) {
		this.recursos.addAll(recursos);
	}

	public void remover(Recurso recurso) {
		recursos.remove(recurso);
	}

	public Collection<Recurso> getRecursos() {
		return Collections.unmodifiableCollection(recursos);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
