package com.cachedb.server.commands;

import com.cachedb.server.Services;
import com.cachedb.server.models.UserContent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class DelCommand implements Callable<String> {
    private List<String> keys = new ArrayList<>();

    //for del command
    public  DelCommand(String key) {
        this.keys.add(key);
    }

    //for mdel command
    public DelCommand(String[] parts){
        for (int i = 2; i < parts.length - 1; i++) {
            String key = parts[i].replace(",", "").trim();
            this.keys.add(key);
        }
    }


    @Override
    public String call() throws Exception {
        List<String> response = new ArrayList<>();
        BiConsumer<Map<String, UserContent>, List<String>> action = (db, keys) -> {
            for (String key : keys) {
                if (db.containsKey(key)) {
                    db.remove(key);
                    response.add("SUCCESS");
                } else {
                    response.add("SUCCESS");
                }
            }
        };
        Services.deleteKey(action, this.keys);
        return String.join(",", response);
    }
}
