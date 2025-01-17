package org.example;
import java.time.LocalDate;
import java.util.HashMap;

public class Song {
    String name;
    String artist;
    int listens;
    HashMap<LocalDate, Integer> history;

    Song (String name, String artist) {
        this.name = name;
        this.artist=artist;
        this.listens=0;
        this.history = new HashMap<LocalDate, Integer>();
    }

    void addListen() {
        this.listens++;
        if (this.history.containsKey(Date.dateGetter())) {
            Integer newCount = this.history.get(Date.dateGetter()) + 1;
            this.history.put(Date.dateGetter(), newCount);
        }
        else {
            this.history.put(Date.dateGetter(), 1);
        }
    }
}
