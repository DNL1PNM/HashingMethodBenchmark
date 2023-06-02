package org.benchmark;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CustomSHA256<K> implements HashFunc<K> {
    private MessageDigest md;

    public CustomSHA256() {
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            // Обработка исключения в случае ошибки при создании хеш-функции SHA-256
            e.printStackTrace();
        }
    }

    public int hash(K key) {
        if (md == null) {
            // Хеш-функция не была успешно инициализирована
            return 0; // Или другое значение по умолчанию
        }

        byte[] bytes = key.toString().getBytes(StandardCharsets.UTF_8);
        byte[] digest = md.digest(bytes);
        return byteArrayToInt(digest);
    }

    private int byteArrayToInt(byte[] byteArray) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            value = (value << 8) + (byteArray[i] & 0xFF);
        }
        return value;
    }
}