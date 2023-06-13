package org.benchmark;
import java.util.ArrayList;
import java.util.List;

public class ChainingHash<K, V> extends HashTable<K, V> {
    private List<List<Entry<K, V>>> table;

    public ChainingHash(HashFunc<K> hashFunction, int size) {
        this.size=size;
        createTable(size);
        setHashFunction(hashFunction, size);
    }

    @Override
    public void insert(K key, V value) {
        int hash = getHashFunction().hash(key);
        // Вычисление хеш-кода ключа
        int index = Math.abs(hash) % getSize();
        // Получение индекса ячейки хеш-таблицы
        List<Entry<K, V>> chain = table.get(index);
        // Получение списка элементов в ячейке таблицы
        for (Entry<K, V> entry : chain) {
        // Поиск элемента с заданным ключом в списке
            if (entry.getKey().equals(key)) {
        // Сравнение ключа элемента с заданным ключом
                entry.setValue(value);
        // Обновление значения элемента
                return;
            }
        }
        chain.add(new Entry<>(key, value));
        // Если элемент с заданным ключом не найден,
        // добавляем новый элемент в список
    }

    @Override
    public void remove(K key) {
        int hash = getHashFunction().hash(key);
        int index = Math.abs(hash) % getSize();

        List<Entry<K, V>> chain = table.get(index);
        for (Entry<K, V> entry : chain) {
            if (entry.getKey().equals(key)) {
                chain.remove(entry);
                return;
            }
        }
    }

    public V search(K key) {
        int hash = getHashFunction().hash(key);
        // Вычисление хеш-кода ключа
        int index = Math.abs(hash) % getSize();
        // Получение индекса ячейки хеш-таблицы
        List<Entry<K, V>> chain = table.get(index);
        // Получение списка элементов в ячейке таблицы
        for (Entry<K, V> entry : chain) {
        // Поиск элемента с заданным ключом в списке
            if (entry.getKey().equals(key)) {
        // Сравнение ключа элемента с заданным ключом
                return entry.getValue();
        // Возвращение значения элемента
            }
        }
        return null;
        // Возвращение null, если элемент не найден
    }

    private void createTable(int size) {
        table = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            table.add(new ArrayList<>());
        }
    }
}