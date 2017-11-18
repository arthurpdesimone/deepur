package com.ruiriot.deepur.adapter;

import com.ruiriot.deepur.model.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoriesAdapter {

    public static final List<Category> ITEMS = new ArrayList<>();

    private static final Map<String, Category> ITEM_MAP = new HashMap<>();

    private static final int COUNT = 25;

    static {

        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(Category item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static Category createDummyItem(int position) {
        return new Category(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore color information here.");
        }
        return builder.toString();
    }
}
