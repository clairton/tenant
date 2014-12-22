package br.eti.clairton.tenant;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.persistence.Tuple;
import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Selection;
import javax.persistence.criteria.Subquery;

public class CriteriaBuilderTenant implements CriteriaBuilder {
	private final CriteriaBuilder criteriaBuilder;

	public CriteriaBuilderTenant(final CriteriaBuilder cb) {
		super();
		this.criteriaBuilder = cb;
	}

	public CriteriaQuery<Object> createQuery() {
		return new CriteriaQueryTenant<Object>(criteriaBuilder.createQuery());
	}

	public <T> CriteriaQuery<T> createQuery(final Class<T> resultClass) {
		return new CriteriaQueryTenant<T>(
				criteriaBuilder.createQuery(resultClass));
	}

	public CriteriaQuery<Tuple> createTupleQuery() {
		return new CriteriaQueryTenant<Tuple>(
				criteriaBuilder.createTupleQuery());
	}

	public <Y> CompoundSelection<Y> construct(final Class<Y> resultClass,
			Selection<?>... selections) {
		return criteriaBuilder.construct(resultClass, selections);
	}

	public CompoundSelection<Tuple> tuple(Selection<?>... selections) {
		return criteriaBuilder.tuple(selections);
	}

	public CompoundSelection<Object[]> array(Selection<?>... selections) {
		return criteriaBuilder.array(selections);
	}

	public Order asc(Expression<?> x) {
		return criteriaBuilder.asc(x);
	}

	public Order desc(Expression<?> x) {
		return criteriaBuilder.desc(x);
	}

	public <N extends Number> Expression<Double> avg(Expression<N> x) {
		return criteriaBuilder.avg(x);
	}

	public <N extends Number> Expression<N> sum(Expression<N> x) {
		return criteriaBuilder.sum(x);
	}

	public Expression<Long> sumAsLong(Expression<Integer> x) {
		return criteriaBuilder.sumAsLong(x);
	}

	public Expression<Double> sumAsDouble(Expression<Float> x) {
		return criteriaBuilder.sumAsDouble(x);
	}

	public <N extends Number> Expression<N> max(Expression<N> x) {
		return criteriaBuilder.max(x);
	}

	public <N extends Number> Expression<N> min(Expression<N> x) {
		return criteriaBuilder.min(x);
	}

	public <X extends Comparable<? super X>> Expression<X> greatest(
			Expression<X> x) {
		return criteriaBuilder.greatest(x);
	}

	public <X extends Comparable<? super X>> Expression<X> least(Expression<X> x) {
		return criteriaBuilder.least(x);
	}

	public Expression<Long> count(Expression<?> x) {
		return criteriaBuilder.count(x);
	}

	public Expression<Long> countDistinct(Expression<?> x) {
		return criteriaBuilder.countDistinct(x);
	}

	public Predicate exists(Subquery<?> subquery) {
		return criteriaBuilder.exists(subquery);
	}

	public <Y> Expression<Y> all(Subquery<Y> subquery) {
		return criteriaBuilder.all(subquery);
	}

	public <Y> Expression<Y> some(Subquery<Y> subquery) {
		return criteriaBuilder.some(subquery);
	}

	public <Y> Expression<Y> any(Subquery<Y> subquery) {
		return criteriaBuilder.any(subquery);
	}

	public Predicate and(Expression<Boolean> x, Expression<Boolean> y) {
		return criteriaBuilder.and(x, y);
	}

	public Predicate and(Predicate... restrictions) {
		return criteriaBuilder.and(restrictions);
	}

	public Predicate or(Expression<Boolean> x, Expression<Boolean> y) {
		return criteriaBuilder.or(x, y);
	}

	public Predicate or(Predicate... restrictions) {
		return criteriaBuilder.or(restrictions);
	}

	public Predicate not(Expression<Boolean> restriction) {
		return criteriaBuilder.not(restriction);
	}

	public Predicate conjunction() {
		return criteriaBuilder.conjunction();
	}

