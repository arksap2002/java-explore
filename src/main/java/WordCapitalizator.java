public class WordCapitalizator {
    public static String capitalizeWords(String words) {
        words = words.toLowerCase();
        char[] chars = words.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isLetter(chars[i])) {
                if ((i == 0) || (!Character.isLetter(chars[i - 1]))) {
                    chars[i] = Character.toUpperCase(chars[i]);
                } else {
                    chars[i] = Character.toLowerCase(chars[i]);
                }
            }
        }
        return new String(chars);
    }
}