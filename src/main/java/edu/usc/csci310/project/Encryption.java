package edu.usc.csci310.project;

public class Encryption {

    public static String encrypt(String plaintext, int shift) {
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char c = plaintext.charAt(i);
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    ciphertext.append((char) ('A' + (c - 'A' + shift) % 26));
                } else {
                    ciphertext.append((char) ('a' + (c - 'a' + shift) % 26));
                }
            } else if (Character.isDigit(c))
            {
                ciphertext.append((char) ('0' + (c - '0' + shift) % 26));
            } else
            {
                ciphertext.append(c);
            }
        }
        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext) {
        return encrypt(ciphertext, 21);
    }

}
