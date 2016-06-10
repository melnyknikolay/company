package net.nikmlk.companymanager.util;

public class Pair<Integer, String> {
    private Integer key;
    private String value;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Pair(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Pair() {
    }
}
