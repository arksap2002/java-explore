package com.example.parsing.changing;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;

class IntMultiplyBy10Test {
    @Test
    void changing() throws IOException {
        Assertions.assertEquals(IOUtils.resourceToString("/AllIntFor10.java", Charset.defaultCharset()), IntMultiplyBy10.transformResource("/input.java"));
    }

}