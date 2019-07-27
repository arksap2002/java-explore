package com.example.parsing.changing;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.Assert;
import java.io.IOException;
import java.nio.charset.Charset;

public class MinusInsteadOfPlusTest {
    @Test
    public void removing() throws IOException {
        Assert.assertEquals(IOUtils.resourceToString("/PlusToMinus.java", Charset.defaultCharset()), MinusInsteadOfPlus.transformResource("/input.java"));
    }
}