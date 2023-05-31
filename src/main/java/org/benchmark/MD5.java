package org.benchmark;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5<K> implements HashFunc<K> {
    public int hash(K key) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = key.toString().getBytes();
            byte[] digest = md.digest(bytes);
            BigInteger bigInt = new BigInteger(1, digest);
            return bigInt.intValue();
        } catch (NoSuchAlgorithmException e) {
            // Обработка исключения в случае ошибки при создании хеш-функции MD5
            e.printStackTrace();
            return 0; // Или другое значение по умолчанию
        }
    }
}