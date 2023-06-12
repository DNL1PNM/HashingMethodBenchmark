package org.benchmark;
import java.util.LinkedList;
import java.util.List;

public class DoubleHash<K, V> extends HashTable<K, V> {
    private List<Entry<K, V>> table;

    public DoubleHash(HashFunc<K> hashFunction, int size) {
        createTable(size);
        setHashFunction(hashFunction, size);
    }

    @Override
    public void insert(K key, V value) {
        int hash1 = getHashFunction().hash(key);
        int hash2 = hash2(key);
        int index = Math.abs(hash1) % getSize();

        while (table.get(index) != null) {
            index = (index + Math.abs(hash2)) % getSize();
        }

        table.set(index, new Entry<>(key, value));
    }

    @Override
    public void remove(K key) {
        int hash1 = getHashFunction().hash(key);
        int hash2 = hash2(key);
        int index = Math.abs(hash1) % getSize();

        while (table.get(index) != null && !table.get(index).getKey().equals(key)) {
            index = (index + Math.abs(hash2)) % getSize();
        }

        if (table.get(index) != null && table.get(index).getKey().equals(key)) {
            table.set(index, null);
        }
    }

    public V search(K key) {
        int hash1 = getHashFunction().hash(key);
        // Вычисление хеш-кода ключа с помощью первой хеш-функции
        int hash2 = hash2(key);
        // Вычисление хеш-кода ключа с помощью второй хеш-функции
        int index = Math.abs(hash1) % getSize();
        // Получение начального индекса в хеш-таблице
        while (table.get(index) != null && !table.get(index).getKey().equals(key)) {
        // Цикл для поиска элемента с ключом в хеш-таблице
            index = (index + Math.abs(hash2)) % getSize();
        // Переход к следующему индексу с использованием второй хеш-функции
        }
        if (table.get(index) != null && table.get(index).getKey().equals(key)) {
        // Проверка, найден ли элемент с заданным ключом
            return table.get(index).getValue();
        }
        // Возвращение значения элемента
        return null;
        // Возвращение null, если элемент не найден
    }

    private int hash2(K key) {
        int prime = 31;
        int hash = key.hashCode() % prime;
        return prime - hash;
    }

    private void createTable(int size) {
        table = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            table.add(null);
        }
    }
}