package com.example.parsing.changing;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.Assert;
import java.io.IOException;
import java.nio.charset.Charset;

public class ChangeIntToLongTest {
    @Test
    public void changing() throws IOException {
        Assert.assertEquals(IOUtils.resourceToString("/LongInsteadOfInt.java", Charset.defaultCharset()), ChangeIntToLong.transformResource("/input.java"));
    }

}