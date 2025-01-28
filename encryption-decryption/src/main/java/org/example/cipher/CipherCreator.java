package org.example.cipher;

/**
 *
 * Returns a Cipher-object that uses a specific algorithm to encrypt/decrypt a text string.
 *
 */

abstract class CipherCreator {

    static Cipher getCipher(String algorithmType) {
        return switch (algorithmType) {
            case "shift" -> new Shift();
            case "unicode" -> new Unicode();
            default -> null;
        };
    }
}
