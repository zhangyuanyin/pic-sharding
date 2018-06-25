package com.gome.shard;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;

/**
 * @content 影像系统分表逻辑实现
 * @author yyzhang
 * @since 2018年2月26日18:03:14
 */
public class PicSingleKeyTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<String>{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public Collection<String> doBetweenSharding(Collection<String> targetNames, ShardingValue<String> shardingValue) {
		Collection<String> result = new LinkedHashSet<String>(targetNames.size());  
        Range<String> range = shardingValue.getValueRange();
        String[] points = new String[]{range.lowerEndpoint(), range.upperEndpoint()};
        
        for (String point : points) {
        	for (String target : targetNames) {  
        		if (target.endsWith(HashAlgorithm.index(point))) {  
        			result.add(target);  
        		}  
        	}  
		}
        return result;  
	}
	
	/**
	 * 分表策略：对等值的where条件，动态指定表名
	 * 取值方式：采用表字段APP_NO的hash算法，结合分表数量进行散列
	 */
	@Override
	public String doEqualSharding(Collection<String> targetNames, ShardingValue<String> shardingValue) {
		String strategy = "";
		Collection<String> values = shardingValue.getValues();
		for (String value : values) {
			if(value == null)
				throw new IllegalArgumentException("【分表算法】等值算法中，参数 " + shardingValue.getColumnName() + " 值不能为空！！！");
			strategy += value;
		}
		
		// 依据指定规则，选择当前使用的数据库表名
		for (String target : targetNames) {
			if(target.endsWith(HashAlgorithm.index(strategy))){
				return target;
			}
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Collection<String> doInSharding(Collection<String> targetNames, ShardingValue<String> shardingValue) {
		Collection<String> result = new LinkedHashSet<String>(targetNames.size());
		Collection<String> values = shardingValue.getValues(); 
		
		for (String value : values) {
			for (String target : targetNames) {
				if(target.endsWith(HashAlgorithm.index(value))) {
					result.add(target);
				}
			}
		}
		return result;
	}
}
