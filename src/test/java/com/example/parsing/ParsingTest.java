package com.example.parsing;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.Assert;
import java.io.IOException;
import java.nio.charset.Charset;

public class ParsingTest {

    @Test
    public void parsing() throws IOException {
        Assert.assertEquals(IOUtils.resourceToString("/PrintCode.java", Charset.defaultCharset()), Parsing.transformResource("/input.java"));
    }
}