	public Predicate disjunction() {
		return criteriaBuilder.disjunction();
	}

	public Predicate isTrue(Expression<Boolean> x) {
		return criteriaBuilder.isTrue(x);
	}

	public Predicate isFalse(Expression<Boolean> x) {
		return criteriaBuilder.isFalse(x);
	}

	public Predicate isNull(Expression<?> x) {
		return criteriaBuilder.isNull(x);
	}

	public Predicate isNotNull(Expression<?> x) {
		return criteriaBuilder.isNotNull(x);
	}

	public Predicate equal(Expression<?> x, Expression<?> y) {
		return criteriaBuilder.equal(x, y);
	}

	public Predicate equal(Expression<?> x, Object y) {
		return criteriaBuilder.equal(x, y);
	}

	public Predicate notEqual(Expression<?> x, Expression<?> y) {
		return criteriaBuilder.notEqual(x, y);
	}

	public Predicate notEqual(Expression<?> x, Object y) {
		return criteriaBuilder.notEqual(x, y);
	}

	public <Y extends Comparable<? super Y>> Predicate greaterThan(
			Expression<? extends Y> x, Expression<? extends Y> y) {
		return criteriaBuilder.greaterThan(x, y);
	}

	public <Y extends Comparable<? super Y>> Predicate greaterThan(
			Expression<? extends Y> x, Y y) {
		return criteriaBuilder.greaterThan(x, y);
	}

	public <Y extends Comparable<? super Y>> Predicate greaterThanOrEqualTo(
			Expression<? extends Y> x, Expression<? extends Y> y) {
		return criteriaBuilder.greaterThanOrEqualTo(x, y);
	}

	public <Y extends Comparable<? super Y>> Predicate greaterThanOrEqualTo(
			Expression<? extends Y> x, Y y) {
		return criteriaBuilder.greaterThanOrEqualTo(x, y);
	}

	public <Y extends Comparable<? super Y>> Predicate lessThan(
			Expression<? extends Y> x, Expression<? extends Y> y) {
		return criteriaBuilder.lessThan(x, y);
	}

	public <Y extends Comparable<? super Y>> Predicate lessThan(
			Expression<? extends Y> x, Y y) {
		return criteriaBuilder.lessThan(x, y);
	}

	public <Y extends Comparable<? super Y>> Predicate lessThanOrEqualTo(
			Expression<? extends Y> x, Expression<? extends Y> y) {
		return criteriaBuilder.lessThanOrEqualTo(x, y);
	}

	public <Y extends Comparable<? super Y>> Predicate lessThanOrEqualTo(
			Expression<? extends Y> x, Y y) {
		return criteriaBuilder.lessThanOrEqualTo(x, y);
	}

	public <Y extends Comparable<? super Y>> Predicate between(
			Expression<? extends Y> v, Expression<? extends Y> x,
			Expression<? extends Y> y) {
		return criteriaBuilder.between(v, x, y);
	}

	public <Y extends Comparable<? super Y>> Predicate between(
			Expression<? extends Y> v, Y x, Y y) {
		return criteriaBuilder.between(v, x, y);
	}

	public Predicate gt(Expression<? extends Number> x,
			Expression<? extends Number> y) {
		return criteriaBuilder.gt(x, y);
	}

	public Predicate gt(Expression<? extends Number> x, Number y) {
		return criteriaBuilder.gt(x, y);
	}

	public Predicate ge(Expression<? extends Number> x,
			Expression<? extends Number> y) {
		return criteriaBuilder.ge(x, y);
	}

	public Predicate ge(Expression<? extends Number> x, Number y) {
		return criteriaBuilder.ge(x, y);
	}

	public Predicate lt(Expression<? extends Number> x,
			Expression<? extends Number> y) {
		return criteriaBuilder.lt(x, y);
	}

	public Predicate lt(Expression<? extends Number> x, Number y) {
		return criteriaBuilder.lt(x, y);
	}

