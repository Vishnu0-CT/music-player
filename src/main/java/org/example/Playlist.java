package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Playlist {
    static HashMap<String, ArrayList<Song>> playlistArr = new HashMap<String, ArrayList<Song>>();

    public static void createPlaylist(String playlistName) {
        playlistArr.put(playlistName, new ArrayList<Song>());
    }

    public static void addSongToPlaylist(String playlistName, String songName, String artistName) {
        for (Song song : MusicPlayer.songsList) {
            if (songName.equals(song.name) && artistName.equals(song.artist)) {
                playlistArr.get(playlistName).add(song);
                return;
            }
        }
        System.out.println("Song with the requested name and artist not found");
    }

    public static void playPlaylist(String playlistName) {
        for (Song song : playlistArr.get(playlistName)) {
            song.addListen();
        }
    }

    public static void deletePlaylist(String playlistName) {
        playlistArr.remove(playlistName);
    }

    public static void deleteSongFromPlaylist(String playlistName, String songName, String artistName) {
        for (Song song : playlistArr.get(playlistName)) {
            if (songName.equals(song.name) && artistName.equals(song.artist)) {
                playlistArr.get(playlistName).remove(song);
                return;
            }
        }
        System.out.println("Song with the requested name not found");
    }

    public static void importPlaylist(String filePath, String playlistName) {
        if (playlistArr.containsKey(playlistName)) {
            System.out.println("Playlist with the same name already exists");
            return;
        }

        if (filePath.startsWith("~")) {
            filePath = Paths.get(System.getProperty("user.home"), filePath.substring(1)).toString();
        }

        String delimiter;
        if (filePath.endsWith(".csv")) {
            delimiter = ",";
        } else if (filePath.endsWith(".tsv")) {
            delimiter = "\t";
        } else {
            System.out.println("Unsupported file type");
            return;
        }

        var playlist = new ArrayList<Song>();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String finalLine = line;
                executor.submit(() -> {
                    String[] parts = finalLine.split(delimiter);
                    synchronized (playlist) {
                        playlist.add(new Song(parts[0], parts[1]));
                    }
                });
            }
        } catch (Exception e) {
            System.out.println("File not found");
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!playlist.isEmpty()) {
            playlist.remove(0);
        }

        for (Song song : playlist) {
            MusicPlayer.addSong(song.name, song.artist);
        }

        playlistArr.put(playlistName, playlist);
    }

    public static void exportPlaylist(String playlistName, String fileName, String fileType) {
        if (!playlistArr.containsKey(playlistName)) {
            System.out.println("Playlist with the requested name not found");
            return;
        }

        String filePath = Paths.get(System.getProperty("user.home"), "Desktop/Training/java/music-player/music-player/src/main/resources", fileName + "." + fileType).toString();

        String delimiter;
        if (fileType.equals("csv")) {
            delimiter = ",";
        } else if (fileType.equals("tsv")) {
            delimiter = "\t";
        } else {
            System.out.println("Unsupported file type");
            return;
        }

        ExecutorService executor = Executors.newFixedThreadPool(5);

        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            pw.println("name" + delimiter + "artist");
            for (Song song : playlistArr.get(playlistName)) {
                executor.submit(() -> {
                    synchronized (pw) {
                        pw.println(song.name + delimiter + song.artist);
                    }
                });
            }

            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.MINUTES);

        } catch (Exception e) {
            System.out.println("Error writing to file");
            return;
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}