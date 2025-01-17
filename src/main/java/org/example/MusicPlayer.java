package org.example;

import java.time.LocalDate;
import java.util.ArrayList;

public class MusicPlayer {

    static ArrayList<Song> songsList = new ArrayList<Song>();

    public static void addSong(String name, String artist) {
        Song song = new Song(name, artist);
        songsList.add(song);
    }

    public static void playSong(String songName) {
        for (Song song : songsList) {
            if (songName.equals(song.name)) {
                song.addListen();
                return;
            }
        }
        System.out.println("Song with the requested name not found");
    }

    public static void playSong(String songName, String artistName) {
        for (Song song : songsList) {
            if (songName.equals(song.name) && artistName.equals(song.artist)) {
                song.addListen();
                return;
            }
        }
        System.out.println("Song with the requested name and artist not found");
    }

    public static ArrayList<String> songsByArtist(String artistName) {
        ArrayList<String> songs = new ArrayList<String>();
        for (Song song : songsList) {
            if (artistName.equals(song.artist)) {
                songs.add(song.name);
            }
        }
        return songs;
    }

    public static String[] alltimeTopSongs() {
        songsList.sort((s1, s2) -> Integer.compare(s2.listens, s1.listens));
        String[] topSongs = new String[5];
        int counter = 0;
        for (Song song : songsList) {
            if (counter >= 5) {
                break;
            }
            topSongs[counter] = song.name + " - " + song.artist + " (" + song.listens + ")";
            counter++;
        }
        return topSongs;
    }

    public static String[] artistTopSongs(String artistName) {
        songsList.sort((s1, s2) -> Integer.compare(s2.listens, s1.listens));
        String[] topSongs = new String[5];
        int counter = 0;
        for (Song song : songsList) {
            if (counter >= 5) {
                break;
            }
            if (artistName.equals(song.artist)) {
                topSongs[counter] = song.name + " - " + song.artist + " (" + song.listens + ")";
                counter++;
            }
        }
        return topSongs;
    }

    public static String[] unpopularSongs(int count) {
        songsList.sort((s1, s2) -> Integer.compare(s1.listens, s2.listens));
        String[] bottomSongs = new String[5];
        int counter = 0;
        for (Song song : songsList) {
            if (counter >= 5) {
                break;
            }
            if (song.listens <= count) {
                bottomSongs[counter] = song.name + " - " + song.artist + " (" + song.listens + ")";
                counter++;
            }
        }
        return bottomSongs;
    }

    public static String[] dailyTopSongs(LocalDate targetDate) {
        songsList.sort((s1, s2) -> {
            Integer value1 = s1.history.getOrDefault(targetDate, 0);
            Integer value2 = s2.history.getOrDefault(targetDate, 0);
            return Integer.compare(value2, value1);
        });
        int counter = 0;
        String[] topDailySongs = new String[5];
        for (Song song : songsList) {
            if (counter >= 5) {
                break;
            }
            topDailySongs[counter] = song.name + " - " + song.artist + " (" + song.history.getOrDefault(targetDate, 0) + ")";
            counter++;
        }
        return topDailySongs;
    }

}
