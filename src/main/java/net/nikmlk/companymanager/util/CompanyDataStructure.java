package net.nikmlk.companymanager.util;

public class CompanyDataStructure<K, V, T> {
    private K key;
    private V value;
    private T count;

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public T getCount() {
        return count;
    }

    public void setCount(T count) {
        this.count = count;
    }

    public CompanyDataStructure(K key, V value, T count) {
        this.key = key;
        this.value = value;
        this.count = count;
    }
}
