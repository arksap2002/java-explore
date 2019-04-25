package com.example.parsing.removing;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;

class RemoveFieldsTest {

    @Test
    void removing() throws IOException {
        Assertions.assertEquals(IOUtils.resourceToString("/NoFields.java", Charset.defaultCharset()), RemoveFields.transformResource("/input.java"));

    }
}