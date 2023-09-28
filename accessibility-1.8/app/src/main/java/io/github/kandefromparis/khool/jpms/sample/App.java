package io.github.kandefromparis.khool.jpms.sample;

import io.github.kandefromparis.khool.jpms.sample.service.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

public class App {
    public static void main(String[] args)
            throws NoSuchMethodException, ClassNotFoundException,
            InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        App app = new App();
        app.byPass();
    }

    private void byPass()
            throws NoSuchMethodException, ClassNotFoundException,
            InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Authorisation authorisation = new Authorisation();
        Class<? extends Authorisation> authorisationClass = authorisation.getClass();

        Method checkLicenseMethode = authorisationClass.getDeclaredMethod("checkLicense", byte[].class);
        try {
            byte[] notExpectedLicense = "not the right pass".getBytes(StandardCharsets.UTF_8);
            Object invoked = checkLicenseMethode.invoke(authorisation, notExpectedLicense);
            System.out.println("expectedLicense result: " + invoked);
        }catch (IllegalAccessException e) {
            System.err.println("checkLicense "+e.getLocalizedMessage());
        }
        System.out.println("----------");

        checkLicenseMethode.setAccessible(true);
        byte[] notExpectedLicense = "not the right pass".getBytes(StandardCharsets.UTF_8);

        Object invoked = checkLicenseMethode.invoke(authorisation, notExpectedLicense);
        System.out.println("notExpectedLicense result: " + invoked);
        System.out.println("----------");
        byte[] expectedLicense = "aller, soit sympa".getBytes(StandardCharsets.UTF_8);
        invoked = checkLicenseMethode.invoke(authorisation, expectedLicense);
        System.out.println("expectedLicense result: " + invoked);
        System.out.println("----------");

    }
}
