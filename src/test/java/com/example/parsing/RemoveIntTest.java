package com.example.parsing;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.*;

class RemoveIntTest {

    @Test
    void removing() throws IOException {
        assertEquals(IOUtils.resourceToString("/NoInt", Charset.defaultCharset()), RemoveInt.transformResource("/input.java"));
    }
}