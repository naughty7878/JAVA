package com.test.springboot.sharding.algorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @author chengheng
 * @description TODO
 * @date 2021/11/19
 */
public class CustomShardingAlgorithm implements PreciseShardingAlgorithm<Integer> {

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Integer> shardingValue) {
        String value = shardingValue.getValue().toString();
        for (String targetName : availableTargetNames) {
            Integer i = Integer.parseInt(value) % 2;
            if (targetName.contains(i.toString())) {
                return targetName;
            }
        }
        throw new IllegalArgumentException();
    }
}
