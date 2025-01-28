package org.example.cipher;

/**
 *
 * Creates an encrypted/decrypted text based on user input from the command line.
 *
 * A Cipher-object is created using the chosen algorithm for encryption/decryption, and the
 * manipulated text is returned to the user.
 *
 */

public abstract class TextManipulator {

    public static String createText(String algorithmType, String mode, String text, int key) {
        Cipher cipher = CipherCreator.getCipher(algorithmType);
        if (cipher != null) {
            return cipher.manipulateText(mode, text, key);
        }
        return null;
    }
}
