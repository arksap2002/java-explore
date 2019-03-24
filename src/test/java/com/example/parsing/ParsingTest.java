package com.example.parsing;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.*;

class ParsingTest {

    @Test
    void parsing() throws IOException {
        assertEquals(IOUtils.resourceToString("/PrintCode", Charset.defaultCharset()), Parsing.transformResource("/input.java"));
    }
}