	public Predicate le(Expression<? extends Number> x,
			Expression<? extends Number> y) {
		return criteriaBuilder.le(x, y);
	}

	public Predicate le(Expression<? extends Number> x, Number y) {
		return criteriaBuilder.le(x, y);
	}

	public <N extends Number> Expression<N> neg(Expression<N> x) {
		return criteriaBuilder.neg(x);
	}

	public <N extends Number> Expression<N> abs(Expression<N> x) {
		return criteriaBuilder.abs(x);
	}

	public <N extends Number> Expression<N> sum(Expression<? extends N> x,
			Expression<? extends N> y) {
		return criteriaBuilder.sum(x, y);
	}

	public <N extends Number> Expression<N> sum(Expression<? extends N> x, N y) {
		return criteriaBuilder.sum(x, y);
	}

	public <N extends Number> Expression<N> sum(N x, Expression<? extends N> y) {
		return criteriaBuilder.sum(x, y);
	}

	public <N extends Number> Expression<N> prod(Expression<? extends N> x,
			Expression<? extends N> y) {
		return criteriaBuilder.prod(x, y);
	}

	public <N extends Number> Expression<N> prod(Expression<? extends N> x, N y) {
		return criteriaBuilder.prod(x, y);
	}

	public <N extends Number> Expression<N> prod(N x, Expression<? extends N> y) {
		return criteriaBuilder.prod(x, y);
	}

	public <N extends Number> Expression<N> diff(Expression<? extends N> x,
			Expression<? extends N> y) {
		return criteriaBuilder.diff(x, y);
	}

	public <N extends Number> Expression<N> diff(Expression<? extends N> x, N y) {
		return criteriaBuilder.diff(x, y);
	}

	public <N extends Number> Expression<N> diff(N x, Expression<? extends N> y) {
		return criteriaBuilder.diff(x, y);
	}

	public Expression<Number> quot(Expression<? extends Number> x,
			Expression<? extends Number> y) {
		return criteriaBuilder.quot(x, y);
	}

	public Expression<Number> quot(Expression<? extends Number> x, Number y) {
		return criteriaBuilder.quot(x, y);
	}

	public Expression<Number> quot(Number x, Expression<? extends Number> y) {
		return criteriaBuilder.quot(x, y);
	}

	public Expression<Integer> mod(Expression<Integer> x, Expression<Integer> y) {
		return criteriaBuilder.mod(x, y);
	}

	public Expression<Integer> mod(Expression<Integer> x, Integer y) {
		return criteriaBuilder.mod(x, y);
	}

	public Expression<Integer> mod(Integer x, Expression<Integer> y) {
		return criteriaBuilder.mod(x, y);
	}

	public Expression<Double> sqrt(Expression<? extends Number> x) {
		return criteriaBuilder.sqrt(x);
	}

	public Expression<Long> toLong(Expression<? extends Number> number) {
		return criteriaBuilder.toLong(number);
	}

	public Expression<Integer> toInteger(Expression<? extends Number> number) {
		return criteriaBuilder.toInteger(number);
	}

	public Expression<Float> toFloat(Expression<? extends Number> number) {
		return criteriaBuilder.toFloat(number);
	}

	public Expression<Double> toDouble(Expression<? extends Number> number) {
		return criteriaBuilder.toDouble(number);
	}

	public Expression<BigDecimal> toBigDecimal(
			Expression<? extends Number> number) {
		return criteriaBuilder.toBigDecimal(number);
	}

	public Expression<BigInteger> toBigInteger(
			Expression<? extends Number> number) {
		return criteriaBuilder.toBigInteger(number);
	}

	public Expression<String> toString(Expression<Character> character) {
		return criteriaBuilder.toString(character);
	}

	public <T> Expression<T> literal(T value) {
		return criteriaBuilder.literal(value);
	}

	public <T> Expression<T> nullLiteral(Class<T> resultClass) {
		return criteriaBuilder.nullLiteral(resultClass);
	}

