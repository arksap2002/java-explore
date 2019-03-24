package com.example.parsing;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.*;

class ChangingNextIntToNextLongTest {

    @Test
    void changing() throws IOException {
        assertEquals(IOUtils.resourceToString("/NextLongInsteadOfNextInt", Charset.defaultCharset()), ChangingNextIntToNextLong.transformResource("/input.java"));
    }
}