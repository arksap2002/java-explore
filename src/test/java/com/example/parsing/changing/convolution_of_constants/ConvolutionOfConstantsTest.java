package com.example.parsing.changing.convolution_of_constants;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;

class ConvolutionOfConstantsTest {

    @Test
    void changing() throws IOException {
        Assertions.assertEquals(IOUtils.resourceToString("/ConvolutionOfConstants.java", Charset.defaultCharset()), Main.transformResource("/input5.java"));
    }
}