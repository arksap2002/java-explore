package com.example.parsing.changing.convolutionOfConstants;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;

public class MainTest {
    @Test
    public void changing() throws IOException {
        Assert.assertEquals(IOUtils.resourceToString("/ConvolutionOfConstants.java", Charset.defaultCharset()), Main.transformResource("/input5.java"));
    }
}