package com.gcp.crud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.List;

import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.ProjectionEntity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.cloud.datastore.StringValue;


@Service("datastoreService")
@Transactional
public class DatastoreService {
	//Cloud Datastore
	private final Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    private final KeyFactory keyFactory = datastore.newKeyFactory().setNamespace("SYPARK").setKind("TB_BAS_0100");
    
    Key taskKey = datastore.allocateId(keyFactory.newKey()); //insert entity key
    
    
	//select
	public List<Map<String, Object>>  select() throws Exception {
		
		//query send                                    //newKeyQueryBuilder -> key select
		Query<Entity> query =Query.newEntityQueryBuilder()
				.setNamespace("SYPARK").setKind("TB_BAS_0100")
				.setOrderBy(OrderBy.asc("ORD_NO"))
				.build();
		
		//get entities
		QueryResults<Entity> entities = datastore.run(query);

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		
		//get data from entity
		while(entities.hasNext()) {
			Entity entity = entities.next();
			System.out.println(entity);
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("CLASS_CD", entity.getString("CLASS_CD"));
			map.put("CLASS_NM", entity.getString("CLASS_NM"));
			map.put("CLASS_TYPE_CD", entity.getString("CLASS_TYPE_CD"));
			map.put("ORD_NO", entity.getLong("ORD_NO"));
			resultList.add(map);
		}
		
		System.out.println(resultList);
		
		return resultList;
	}    
    
    
	//insert
	public void insert() throws Exception {
		
	    Entity task = Entity.newBuilder(taskKey)
	    		.set("CLASS_CD", "AD") 
		        .set("CLASS_NM", "AL_Test") 
		        .set("CLASS_TYPE_CD", "Parts") 
		        .set("ORD_NO", 3) 
		        .build();
		datastore.put(task);
	}
	
    
	//update
	public void update() throws Exception {
		
	    Entity task = Entity.newBuilder(taskKey)
	    		.set("CLASS_CD", "AD") 
		        .set("CLASS_NM", "AL_Test") 
		        .set("CLASS_TYPE_CD", "Product") 
		        .set("ORD_NO", 3) 
		        .build();
		datastore.update(task);
	}
	
	//delete
	public void delete() throws Exception {
		
		datastore.delete(taskKey);
		
	}
	
}
