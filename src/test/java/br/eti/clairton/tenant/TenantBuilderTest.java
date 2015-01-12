package br.eti.clairton.tenant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiJUnit4Runner.class)
public class TenantBuilderTest {
	private @Inject EntityManager entityManager;
	private @Inject Connection connection;
	private @Inject TenantBuilder tenant;
	private String nome = "OutroTesteQueNãoDeveAparecerNaConsulta";

	@Before
	public void init() throws Exception {
		entityManager.getTransaction().begin();
		final String sql = "DELETE FROM operacoes;DELETE FROM recursos;DELETE FROM aplicacoes;";
		connection.createStatement().execute(sql);
		entityManager.getTransaction().commit();

		entityManager.getTransaction().begin();
		final Aplicacao aplicacao = new Aplicacao("Teste");
		final Recurso recurso = new Recurso(aplicacao, "Teste");
		final Operacao operacao = new Operacao(recurso, "Teste");
		entityManager.persist(operacao);
		final Aplicacao aplicacao2 = new Aplicacao(nome);
		final Recurso recurso2 = new Recurso(aplicacao2, "OutroTeste");
		final Operacao operacao2 = new Operacao(recurso2, "OutroTeste");
		entityManager.persist(operacao2);
		entityManager.getTransaction().commit();
	}

	@Test
	public void testStartWithNull() {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Recurso> query = builder.createQuery(Recurso.class);
		final Root<Recurso> from = query.from(Recurso.class);
		query.where(tenant.run(builder, from, nome));
		final TypedQuery<Recurso> typedQuery = entityManager.createQuery(query);
		final List<Recurso> result = typedQuery.getResultList();
		assertEquals(1, result.size());
	}

	@Test
	public void testWithTenant() {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Recurso> query = builder.createQuery(Recurso.class);
		final Root<Recurso> from = query.from(Recurso.class);
		try {
			//recurpera os predicados
			final Predicate predicate = tenant.run(builder, from, nome);
			//aplicaca os predicados
			query.where(predicate);
		} catch (final TenantNotFound e) {
			//caso não haja tenant irá lançar a exceção
		}
		final TypedQuery<Recurso> typedQuery = entityManager.createQuery(query);
		/*
		 * Retornara somente os Recursos que estão relacionados as Aplicações com nome
		 * diferente de "AplicaçãoQueNãoDeveAparecerNaConsulta"
		 */
		final List<Recurso> result = typedQuery.getResultList();
		assertEquals(1, result.size());
	}

	@Test
	public void testWithTenantInJoin() {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Operacao> query = builder
				.createQuery(Operacao.class);
		final Root<Operacao> from = query.from(Operacao.class);
		final Join<Operacao, Recurso> join = from.join(Operacao_.recurso);
		query.where(tenant.run(builder, join, nome));
		final TypedQuery<Operacao> typedQuery = entityManager
				.createQuery(query);
		final List<Operacao> result = typedQuery.getResultList();
		assertEquals(1, result.size());
	}

	@Test
	public void testWithoutTenant() {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Operacao> query = builder
				.createQuery(Operacao.class);
		final Root<Operacao> from = query.from(Operacao.class);
		try {
			query.where(tenant.run(builder, from, nome));
		} catch (final TenantNotFound e) {

		}
		final TypedQuery<Operacao> typedQuery = entityManager
				.createQuery(query);
		final List<Operacao> result = typedQuery.getResultList();
		assertEquals(2, result.size());
	}

	@Test
	public void testExistTenant() {
		assertTrue(tenant.exist(Recurso.class));
	}

	@Test
	public void testNotExistTenant() {
		assertFalse(tenant.exist(Operacao.class));
	}
}
