package org.example.cipher;

/**
 *
 * Algorithm for encrypting and decrypting a text using only letters from the english alphabet. If a character in
 * the text is not a letter, then the character will not be manipulated.
 *
 * Example: encrypting the string, 'He77o!' -> 'mj77t!'.
 *
 */

final class Shift implements Cipher {

    private static final int ALPHABET_LENGTH = 26;
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    @Override
    public String manipulateText(String mode, String text, int key) {
        StringBuilder sb = new StringBuilder();
        char[] textArr = text.toLowerCase().toCharArray();
        int index = 0;
        while (index < textArr.length) {
            char c = textArr[index];
            if (Character.isLetter(c)) {
                int indexChar = ALPHABET.indexOf(c);
                int newPosition = getPosition(indexChar, mode, key);
                sb.append(ALPHABET.charAt(newPosition));
            } else {
                sb.append(c);
            }
            index++;
        }
        return sb.toString();
    }

    private int getPosition(int c, String mode, int key) {
        return switch (mode) {
            case "enc" -> (c + key) % ALPHABET_LENGTH;
            case "dec" -> ((c - key) > 0) ? (c - key) % ALPHABET_LENGTH :
                (c - key + ALPHABET_LENGTH) % ALPHABET_LENGTH;
            default -> -1;
        };
    }
}
