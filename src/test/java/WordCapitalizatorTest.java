import static org.junit.Assert.assertEquals;

public class WordCapitalizatorTest {

    @org.junit.Test
    public void capitalizeWords() {
        assertEquals("Hello, World", WordCapitalizator.capitalizeWords("heLlo, woRld"));
        assertEquals("Vasya , Vasya", WordCapitalizator.capitalizeWords("vasYa , Vasya"));
        assertEquals(" Vasya - Vasya ", WordCapitalizator.capitalizeWords(" vasya - vasya "));
	assertEquals("", WordCapitalizator.capitalizeWords(""));
        assertEquals("Vasya-Vasya", WordCapitalizator.capitalizeWords("vasya-vasYa"));
	assertEquals(" a", WordCapitalizator.capitalizeWords(" a"));
    }
}
