package org.example.cipher;

/**
 *
 * Algorithm for encrypting and decrypting a text using a unicode representation of the character shift
 * (including signs other than english letters).
 *
 * Example: encrypting the string, 'He77o!' -> 'Mj<<t&'.
 *
 */

final class Unicode implements Cipher {

    private static final int END_POINT = 'z';

    @Override
    public String manipulateText(String mode, String text, int key) {
        StringBuilder sb = new StringBuilder();
        char[] arr = text.toCharArray();
        for (char c : arr) {
            int newPosition = getPosition(c, mode, key);
            if (newPosition >= 0) {
                sb.append((char) newPosition);
            } else {
                return "Invalid action";
            }
        }
        return sb.toString();
    }

    private int getPosition(char c, String mode, int key) {
        return switch (mode) {
            case "enc" -> (c + key) % (END_POINT + key);
            case "dec" -> (c - key) % (END_POINT + key);
            default -> -1;
        };
    }
}
