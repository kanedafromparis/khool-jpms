package io.github.kandefromparis.khool.jpms.sample;
import io.github.kandefromparis.khool.jpms.sample.model.DataSample;
import io.github.kandefromparis.khool.jpms.sample.service.DataService;
import java.util.ServiceLoader;
public class DataApp {
public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ServiceLoader<DataService> dataService = ServiceLoader.load(DataService.class);
        DataService service = dataService.findFirst().get();
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
