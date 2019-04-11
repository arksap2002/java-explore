package com.example.parsing.removing;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RemoveClassMembersTest {
    @Test
    void removing() throws IOException {
        Assertions.assertEquals(IOUtils.resourceToString("/NoClassMembers.java", Charset.defaultCharset()), RemoveClassMembers.transformResource("/input.java"));
    }
}
