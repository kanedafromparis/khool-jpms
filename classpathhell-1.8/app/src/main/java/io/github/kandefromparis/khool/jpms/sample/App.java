package io.github.kandefromparis.khool.jpms.sample;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

public class App {
    public static void main(String[] args)
            throws NoSuchMethodException, ClassNotFoundException,
            InvocationTargetException, IllegalAccessException, NoSuchFieldException, InstantiationException {
        App app = new App();
        app.runHello();
    }

    private void runHello()
            throws NoSuchMethodException, ClassNotFoundException,
            InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<?> aClass = Class.forName("io.github.kandefromparis.khool.jpms.sample.service.Hello");
        Method toString = aClass.getDeclaredMethod("toString");
        Object instance = aClass.newInstance();
        String invoked = (String)toString.invoke(instance);
        if(invoked instanceof String){
            String invokedToString = (String) invoked;
            System.out.println(" invokedToString : " +invokedToString);
        }else{
            System.out.println(" invoked not instanceof String ");
        }
    }
}
