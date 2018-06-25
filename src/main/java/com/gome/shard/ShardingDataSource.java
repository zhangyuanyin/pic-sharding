package com.gome.shard;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;

@Component
public class ShardingDataSource {
	
	// 分表字段
	private static final String APP_NO = "APP_NO";
	// 表名
	private static final String TAB_PICTURE = "PICTURE";
	// 分8张表
	private static final int TAB_NUM = 1 << 3;
	
	@Autowired  
	DataSource dataSource;  
	
	DataSource shardingDataSource;  
	
	@PostConstruct  
	public void init() throws SQLException {  
		Map<String, DataSource> map = new HashMap<String, DataSource>();  
		map.put("dataSource", dataSource);  
		DataSourceRule dataSourceRule = new DataSourceRule(map);  
		
		List<TableRule> tableRuleList = new ArrayList<TableRule>();  
		List<String> actualList = new ArrayList<String>();  
		for (int i = 1; i <= TAB_NUM; i++) {  
			actualList.add(TAB_PICTURE + "_" + i);  
		}  
		//PICTURE逻辑表名，pList实际所有的分表  
		tableRuleList.add(new TableRule.TableRuleBuilder(TAB_PICTURE)  
				.actualTables(actualList)  
				.dataSourceRule(dataSourceRule)  
				.tableShardingStrategy(new TableShardingStrategy(APP_NO, new PicSingleKeyTableShardingAlgorithm())).build());
		
		ShardingRule shardingRule = ShardingRule.builder()  
				.dataSourceRule(dataSourceRule)  
				.tableRules(tableRuleList)  
				.build();  
		
		shardingDataSource = ShardingDataSourceFactory.createDataSource(shardingRule);  
	}  
	
	public DataSource getDataSource() {  
		return shardingDataSource;  
	}  
}
