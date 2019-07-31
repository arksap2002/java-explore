package com.example.parsing.removing;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.Assert;
import java.io.IOException;
import java.nio.charset.Charset;

public class RemoveMethodsTest {

    @Test
    public void removing() throws IOException {
        Assert.assertEquals(IOUtils.resourceToString("/NoMethods.java", Charset.defaultCharset()), RemoveMethods.transformResource("/input.java"));
    }
}