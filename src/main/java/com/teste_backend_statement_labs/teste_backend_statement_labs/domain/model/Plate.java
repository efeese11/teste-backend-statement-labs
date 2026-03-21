package com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model;

import java.util.Objects;

public class Plate {
    private final String value;

    public Plate(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Plate cannot be null or empty");
        }

        value = value.toUpperCase().trim();

        if (!value.matches("^[A-Z]{2}-\\d{2}-\\d{2}-[A-Z]{2}$")) {
            throw new IllegalArgumentException("Invalid plate format");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plate)) return false;
        Plate plate = (Plate) o;
        return value.equals(plate.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
