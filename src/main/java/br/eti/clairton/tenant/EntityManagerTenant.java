package br.eti.clairton.tenant;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.Metamodel;

public class EntityManagerTenant implements EntityManager {
	private final EntityManager entityManager;

	public EntityManagerTenant(final EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	public void persist(Object entity) {
		entityManager.persist(entity);
	}

	public <T> T merge(T entity) {
		return entityManager.merge(entity);
	}

	public void remove(Object entity) {
		entityManager.remove(entity);
	}

	public <T> T find(Class<T> entityClass, Object primaryKey) {
		return entityManager.find(entityClass, primaryKey);
	}

	public <T> T find(Class<T> entityClass, Object primaryKey,
			Map<String, Object> properties) {
		return entityManager.find(entityClass, primaryKey, properties);
	}

	public <T> T find(Class<T> entityClass, Object primaryKey,
			LockModeType lockMode) {
		return entityManager.find(entityClass, primaryKey, lockMode);
	}

	public <T> T find(Class<T> entityClass, Object primaryKey,
			LockModeType lockMode, Map<String, Object> properties) {
		return entityManager
				.find(entityClass, primaryKey, lockMode, properties);
	}

	public <T> T getReference(Class<T> entityClass, Object primaryKey) {
		return entityManager.getReference(entityClass, primaryKey);
	}

	public void flush() {
		entityManager.flush();
	}

	public void setFlushMode(FlushModeType flushMode) {
		entityManager.setFlushMode(flushMode);
	}

	public FlushModeType getFlushMode() {
		return entityManager.getFlushMode();
	}

	public void lock(Object entity, LockModeType lockMode) {
		entityManager.lock(entity, lockMode);
	}

	public void lock(Object entity, LockModeType lockMode,
			Map<String, Object> properties) {
		entityManager.lock(entity, lockMode, properties);
	}

	public void refresh(Object entity) {
		entityManager.refresh(entity);
	}

	public void refresh(Object entity, Map<String, Object> properties) {
		entityManager.refresh(entity, properties);
	}

	public void refresh(Object entity, LockModeType lockMode) {
		entityManager.refresh(entity, lockMode);
	}

	public void refresh(Object entity, LockModeType lockMode,
			Map<String, Object> properties) {
		entityManager.refresh(entity, lockMode, properties);
	}

	public void clear() {
		entityManager.clear();
	}

	public void detach(Object entity) {
		entityManager.detach(entity);
	}

	public boolean contains(Object entity) {
		return entityManager.contains(entity);
	}

	public LockModeType getLockMode(Object entity) {
		return entityManager.getLockMode(entity);
	}

	public void setProperty(String propertyName, Object value) {
		entityManager.setProperty(propertyName, value);
	}

	public Map<String, Object> getProperties() {
		return entityManager.getProperties();
	}

	public Query createQuery(String qlString) {
		return entityManager.createQuery(qlString);
	}

	public <T> TypedQuery<T> createQuery(CriteriaQuery<T> criteriaQuery) {
		return entityManager.createQuery(criteriaQuery);
	}

	public <T> TypedQuery<T> createQuery(String qlString, Class<T> resultClass) {
		return entityManager.createQuery(qlString, resultClass);
	}

	public Query createNamedQuery(String name) {
		return entityManager.createNamedQuery(name);
	}

	public <T> TypedQuery<T> createNamedQuery(String name, Class<T> resultClass) {
		return entityManager.createNamedQuery(name, resultClass);
	}

	public Query createNativeQuery(String sqlString) {
		return entityManager.createNativeQuery(sqlString);
	}

	public Query createNativeQuery(String sqlString,
			@SuppressWarnings("rawtypes") Class resultClass) {
		return entityManager.createNativeQuery(sqlString, resultClass);
	}

	public Query createNativeQuery(String sqlString, String resultSetMapping) {
		return entityManager.createNativeQuery(sqlString, resultSetMapping);
	}

	public void joinTransaction() {
		entityManager.joinTransaction();
	}

	public <T> T unwrap(Class<T> cls) {
		return entityManager.unwrap(cls);
	}

	public Object getDelegate() {
		return entityManager.getDelegate();
	}

	public void close() {
		entityManager.close();
	}

	public boolean isOpen() {
		return entityManager.isOpen();
	}

	public EntityTransaction getTransaction() {
		return entityManager.getTransaction();
	}

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManager.getEntityManagerFactory();
	}

	public CriteriaBuilder getCriteriaBuilder() {
		return new CriteriaBuilderTenant(entityManager.getCriteriaBuilder());
	}

	public Metamodel getMetamodel() {
		return entityManager.getMetamodel();
	}
}
