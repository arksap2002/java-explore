package com.example.parsing.changing;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.Assert;
import java.io.IOException;
import java.nio.charset.Charset;

public class ChangingOutToArrTest {

    @Test
    public void removing() throws IOException {
        Assert.assertEquals(IOUtils.resourceToString("/SystemerrInsteadOfSystemout.java", Charset.defaultCharset()), ChangingOutToArr.transformResource("/input.java"));
    }
}