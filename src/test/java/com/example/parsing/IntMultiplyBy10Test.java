package com.example.parsing;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntMultiplyBy10Test {
    @Test
    void changing() throws IOException {
        assertEquals(IOUtils.resourceToString("/AllIntFor10", Charset.defaultCharset()), IntMultiplyBy10.changing("/1.txt"));
    }

}