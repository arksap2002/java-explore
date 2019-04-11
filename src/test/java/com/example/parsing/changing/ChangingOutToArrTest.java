package com.example.parsing.changing;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;

class ChangingOutToArrTest {

    @Test
    void removing() throws IOException {
        Assertions.assertEquals(IOUtils.resourceToString("/SystemerrInsteadOfSystemout.java", Charset.defaultCharset()), ChangingOutToArr.transformResource("/input.java"));
    }
}