package org.benchmark;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class СustomSHA256<K> implements HashFunc<K> {
    public int hash(K key) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = key.toString().getBytes(StandardCharsets.UTF_8);
            byte[] digest = md.digest(bytes);
            return byteArrayToInt(digest);
        } catch (NoSuchAlgorithmException e) {
            // Обработка исключения в случае ошибки при создании хеш-функции SHA-256
            e.printStackTrace();
            return 0; // Или другое значение по умолчанию
        }
    }

    private int byteArrayToInt(byte[] byteArray) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            value = (value << 8) + (byteArray[i] & 0xFF);
        }
        return value;
    }
}