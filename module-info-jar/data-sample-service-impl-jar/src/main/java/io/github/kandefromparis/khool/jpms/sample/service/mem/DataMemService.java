package io.github.kandefromparis.khool.jpms.sample.service.mem;

import io.github.kandefromparis.khool.jpms.sample.model.DataSample;

import java.util.HashMap;

public class DataMemService implements DataService{
    private HashMap<String,DataSample> mem = new HashMap();

    public String create(DataSample sample){
         String id = String.valueOf(sample.hashCode());
         mem.put(id,sample);
         return id;
    }

    public DataSample read(String datasampleId){
        return mem.get(datasampleId);
    }

    public DataSample update(String datasampleId,DataSample sample){
        mem.put(datasampleId,sample);
        return sample;
    }

    public DataSample delete(String datasampleId){
        DataSample deleted=mem.remove(datasampleId);
        return deleted;
    }
}
