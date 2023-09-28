package io.github.kandefromparis.khool.jpms.sample.service.mem;

import io.github.kandefromparis.khool.jpms.sample.model.DataSample;

public interface DataService {
    public String create(DataSample sample);

    public DataSample read(String datasampleId);

    public DataSample update(String datasampleId, DataSample sample);

    public DataSample delete(String datasampleId);
}
