package com.hanwencheng;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hanwencheng on 1/25/16.
 */
public class ValueParameters {
    private Map<String, Object> schemasMap;

    public ValueParameters() {
        this.schemasMap = new HashMap<String ,Object>();
    }

    /**
     * create certain schema in map
     * @param name schema name
     * @param schema schema object to be stored
     */
    public void create(String name, Object schema){
        this.schemasMap.put(name, schema);
    }

    /**
     * delete the schema
     * @param name schema name
     */
    public void delete(String name){
        this.schemasMap.remove(name);
    }

    /**
     * delete the schema only when the object also mapped to the certain value
     * @param name the name of the schema
     * @param schema the schema object
     */
    public boolean delete(String name, Object schema ){
        return this.schemasMap.remove(name, schema);
    }

    /**
     * get the schema
     * @param name schema name
     * @return the schema object
     */
    public Object get(String name){
        return this.schemasMap.get(name);
    }
}
