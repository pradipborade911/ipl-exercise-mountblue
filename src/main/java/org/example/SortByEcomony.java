package org.example;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;

class SortByEconomy implements Comparator<String> {
    SortedMap<String, Double> result;

    public SortByEconomy(SortedMap<String, Double> result) {
        this.result = result;
    }

    @Override
    public int compare(String s1, String s2) {
        return result.get(s1).compareTo(result.get(s2));
    }
}