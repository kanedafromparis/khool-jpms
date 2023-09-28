package io.github.kandefromparis.khool.jpms.sample;

import io.github.kandefromparis.khool.jpms.sample.model.DataSample;
import io.github.kandefromparis.khool.jpms.sample.service.mem.DataService;

public class DataApp {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        DataService service = (DataService)Class.forName("io.github.kandefromparis.khool.jpms.sample.service.mem.DataMemService").newInstance();
        System.out.println("create");
        String id = service.create(new DataSample());
        System.out.println("created : "+id);
        DataSample dataSample = service.read("0");
        System.out.println("read (null) : "+dataSample);
        dataSample = service.read(id);
        //service.update(new DataSample());
        service.delete(id);
        System.out.println("deleted : "+id);

    }


}
