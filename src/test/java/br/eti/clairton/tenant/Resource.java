package br.eti.clairton.tenant;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class Resource {

	@Produces
	@ApplicationScoped
	public EntityManagerFactory createEntityManagerFactory() {
		return new EntityManagerFactoryTenant(
				Persistence.createEntityManagerFactory("default"));
	}

	@Produces
	@ApplicationScoped
	public EntityManager createEntityManager(@Default EntityManagerFactory emf) {
		return new EntityManagerTenant(emf.createEntityManager());
	}

}