	public <T> ParameterExpression<T> parameter(Class<T> paramClass) {
		return criteriaBuilder.parameter(paramClass);
	}

	public <T> ParameterExpression<T> parameter(Class<T> paramClass, String name) {
		return criteriaBuilder.parameter(paramClass, name);
	}

	public <C extends Collection<?>> Predicate isEmpty(Expression<C> collection) {
		return criteriaBuilder.isEmpty(collection);
	}

	public <C extends Collection<?>> Predicate isNotEmpty(
			Expression<C> collection) {
		return criteriaBuilder.isNotEmpty(collection);
	}

	public <C extends Collection<?>> Expression<Integer> size(
			Expression<C> collection) {
		return criteriaBuilder.size(collection);
	}

	public <C extends Collection<?>> Expression<Integer> size(C collection) {
		return criteriaBuilder.size(collection);
	}

	public <E, C extends Collection<E>> Predicate isMember(Expression<E> elem,
			Expression<C> collection) {
		return criteriaBuilder.isMember(elem, collection);
	}

	public <E, C extends Collection<E>> Predicate isMember(E elem,
			Expression<C> collection) {
		return criteriaBuilder.isMember(elem, collection);
	}

	public <E, C extends Collection<E>> Predicate isNotMember(
			Expression<E> elem, Expression<C> collection) {
		return criteriaBuilder.isNotMember(elem, collection);
	}

	public <E, C extends Collection<E>> Predicate isNotMember(E elem,
			Expression<C> collection) {
		return criteriaBuilder.isNotMember(elem, collection);
	}

	public <V, M extends Map<?, V>> Expression<Collection<V>> values(M map) {
		return criteriaBuilder.values(map);
	}

	public <K, M extends Map<K, ?>> Expression<Set<K>> keys(M map) {
		return criteriaBuilder.keys(map);
	}

	public Predicate like(Expression<String> x, Expression<String> pattern) {
		return criteriaBuilder.like(x, pattern);
	}

	public Predicate like(Expression<String> x, String pattern) {
		return criteriaBuilder.like(x, pattern);
	}

	public Predicate like(Expression<String> x, Expression<String> pattern,
			Expression<Character> escapeChar) {
		return criteriaBuilder.like(x, pattern, escapeChar);
	}

	public Predicate like(Expression<String> x, Expression<String> pattern,
			char escapeChar) {
		return criteriaBuilder.like(x, pattern, escapeChar);
	}

	public Predicate like(Expression<String> x, String pattern,
			Expression<Character> escapeChar) {
		return criteriaBuilder.like(x, pattern, escapeChar);
	}

	public Predicate like(Expression<String> x, String pattern, char escapeChar) {
		return criteriaBuilder.like(x, pattern, escapeChar);
	}

	public Predicate notLike(Expression<String> x, Expression<String> pattern) {
		return criteriaBuilder.notLike(x, pattern);
	}

	public Predicate notLike(Expression<String> x, String pattern) {
		return criteriaBuilder.notLike(x, pattern);
	}

	public Predicate notLike(Expression<String> x, Expression<String> pattern,
			Expression<Character> escapeChar) {
		return criteriaBuilder.notLike(x, pattern, escapeChar);
	}

	public Predicate notLike(Expression<String> x, Expression<String> pattern,
			char escapeChar) {
		return criteriaBuilder.notLike(x, pattern, escapeChar);
	}

	public Predicate notLike(Expression<String> x, String pattern,
			Expression<Character> escapeChar) {
		return criteriaBuilder.notLike(x, pattern, escapeChar);
	}

	public Predicate notLike(Expression<String> x, String pattern,
			char escapeChar) {
		return criteriaBuilder.notLike(x, pattern, escapeChar);
	}

	public Expression<String> concat(Expression<String> x, Expression<String> y) {
		return criteriaBuilder.concat(x, y);
	}

	public Expression<String> concat(Expression<String> x, String y) {
		return criteriaBuilder.concat(x, y);
	}

