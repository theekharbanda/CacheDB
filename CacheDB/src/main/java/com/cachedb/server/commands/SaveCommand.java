package com.cachedb.server.commands;

import com.cachedb.server.Services;
import com.cachedb.server.models.UserContent;

import java.util.Map;
import java.util.concurrent.Callable;

public class SaveCommand implements Callable<String> {
    private String key;

    public SaveCommand(String key){
        this.key = key;
    }
    @Override
    public String call() throws Exception {
        Map<String, UserContent> db = Services.getDataBase();
        if(db.containsKey(key)){
            Services.writeToFile(key);
            return "SUCCESS";
        }
        return "UNDEFINED";
    }
}
