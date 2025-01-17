import java.util.*;
import java.util.HashMap;
import java.time.*;

public class MusicPlayer {

    static LocalDate day = LocalDate.now();

    public static class Song {
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
            if (this.history.containsKey(day)) {
                Integer newCount = this.history.get(day) + 1;
                this.history.put(day, newCount);
            }
            else {
                this.history.put(day, 1);
            }
        }
    }
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

    public static void main(String[] args) {
        MusicPlayer.addSong("A", "1");
        MusicPlayer.addSong("B", "2");
        MusicPlayer.addSong("C", "3");
        MusicPlayer.addSong("D", "4");
        MusicPlayer.addSong("E", "5");
        MusicPlayer.addSong("F", "6");
        MusicPlayer.addSong("G", "7");
        MusicPlayer.addSong("H", "8");
        MusicPlayer.addSong("I", "9");
        MusicPlayer.addSong("J", "10");
        MusicPlayer.addSong("K", "10");
        MusicPlayer.addSong("L", "10");
        MusicPlayer.addSong("M", "10");
        MusicPlayer.addSong("N", "10");
        MusicPlayer.addSong("O", "10");


        day = LocalDate.parse("2025-01-01");
        MusicPlayer.playSong("A");
        MusicPlayer.playSong("B", "2");
        MusicPlayer.playSong("A", "1");
        MusicPlayer.playSong("C");
        MusicPlayer.playSong("D");
        MusicPlayer.playSong("E");

        day = LocalDate.parse("2025-01-02");
        MusicPlayer.playSong("J", "10");
        MusicPlayer.playSong("J", "10");
        MusicPlayer.playSong("J", "10");
        MusicPlayer.playSong("K", "10");
        MusicPlayer.playSong("K", "10");
        MusicPlayer.playSong("L", "10");
        MusicPlayer.playSong("M", "10");
        MusicPlayer.playSong("N", "10");

        day = LocalDate.parse("2025-01-03");
        MusicPlayer.playSong("A");
        MusicPlayer.playSong("A");
        MusicPlayer.playSong("A");



//        Song out1 = songsList.get(0);
//        System.out.println("Song: " + out1.name + ", Artist: " + out1.artist + ", Listens: " + out1.listens);
//
//        Song out2 = songsList.get(1);
//        System.out.println("Song: " + out2.name + ", Artist: " + out2.artist + ", Listens: " + out2.listens);

//        var out3 = MusicPlayer.alltimeTopSongs();
//        Arrays.stream(out3).forEach(System.out::println);
//
//        var out4 = MusicPlayer.songsByArtist("10");
//        System.out.println(out4);

//        var out5 = MusicPlayer.artistTopSongs("10");
//        Arrays.stream(out5).forEach(System.out::println);

//        var out6 = MusicPlayer.unpopularSongs(0);
//        Arrays.stream(out6).forEach(System.out::println);

//        var out7 = MusicPlayer.dailyTopSongs(LocalDate.parse("2025-01-01"));
//        Arrays.stream(out7).forEach(System.out::println);
//
//        System.out.println();
//
//        var out8 = MusicPlayer.dailyTopSongs(LocalDate.parse("2025-01-02"));
//        Arrays.stream(out8).forEach(System.out::println);
//
//        System.out.println();
//
//        var out9 = MusicPlayer.dailyTopSongs(LocalDate.parse("2025-01-03"));
//        Arrays.stream(out9).forEach(System.out::println);
    }
}
