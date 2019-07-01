package com.example.parsing.changing;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;

class ConvolutionOfConstantsTest {

    @Test
    void transformResource() throws IOException {
        Assertions.assertEquals(IOUtils.resourceToString("/ConvolutionOfConstants.java", Charset.defaultCharset()), ConvolutionOfConstants.transformResource("/input5.java"));
    }
}