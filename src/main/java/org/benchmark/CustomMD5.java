package org.benchmark;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CustomMD5<K> implements HashFunc<K> {
    private MessageDigest md;
    public CustomMD5() {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            // Обработка исключения в случае ошибки при создании хеш-функции MD5
            e.printStackTrace();
        }
    }

    public int hash(K key) {
        byte[] bytes = key.toString().getBytes(StandardCharsets.UTF_8);
        byte[] digest = md.digest(bytes);
        BigInteger bigInt = new BigInteger(1, digest);
        return bigInt.intValue();
    }

}