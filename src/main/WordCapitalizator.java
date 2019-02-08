package main;

class WordCapitalizator {
    static String capitalizeWords(String words) {
        char[] chars = words.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if ((i == 0 || (!is_letter(chars[i - 1]))) && is_letter(chars[i]))
                chars[i] = Character.toUpperCase(chars[i]);
            if (i != 0 && is_letter(chars[i - 1]))
                chars[i] = Character.toLowerCase(chars[i]);
        }
        return new String(chars);
    }
    private static boolean is_letter(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
    }
}