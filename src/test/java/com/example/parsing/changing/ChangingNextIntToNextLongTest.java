package com.example.parsing.changing;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.Assert;
import java.io.IOException;
import java.nio.charset.Charset;

public class ChangingNextIntToNextLongTest {

    @Test
    public void changing() throws IOException {
        Assert.assertEquals(IOUtils.resourceToString("/NextLongInsteadOfNextInt.java", Charset.defaultCharset()), ChangingNextIntToNextLong.transformResource("/input.java"));
    }
}