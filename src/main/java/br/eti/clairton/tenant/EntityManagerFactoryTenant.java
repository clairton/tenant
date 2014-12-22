package br.eti.clairton.tenant;

import java.util.Map;

import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.metamodel.Metamodel;

public class EntityManagerFactoryTenant implements EntityManagerFactory {
	private final EntityManagerFactory entityManagerFactory;

	public EntityManagerFactoryTenant(EntityManagerFactory entityManagerFactory) {
		super();
		this.entityManagerFactory = entityManagerFactory;
	}

	public EntityManager createEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

	public EntityManager createEntityManager(
			@SuppressWarnings("rawtypes") Map map) {
		return entityManagerFactory.createEntityManager(map);
	}

	public CriteriaBuilder getCriteriaBuilder() {
		return new CriteriaBuilderTenant(
				entityManagerFactory.getCriteriaBuilder());
	}

	public Metamodel getMetamodel() {
		return entityManagerFactory.getMetamodel();
	}

	public boolean isOpen() {
		return entityManagerFactory.isOpen();
	}

	public void close() {
		entityManagerFactory.close();
	}

	public Map<String, Object> getProperties() {
		return entityManagerFactory.getProperties();
	}

	public Cache getCache() {
		return entityManagerFactory.getCache();
	}

	public PersistenceUnitUtil getPersistenceUnitUtil() {
		return entityManagerFactory.getPersistenceUnitUtil();
	}
}
