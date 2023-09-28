package io.github.kandefromparis.khool.jpms.sample.service;

import io.github.kandefromparis.khool.jpms.sample.service.HelloService;

public class Hello implements HelloService{
    String hello = "hello A !!!";

    public String getHello(){
        return hello;
    }

    @Override
    public String toString() {
        return hello;
    }
}
