package com.example.parsing;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.*;

class RemoveIntegerTest {

    @Test
    void removing() throws IOException {
        assertEquals(IOUtils.resourceToString("/NoInteger", Charset.defaultCharset()), RemoveInteger.transformResource("/input.java"));

    }
}