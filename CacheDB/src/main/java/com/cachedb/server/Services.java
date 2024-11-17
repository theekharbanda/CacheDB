package com.cachedb.server;

import com.cachedb.server.constants.Constants;
import com.cachedb.server.models.UserContent;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

public class Services {

    private static Map<String,UserContent> DataBase = new ConcurrentHashMap<>();

    public static Map<String, UserContent> getDataBase() {
        return DataBase;
    }


    //Write a data of specific key to the file
    public static void writeToFile(String key) throws IOException {
        for (Map.Entry<String, UserContent> entry : DataBase.entrySet()) {
            FileOutputStream fs = new FileOutputStream(Constants.PathToDB + entry.getKey()+".bin",true);
            ObjectOutputStream writer = new ObjectOutputStream(fs);
            if(entry.getKey().equals(key)){
                writer.writeObject(entry.getKey() + "=" + entry.getValue());
            }
            writer.flush();
            fs.close();
            writer.close();
        }

    }

    //Writes all the data in DATABASE
    public static void writeToFile() throws IOException {

        for (Map.Entry<String, UserContent> entry : DataBase.entrySet()) {
            FileOutputStream fs = new FileOutputStream(Constants.PathToDB + entry.getKey()+".bin",true);
            ObjectOutputStream writer = new ObjectOutputStream(fs);
            writer.writeObject(entry.getKey() + "=" + entry.getValue());
            writer.flush();
            fs.close();
            writer.close();

        }

    }

    //Clears memory
    public static void clearMemory(){
        DataBase.clear();
    }

//    public static void loadFromDB() throws IOException {
//        // user123={userName='JohnDoe,', userData='SampleData'}
//        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.PathToDB))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split("=", 2);  // Split key and value
//                if (parts.length == 2) {
//                    String key = parts[0];
//                    String[] userDetails = parts[1].split(",", 2);  // Split name and email
//                    if (userDetails.length == 2) {
//                        User user = new User(userDetails[0].substring(11,18), userDetails[1].substring(11,21));
//                        DataBase.put(key, user);
//                    }
//                }
//            }
//        }
//    }


    //Modifies DATABASE
    public static void modifyDB(BiConsumer<Map<String, UserContent>, List<UserContent>> action, List<UserContent> userContent){
        action.accept(DataBase,userContent);
    }

    //DELETES key in DATABASE
    public static void deleteKey(BiConsumer<Map<String,UserContent>,List<String>> action, List<String> keys){
        action.accept(DataBase,keys);
    }

}
