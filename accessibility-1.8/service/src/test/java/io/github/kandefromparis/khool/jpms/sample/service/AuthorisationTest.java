package io.github.kandefromparis.khool.jpms.sample.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class AuthorisationTest {

    @ParameterizedTest
    @CsvSource({ "aller, soit sympa, true",
        "j'ai pas de basket, false"})
    void canRunMyAppTrue(String lic, boolean expectedRes) {
        Authorisation authorisation = new Authorisation();
        assertEquals(expectedRes, authorisation.canRunMyApp(lic));

    }
}