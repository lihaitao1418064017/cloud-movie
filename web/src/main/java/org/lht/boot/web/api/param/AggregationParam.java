package org.lht.boot.web.api.param;


import com.google.common.collect.Lists;
import lombok.Getter;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.joda.time.DateTimeZone;
import org.lht.boot.web.common.exception.NotSupportedException;

import java.util.Arrays;
import java.util.List;

@Getter
public class AggregationParam extends Param {

    private List<AggregationBuilder> groupBys = Lists.newArrayList();

    private List<AggregationBuilder> aggregations = Lists.newArrayList();

    /**
     * 创建一个空的查询参数实体,该实体无任何参数.
     *
     * @return 无条件的参数实体
     */
    public static AggregationParam empty() {
        return new AggregationParam();
    }

    /**
     * 创建一个含有单个条件的参数实体,条件默认为is
     *
     * @param field 参数名称
     * @param value 参数值
     * @return 单个条件的参数实体
     * @see QueryParam#where(String, Object)
     */
    public static AggregationParam single(String field, Object value) {
        return empty().where(field, value);
    }

    public AggregationParam groupBy(String... groupBys) {
        Arrays.stream(groupBys)
                .map(field -> AggregationBuilders.terms(field).field(field).size(Integer.MAX_VALUE))
                .forEach(this.groupBys::add);
        return this;
    }

    public AggregationParam groupByAs(String groupBy, String as) {
        this.groupBys.add(AggregationBuilders.terms(as).field(groupBy).size(Integer.MAX_VALUE));
        return this;
    }

    public AggregationParam groupBy(DateHistogramInterval interval, String... groupBys) {
        Arrays.stream(groupBys)
                .map(field ->
                        AggregationBuilders
                                .dateHistogram(field)
                                .dateHistogramInterval(interval)
                                .field(field)
                                .minDocCount(1)
                                .timeZone(DateTimeZone.forOffsetHours(8))
                )
                .forEach(this.groupBys::add);
        return this;
    }

    public AggregationParam groupBy(DateHistogramInterval interval, String formatter, String groupBy) {
        this.groupBys.add(AggregationBuilders
                .dateHistogram(groupBy)
                .dateHistogramInterval(interval)
                .field(groupBy)
                .minDocCount(1)
                .format(formatter)
                .timeZone(DateTimeZone.forOffsetHours(8)));
        return this;
    }

    public AggregationParam groupByAs(DateHistogramInterval interval, String groupBy, String as) {
        this.groupBys.add(AggregationBuilders
                .dateHistogram(as)
                .dateHistogramInterval(interval)
                .field(groupBy)
                .minDocCount(1)
                .timeZone(DateTimeZone.forOffsetHours(8)));
        return this;
    }

    public AggregationParam groupByAs(DateHistogramInterval interval, String formatter, String groupBy, String as) {
        this.groupBys.add(AggregationBuilders
                .dateHistogram(as)
                .dateHistogramInterval(interval)
                .field(groupBy)
                .minDocCount(1)
                .format(formatter)
                .timeZone(DateTimeZone.forOffsetHours(8)));
        return this;
    }

    /**
     * GeoHash聚合查询
     *
     * @param precision geohash经度
     * @param groupBy   聚合字段, 必须为GeoPoint类型
     * @return
     */
    public AggregationParam groupByAs(Integer precision, String groupBy) {
        return groupByAs(precision, groupBy, groupBy);

    }

    /**
     * GeoHash聚合查询
     *
     * @param precision geohash经度
     * @param groupBy   聚合字段, 必须为GeoPoint类型
     * @param as        聚合后的结果字段
     * @return
     */
    public AggregationParam groupByAs(Integer precision, String groupBy, String as) {
        this.groupBys.add(
                AggregationBuilders
                        .geohashGrid(as)
                        .precision(precision)
                        .field(groupBy)
                        .size(Integer.MAX_VALUE)
        );
        return this;
    }

    public AggregationParam groupBy(AggregationBuilder... groupBys) {
        this.groupBys.addAll(Arrays.asList(groupBys));
        return this;
    }

    public AggregationParam aggregation(Aggregation... aggregations) {
        Arrays.stream(aggregations)
                .map(configField -> {
                    String field = configField.getField();
                    String as = configField.getAs();
                    switch (configField.getType()) {
                        case AVG:
                            return AggregationBuilders.avg(as).field(field);
                        case MAX:
                            return AggregationBuilders.max(as).field(field);
                        case MIN:
                            return AggregationBuilders.min(as).field(field);
                        case SUM:
                            return AggregationBuilders.sum(as).field(field);
                        case COUNT:
                            return AggregationBuilders.count(as).field(field);
                        default:
                            throw new NotSupportedException();
                    }
                })
                .forEach(this.aggregations::add);
        return this;
    }

    public AggregationParam aggregation(AggregationBuilder... aggregations) {
        this.aggregations.addAll(Arrays.asList(aggregations));
        return this;
    }


}
