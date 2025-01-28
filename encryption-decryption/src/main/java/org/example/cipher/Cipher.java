package org.example.cipher;

interface Cipher {

    /**
     *
     * @param mode a string that decides encryption or decryption of the input text.
     * @param text a text string to be manipulated.
     * @param key number of character shifts to be made.
     * @return a string-object that has been changed based on command line arguments.
     */

    String manipulateText(String mode, String text, int key);
}
