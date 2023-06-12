package org.benchmark;
import java.util.ArrayList;
import java.util.List;
public class LinearHash<K, V> extends HashTable<K, V> {
    private List<Entry<K, V>> table;

    public LinearHash(HashFunc<K> hashFunction, int size) {
        createTable(size);
        setHashFunction(hashFunction, size);
    }

    @Override
    public void insert(K key, V value) {
        int hash = getHashFunction().hash(key);
        int index = Math.abs(hash) % getSize();

        while (table.get(index) != null ) {//|| tombstone
            index = (index + 1) % getSize();
        }
        table.set(index, new Entry<>(key, value));
    }
    @Override
    public void remove(K key) {
        int hash = getHashFunction().hash(key);
        int index = Math.abs(hash) % getSize();

        while (table.get(index) != null && !table.get(index).getKey().equals(key)) {
            index = (index + 1) % getSize();
        }

        if (table.get(index) != null && table.get(index).getKey().equals(key)) {
            table.set(index, null);
        }
    }
    public V search(K key) {
        int hash = getHashFunction().hash(key);
        // Вычисление хеш-кода ключа
        int index = Math.abs(hash) % getSize();
        // Получение начального индекса в хеш-таблице

        // Цикл для поиска элемента с ключом в хеш-таблице
        while (table.get(index) != null && !table.get(index).getKey().equals(key)) {
            index = (index + 1) % getSize();
        // Переход к следующему индексу (открытая адресация)
        }
        // Проверка, найден ли элемент с заданным ключом
        if (table.get(index) != null && table.get(index).getKey().equals(key)) {
            return table.get(index).getValue(); // Возвращение значения элемента
        }
        return null;
        // Возвращение null, если элемент не найден
    }
    private void createTable(int size) {
        table = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            table.add(null);
        }
    }
}