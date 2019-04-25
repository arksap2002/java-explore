package com.example.parsing.removing;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;

class RemoveSystemOutPrintlnTest {

    @Test
    void removing() throws IOException {
        Assertions.assertEquals(IOUtils.resourceToString("/NoSystemoutplintln.java", Charset.defaultCharset()), RemoveSystemOutPrintln.transformResource("/input.java"));
    }
}