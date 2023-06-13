package org.benchmark;
import java.util.ArrayList;
import java.util.List;

public class DoubleHash<K, V> extends HashTable<K, V> {
    private List<Entry<K, V>> table;
    private final Entry<K, V> TOMBSTONE = new Entry<K, V> (null, null);

    public DoubleHash(HashFunc<K> hashFunction, int size) {
        this.size=size;
        createTable(size);
        setHashFunction(hashFunction, size);
    }

    @Override
    public void insert(K key, V value) {
        int hash1 = getHashFunction().hash(key);
        int hash2 = hash2(key);
        int index = Math.abs(hash1) % getSize();
        int index1 = index;
        while(table.get(index) != null && !table.get(index).getKey().equals(key) && table.get(index) != TOMBSTONE ) {
            index = (index + Math.abs(hash2)) % getSize();
            if (index1 == index) {
                index++;
                index1++;
            }
        }
        table.set(index, new Entry<>(key, value));
    }
    public V search(K key) {
        int hash1 = getHashFunction().hash(key);
        // Вычисление хеш-кода ключа с помощью первой хеш-функции
        int hash2 = hash2(key);
        // Вычисление хеш-кода ключа с помощью второй хеш-функции
        int index = Math.abs(hash1) % getSize();

        int index1 = index;
        // Получение начального индекса в хеш-таблице
        while (table.get(index) != null && (table.get(index) == TOMBSTONE || !table.get(index).getKey().equals(key))) {
        // Цикл для поиска элемента с ключом в хеш-таблице
            index = (index + Math.abs(hash2)) % getSize();
            if (index1 == index) {
                index++;
                index1++;
            }
        // Переход к следующему индексу с использованием второй хеш-функции
        }
        if (table.get(index) != null && table.get(index).getKey() != null && table.get(index).getKey().equals(key)) {
        // Проверка, найден ли элемент с заданным ключом
            return table.get(index).getValue();
        }
        // Возвращение значения элемента
        return null;
        // Возвращение null, если элемент не найден
    }
    @Override
    public void remove(K key) {
        int hash1 = getHashFunction().hash(key);
        int hash2 = hash2(key);
        int index = Math.abs(hash1) % getSize();
        int index1 = index;

        while (table.get(index) != null && (table.get(index) == TOMBSTONE || !table.get(index).getKey().equals(key))) {
            index = (index + Math.abs(hash2)) % getSize();
            if (index1 == index) {
                index++;
                index1++;
            }
        }

        if (table.get(index) != null && table.get(index).getKey() != null && table.get(index).getKey().equals(key)) {
            table.set(index, (Entry<K, V>) TOMBSTONE);
        }
    }

    private int hash2(K key) {
        int hash = key.hashCode() % getSize();
        if (hash != 0) {
            return hash;
        } else {
            hash++;
        }
        return hash;
    }

    private void createTable(int size) {
        table = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            table.add(null);
        }
    }
}