package com.example.parsing;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.*;

class MinusInsteadOfPlusAndPlusInsteadOfMultiplikationTest {

    @Test
    void changing() throws IOException {
        assertEquals(IOUtils.resourceToString("/PlusToMinusAndMultiplyToPlus.java", Charset.defaultCharset()), MinusInsteadOfPlusAndPlusInsteadOfMultiplikation.transformResource("/input.java"));

    }
}