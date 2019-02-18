public class WordCapitalizator {
    public static String capitalizeWords(String words) {
        char[] chars = words.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if ((i == 0 || (!Character.isLetter(chars[i - 1]))) && Character.isLetter(chars[i]))
                chars[i] = Character.toUpperCase(chars[i]);
            if (i != 0 && Character.isLetter(chars[i - 1]))
                chars[i] = Character.toLowerCase(chars[i]);
        }
        return new String(chars);
    }
}