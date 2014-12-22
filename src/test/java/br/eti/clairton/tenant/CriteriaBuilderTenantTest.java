package br.eti.clairton.tenant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiJUnit4Runner.class)
public class CriteriaBuilderTenantTest {
	private @Inject EntityManager em;

	@Before
	public void setUp() throws Exception {
		em.getTransaction().begin();
		final Connection connection = em.unwrap(Connection.class);
		final String sql = "DELETE FROM operacoes;DELETE FROM recursos;DELETE FROM aplicacoes;";
		connection.createStatement().execute(sql);

		final Aplicacao aplicacao = new Aplicacao("Teste");
		final Recurso recurso = new Recurso(aplicacao, "Teste");
		final Operacao operacao = new Operacao(recurso, "Teste");
		em.persist(operacao);
		em.getTransaction().commit();
	}

	@Test
	public void test() {
		assertEquals(1, em.createQuery("Select a From Aplicacao a")
				.getResultList().size());
		assertNotNull(em);
	}
}
