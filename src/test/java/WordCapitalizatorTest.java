package main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class WordCapitalizatorTest {

    @org.junit.jupiter.api.Test
    void capitalizeWords() {
        assertEquals("Hello, World", WordCapitalizator.capitalizeWords("heLlo, woRld"));
        assertEquals("Vasya , Vasya", WordCapitalizator.capitalizeWords("vasYa , Vasya"));
        assertEquals(" Vasya - Vasya ", WordCapitalizator.capitalizeWords(" vasya - vasya "));
    }
}
