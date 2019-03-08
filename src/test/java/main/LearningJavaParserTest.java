package main;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.junit.Assert.assertEquals;

class LearningJavaParserTest {

    @Test
    void parsing() throws IOException {
        assertEquals(IOUtils.resourceToString("/Test1.txt", Charset.defaultCharset()), main.LearningJavaParser.parsing("1.txt"));
    }
}