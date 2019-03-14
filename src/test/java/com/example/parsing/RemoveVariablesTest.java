package com.example.parsing;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.*;

class RemoveVariablesTest {

    @Test
    void removing() throws IOException {
        assertEquals(IOUtils.resourceToString("/NoVariables", Charset.defaultCharset()), RemoveVariables.removing("/1.txt"));
    }
}