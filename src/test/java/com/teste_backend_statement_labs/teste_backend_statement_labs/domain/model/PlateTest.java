package com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlateTest {

    @Test
    void shouldCreatePlateWithValidValue() {
        Plate plate = new Plate("LD-23-45-AB");
        assertEquals("LD-23-45-AB", plate.getValue());
    }

    @Test
    void shouldConvertToUpperCase() {
        Plate plate = new Plate("ld-23-45-ab");
        assertEquals("LD-23-45-AB", plate.getValue());
    }

    @Test
    void shouldThrowExceptionForNull() {
        assertThrows(IllegalArgumentException.class, () -> new Plate(null));
    }

    @Test
    void shouldThrowExceptionForEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Plate(""));
    }

    @Test
    void shouldThrowExceptionForInvalidFormat() {
        assertThrows(IllegalArgumentException.class, () -> new Plate("INVALID"));
        assertThrows(IllegalArgumentException.class, () -> new Plate("L-23-45-AB"));
        assertThrows(IllegalArgumentException.class, () -> new Plate("LD2345AB"));
    }

    @Test
    void shouldHaveCorrectEqualsAndHashCode() {
        Plate plate1 = new Plate("LD-23-45-AB");
        Plate plate2 = new Plate("LD-23-45-AB");
        Plate plate3 = new Plate("LU-12-34-CD");

        assertEquals(plate1, plate2);
        assertNotEquals(plate1, plate3);
        assertEquals(plate1.hashCode(), plate2.hashCode());
    }

}
