package com.cachedb.server.Expiration;


import com.cachedb.server.Services;
import com.cachedb.server.models.UserContent;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Expiration {

    public Expiration() {

        // Create a scheduled executor service with a daemon thread
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = Executors.defaultThreadFactory().newThread(r);
            t.setDaemon(true);  // Set thread as daemon
            return t;
        });

        // Schedule the task to run every 5 seconds (or any interval of your choice)
        scheduler.scheduleAtFixedRate(this::removeExpiredEntries, 0, 10, TimeUnit.SECONDS);
    }

    // Method to remove expired entries
    private void removeExpiredEntries() {
        Map<String, UserContent> db = Services.getDataBase();
        LocalDateTime now = LocalDateTime.now();

        for (Map.Entry<String, UserContent> entry : db.entrySet()) {
            if (entry.getValue().isExpired()) {
                db.remove(entry.getKey());
            }
        }

        try {
            Services.writeToFile();  // Persist the changes
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
