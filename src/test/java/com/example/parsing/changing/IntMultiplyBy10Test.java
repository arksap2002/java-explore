package com.example.parsing.changing;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.Assert;
import java.io.IOException;
import java.nio.charset.Charset;

public class IntMultiplyBy10Test {
    @Test
    public void changing() throws IOException {
        Assert.assertEquals(IOUtils.resourceToString("/AllIntFor10.java", Charset.defaultCharset()), IntMultiplyBy10.transformResource("/input.java"));
    }

}