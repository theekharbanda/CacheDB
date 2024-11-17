package com.cachedb.server.commands;

import com.cachedb.server.Services;
import com.cachedb.server.models.User;
import com.cachedb.server.models.UserContent;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class GetCommand implements Callable<String> {
    String key;
    List<String> keys = new ArrayList<>();
    //for get command
    public GetCommand(String key){
        this.keys.add(key);
    }

    //for mget command
    public GetCommand(String[] parts){
        for (int i = 2; i < parts.length - 1; i++) {
            String key = parts[i].replace(",", "").trim();
            this.keys.add(key);
        }
    }


    @Override
    public String call() throws Exception {
        Map<String, UserContent> db = Services.getDataBase();
        List<String> response = keys.stream().map(key -> {
            if (db.containsKey(key)) {
                return db.get(key).getUserName(); // Directly return the username
            } else {
                return "UNDEFINED"; // Return "UNDEFINED" if the key doesn't exist
            }
        }).toList();
        return String.join(",", response);
    }
}
