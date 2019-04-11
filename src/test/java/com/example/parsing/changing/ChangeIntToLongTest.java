package com.example.parsing.changing;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;

class ChangeIntToLongTest {
    @Test
    void changing() throws IOException {
        Assertions.assertEquals(IOUtils.resourceToString("/LongInsteadOfInt.java", Charset.defaultCharset()), ChangeIntToLong.transformResource("/input.java"));
    }

}