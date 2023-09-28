package io.github.kandefromparis.khool.jpms.sample.service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Authorisation {
    private byte[] expectedLicense="aller, soit sympa".getBytes(StandardCharsets.UTF_8);

    private byte[] getExpectedLicense(){
        return  expectedLicense;
    }
    public Authorisation(){}
    public boolean canRunMyApp(String license)
    {

        return this.checkLicense(license.getBytes(StandardCharsets.UTF_8));
    }

    private boolean checkLicense(byte[] license){
        if (license.length == this.expectedLicense.length){
            return Arrays.equals(license, this.expectedLicense);
        }else {
            return false;
        }
    }
}
