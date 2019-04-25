package com.example.parsing.removing;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;

class RemoveMethodsTest {

    @Test
    void removing() throws IOException {
        Assertions.assertEquals(IOUtils.resourceToString("/NoMethods.java", Charset.defaultCharset()), RemoveMethods.transformResource("/input.java"));
    }
}