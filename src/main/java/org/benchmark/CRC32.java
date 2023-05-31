package org.benchmark;

public class CRC32<K> implements HashFunc<K> {
        public int hash(K key) {
            java.util.zip.CRC32 crc32 = new java.util.zip.CRC32();
            byte[] bytes = key.toString().getBytes();
            crc32.update(bytes);
            return (int) crc32.getValue();
        }
    }