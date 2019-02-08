package main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewWordCapitalizatorTest {

    @Test
    void capitalizeWords() {
        assertEquals("", WordCapitalizator.capitalizeWords(""));
        assertEquals("vasya-vasya", WordCapitalizator.capitalizeWords("Vasya-Vasya"));
    }
}