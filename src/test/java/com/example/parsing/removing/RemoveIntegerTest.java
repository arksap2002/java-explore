package com.example.parsing.removing;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.Assert;
import java.io.IOException;
import java.nio.charset.Charset;

public class RemoveIntegerTest {

    @Test
    public void removing() throws IOException {
        Assert.assertEquals(IOUtils.resourceToString("/NoInteger.java", Charset.defaultCharset()), RemoveInteger.transformResource("/input.java"));

    }
}