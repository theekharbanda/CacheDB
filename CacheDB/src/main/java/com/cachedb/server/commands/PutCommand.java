package com.cachedb.server.commands;

import com.cachedb.server.Services;
import com.cachedb.server.models.User;
import com.cachedb.server.models.UserContent;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;

public class PutCommand implements Callable<String> {
    private UserContent userContent;
    private List<UserContent> users = new ArrayList<>();

    //for mput command
    public PutCommand(String[] parts) {
        int expireTime = 300;
        if(parts[parts.length-2].equals("TTL")){
            expireTime= Integer.parseInt(parts[parts.length-1]);
        }
        this.users.add(new UserContent(  expireTime,
                parts[6]
                ,parts[4].replace(",","")
                ,parts[1]));
    }

    //For put command
    public PutCommand(String[] parts,boolean multiple){
        parseParts(parts);
    }

    private void parseParts(String[] parts) {
        int startingBracket = Arrays.asList(parts).indexOf("[");
        int TTL = 300;
        if(Objects.equals(parts[parts.length - 2], "TTL")) {
            TTL = Integer.parseInt((parts[parts.length-1]));
        }

        for(int i =startingBracket;i<parts.length;i++){

            if(parts[i].startsWith("{")){
                String key = parts[i-1];
                String username = parts[i+2].replace(",","");
                String userData = parts[i+4].replace(",","");
                UserContent user = new UserContent(TTL,userData,username,key);
                users.add(user);
            }
        }

    }


    @Override
    public String call() throws Exception, IOException {
        List<String> response = new ArrayList<>();

        BiConsumer<Map<String, UserContent>, List<UserContent>> action = (db, users) -> {
            users.forEach(user -> {
                db.put(user.getKey(), user);
                response.add("SUCCESS");
            });
        };

        Services.modifyDB(action, users);
        return String.join(",", response);
    }
}
