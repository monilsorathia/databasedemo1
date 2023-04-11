package edu.usc.csci310.project;

import org.junit.jupiter.api.Test;

import static edu.usc.csci310.project.Encryption.decrypt;
import static edu.usc.csci310.project.Encryption.encrypt;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class EncryptionTest {

    @Test
    public void encryptFirst()
    {
        String input = "temp";
        String expected = "yjru";
        assertEquals(expected, encrypt(input, 5));
    }

    @Test
    public void encryptSecond()
    {
        String input = "TEMP";
        String expected = "YJRU";
        assertEquals(expected, encrypt(input, 5));
    }

    @Test
    public void encryptThird()
    {
        String input = "123";
        String expected = "678";
        assertEquals(expected, encrypt(input, 5));
    }

    @Test
    public void encryptFourth()
    {
        String input = "@!!";
        String expected = "@!!";
        assertEquals(expected, encrypt(input, 5));
    }

    @Test
    public void encryptFifth()
    {
        String input = "";
        String expected = "";
        assertEquals(expected, encrypt(input, 5));
    }

    @Test
    void decryptTest() {
        String input  = "yjru";
        String expected = "temp";
        assertEquals(expected, decrypt(input));
    }
}