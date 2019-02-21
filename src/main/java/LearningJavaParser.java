import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class LearningJavaParser {
    public static void main(String[] args) throws IOException {
        ClassLoader cl = new LearningJavaParser().run();
//        InputStream file = cl.getResourceAsStream("1.txt");
        System.out.println(IOUtils.resourceToString("/1.txt", Charset.defaultCharset()));

    }

    private ClassLoader run() {
        return getClass().getClassLoader();
    }
}
