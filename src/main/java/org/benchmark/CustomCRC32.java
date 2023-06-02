package org.benchmark;
import java.util.zip.CRC32;

public class CustomCRC32<K> implements HashFunc<K> {
    public int hash(K key) {
        CRC32 crc32 = new CRC32();
        byte[] bytes = key.toString().getBytes();
        crc32.update(bytes);
        return (int) crc32.getValue();
    }
    public String name() {
        return "CRC32";
    }
}