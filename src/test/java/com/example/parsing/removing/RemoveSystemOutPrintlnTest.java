package com.example.parsing.removing;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.Assert;
import java.io.IOException;
import java.nio.charset.Charset;

public class RemoveSystemOutPrintlnTest {

    @Test
    public void removing() throws IOException {
        Assert.assertEquals(IOUtils.resourceToString("/NoSystemoutplintln.java", Charset.defaultCharset()), RemoveSystemOutPrintln.transformResource("/input.java"));
    }
}