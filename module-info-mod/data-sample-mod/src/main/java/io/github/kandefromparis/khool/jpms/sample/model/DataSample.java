package io.github.kandefromparis.khool.jpms.sample.model;

public class DataSample {
    String hello = "hello A !!!";

    public String getHello(){
        return hello;
    }

    @Override
    public String toString() {
        return hello;
    }
}
