package main;

public class WordCapitalizator {
    public static String capitalizeWords(String words) {
        char[] chars = words.toCharArray();
        if (('a' <= chars[0]) && (chars[0] <= 'z')) {
            chars[0] = Character.toUpperCase(chars[0]);
        }
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == ' ') {
                chars[i + 1] = Character.toUpperCase(chars[i + 1]);
            } else {
                if (('A' <= chars[0]) && (chars[0] <= 'Z')) {
                    chars[i + 1] = Character.toLowerCase(chars[i + 1]);
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (char aChar : chars) {
            stringBuilder.append(aChar);
        }
        return stringBuilder.toString();
    }
}