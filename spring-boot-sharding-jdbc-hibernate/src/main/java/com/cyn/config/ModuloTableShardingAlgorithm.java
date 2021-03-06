package com.cyn.config;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * Created by cyn on 2019/5/17.
 */
public class ModuloTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Long> {

    /**
     * select * from t_order from t_order where order_id = 11
     * └── SELECT *  FROM t_order_1 WHERE order_id = 11
     * select * from t_order from t_order where order_id = 44
     * └── SELECT *  FROM t_order_0 WHERE order_id = 44
     */
    @Override
    public String doEqualSharding(Collection<String> collection, ShardingValue<Long> shardingValue) {
        for (String tableName : collection) {
            if (tableName.endsWith(shardingValue.getValue() % 2 + "")) {
                return tableName;
            }
        }

        throw new IllegalArgumentException();
    }

    /**
     * select * from t_order from t_order where order_id in (11,44)
     * ├── SELECT *  FROM t_order_0 WHERE order_id IN (11,44)
     * └── SELECT *  FROM t_order_1 WHERE order_id IN (11,44)
     * select * from t_order from t_order where order_id in (11,13,15)
     * └── SELECT *  FROM t_order_1 WHERE order_id IN (11,13,15)
     * select * from t_order from t_order where order_id in (22,24,26)
     * └──SELECT *  FROM t_order_0 WHERE order_id IN (22,24,26)
     */
    @Override
    public Collection<String> doInSharding(Collection<String> collection, ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(collection.size());
        for (Long value : shardingValue.getValues()) {
            for (String table : collection) {
                if (table.endsWith(value % 2 + "")) {
                    result.add(table);
                }
            }
        }
        return result;
    }

    /**
     * select * from t_order from t_order where order_id between 10 and 20
     * ├── SELECT *  FROM t_order_0 WHERE order_id BETWEEN 10 AND 20
     * └── SELECT *  FROM t_order_1 WHERE order_id BETWEEN 10 AND 20
     */
    @Override
    public Collection<String> doBetweenSharding(Collection<String> collection, ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(collection.size());
        Range<Long> range = shardingValue.getValueRange();
        for (Long i = range.lowerEndpoint(); i < range.upperEndpoint(); i++) {
            for (String each : collection) {
                if (each.endsWith(i % 2 + "")) {
                    result.add(each);
                }
            }
        }

        return result;
    }
}
