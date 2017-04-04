package de.lsoft.partition.querydsl;

import com.querydsl.core.QueryModifiers;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.impl.JPAQuery;
import de.lsoft.partition.Partitioning;
import de.lsoft.partition.Range;

import java.util.List;

/**
 * JPA-Query Partitioning: Partitioning via Limit and Offset in JPAQuery
 */
public class JPAQueryPartitioning<T> extends Partitioning<JPAQuery<T>> {

    protected JPAQueryPartitioning(JPAQuery<T> query) {
        super(query);
    }

    @Override
    public JPAQuery<T> invoke(JPAQuery<T> query, Range range) {
        if (range != null) {
            Integer offset = range.lowerEndpoint() - 1;
            Integer objectCount = range.upperEndpoint() - offset;
            QueryModifiers queryModifiers = new QueryModifiers(objectCount.longValue(), offset.longValue());
            return query.restrict(queryModifiers);
        } else {
            return null;
        }
    }

    @Override
    protected int getObjectCount(JPAQuery<T> query) {
        if (query.getMetadata().isDistinct()) {
            throw new IllegalStateException("distinct not allowed, use groupBy instead!");
        }

        List<Expression<?>> groupByExpressions = query.getMetadata().getGroupBy();
        return !groupByExpressions.isEmpty() ? query.select(groupByExpressions.toArray(new Expression[groupByExpressions.size()])).fetch().size() : (int) query.fetchCount();
    }

    /**
     * Creates a new JPAQueryPartitioning.
     */
    public static <T> JPAQueryPartitioning<T> forQuery(JPAQuery<T> query) {
        return new JPAQueryPartitioning<T>(query);
    }

    public static <T> JPAQueryPartitioning<T> getStringJPAQueryPartitioning(JPAQuery<T> query) {
        return new JPAQueryPartitioning<>(query);
    }
}