	public Expression<String> concat(String x, Expression<String> y) {
		return criteriaBuilder.concat(x, y);
	}

	public Expression<String> substring(Expression<String> x,
			Expression<Integer> from) {
		return criteriaBuilder.substring(x, from);
	}

	public Expression<String> substring(Expression<String> x, int from) {
		return criteriaBuilder.substring(x, from);
	}

	public Expression<String> substring(Expression<String> x,
			Expression<Integer> from, Expression<Integer> len) {
		return criteriaBuilder.substring(x, from, len);
	}

	public Expression<String> substring(Expression<String> x, int from, int len) {
		return criteriaBuilder.substring(x, from, len);
	}

	public Expression<String> trim(Expression<String> x) {
		return criteriaBuilder.trim(x);
	}

	public Expression<String> trim(Trimspec ts, Expression<String> x) {
		return criteriaBuilder.trim(ts, x);
	}

	public Expression<String> trim(Expression<Character> t, Expression<String> x) {
		return criteriaBuilder.trim(t, x);
	}

	public Expression<String> trim(Trimspec ts, Expression<Character> t,
			Expression<String> x) {
		return criteriaBuilder.trim(ts, t, x);
	}

	public Expression<String> trim(char t, Expression<String> x) {
		return criteriaBuilder.trim(t, x);
	}

	public Expression<String> trim(Trimspec ts, char t, Expression<String> x) {
		return criteriaBuilder.trim(ts, t, x);
	}

	public Expression<String> lower(Expression<String> x) {
		return criteriaBuilder.lower(x);
	}

	public Expression<String> upper(Expression<String> x) {
		return criteriaBuilder.upper(x);
	}

	public Expression<Integer> length(Expression<String> x) {
		return criteriaBuilder.length(x);
	}

	public Expression<Integer> locate(Expression<String> x,
			Expression<String> pattern) {
		return criteriaBuilder.locate(x, pattern);
	}

	public Expression<Integer> locate(Expression<String> x, String pattern) {
		return criteriaBuilder.locate(x, pattern);
	}

	public Expression<Integer> locate(Expression<String> x,
			Expression<String> pattern, Expression<Integer> from) {
		return criteriaBuilder.locate(x, pattern, from);
	}

	public Expression<Integer> locate(Expression<String> x, String pattern,
			int from) {
		return criteriaBuilder.locate(x, pattern, from);
	}

	public Expression<Date> currentDate() {
		return criteriaBuilder.currentDate();
	}

	public Expression<Timestamp> currentTimestamp() {
		return criteriaBuilder.currentTimestamp();
	}

	public Expression<Time> currentTime() {
		return criteriaBuilder.currentTime();
	}

	public <T> In<T> in(Expression<? extends T> expression) {
		return criteriaBuilder.in(expression);
	}

	public <Y> Expression<Y> coalesce(Expression<? extends Y> x,
			Expression<? extends Y> y) {
		return criteriaBuilder.coalesce(x, y);
	}

	public <Y> Expression<Y> coalesce(Expression<? extends Y> x, Y y) {
		return criteriaBuilder.coalesce(x, y);
	}

	public <Y> Expression<Y> nullif(Expression<Y> x, Expression<?> y) {
		return criteriaBuilder.nullif(x, y);
	}

	public <Y> Expression<Y> nullif(Expression<Y> x, Y y) {
		return criteriaBuilder.nullif(x, y);
	}

	public <T> Coalesce<T> coalesce() {
		return criteriaBuilder.coalesce();
	}

	public <C, R> SimpleCase<C, R> selectCase(Expression<? extends C> expression) {
		return criteriaBuilder.selectCase(expression);
	}

	public <R> Case<R> selectCase() {
		return criteriaBuilder.selectCase();
	}

	public <T> Expression<T> function(String name, Class<T> type,
			Expression<?>... args) {
		return criteriaBuilder.function(name, type, args);
	}
}
