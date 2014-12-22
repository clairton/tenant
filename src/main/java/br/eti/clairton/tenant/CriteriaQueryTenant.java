package br.eti.clairton.tenant;

import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.criteria.Subquery;
import javax.persistence.metamodel.EntityType;

public class CriteriaQueryTenant<T> implements CriteriaQuery<T> {
	private final CriteriaQuery<T> criteriaQuery;

	public CriteriaQueryTenant(final CriteriaQuery<T> criteriaQuery) {
		super();
		this.criteriaQuery = criteriaQuery;
	}

	public CriteriaQuery<T> select(Selection<? extends T> selection) {
		return criteriaQuery.select(selection);
	}

	public <X> Root<X> from(Class<X> entityClass) {
		return criteriaQuery.from(entityClass);
	}

	public <X> Root<X> from(EntityType<X> entity) {
		return criteriaQuery.from(entity);
	}

	public CriteriaQuery<T> multiselect(Selection<?>... selections) {
		return criteriaQuery.multiselect(selections);
	}

	public <U> Subquery<U> subquery(Class<U> type) {
		return criteriaQuery.subquery(type);
	}

	public Set<Root<?>> getRoots() {
		return criteriaQuery.getRoots();
	}

	public CriteriaQuery<T> multiselect(List<Selection<?>> selectionList) {
		return criteriaQuery.multiselect(selectionList);
	}

	public Selection<T> getSelection() {
		return criteriaQuery.getSelection();
	}

	public Predicate getRestriction() {
		return criteriaQuery.getRestriction();
	}

	public List<Expression<?>> getGroupList() {
		return criteriaQuery.getGroupList();
	}

	public Predicate getGroupRestriction() {
		return criteriaQuery.getGroupRestriction();
	}

	public boolean isDistinct() {
		return criteriaQuery.isDistinct();
	}

	public Class<T> getResultType() {
		return criteriaQuery.getResultType();
	}

	public CriteriaQuery<T> where(Expression<Boolean> restriction) {
		return criteriaQuery.where(restriction);
	}

	public CriteriaQuery<T> where(Predicate... restrictions) {
		return criteriaQuery.where(restrictions);
	}

	public CriteriaQuery<T> groupBy(Expression<?>... grouping) {
		return criteriaQuery.groupBy(grouping);
	}

	public CriteriaQuery<T> groupBy(List<Expression<?>> grouping) {
		return criteriaQuery.groupBy(grouping);
	}

	public CriteriaQuery<T> having(Expression<Boolean> restriction) {
		return criteriaQuery.having(restriction);
	}

	public CriteriaQuery<T> having(Predicate... restrictions) {
		return criteriaQuery.having(restrictions);
	}

	public CriteriaQuery<T> orderBy(Order... o) {
		return criteriaQuery.orderBy(o);
	}

	public CriteriaQuery<T> orderBy(List<Order> o) {
		return criteriaQuery.orderBy(o);
	}

	public CriteriaQuery<T> distinct(boolean distinct) {
		return criteriaQuery.distinct(distinct);
	}

	public List<Order> getOrderList() {
		return criteriaQuery.getOrderList();
	}

	public Set<ParameterExpression<?>> getParameters() {
		return criteriaQuery.getParameters();
	}
}
