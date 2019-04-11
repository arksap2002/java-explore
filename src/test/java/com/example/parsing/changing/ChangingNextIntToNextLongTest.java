package com.example.parsing.changing;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;

class ChangingNextIntToNextLongTest {

    @Test
    void changing() throws IOException {
        Assertions.assertEquals(IOUtils.resourceToString("/NextLongInsteadOfNextInt.java", Charset.defaultCharset()), ChangingNextIntToNextLong.transformResource("/input.java"));
    }
}