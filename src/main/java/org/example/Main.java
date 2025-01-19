package org.example;

import java.time.LocalDate;
import java.util.Arrays;

public class Main {
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

        Date.dateSetter("2025-01-01");
        MusicPlayer.playSong("A");
        MusicPlayer.playSong("B", "2");
        MusicPlayer.playSong("A", "1");
        MusicPlayer.playSong("C");
        MusicPlayer.playSong("D");
        MusicPlayer.playSong("E");

        Date.dateSetter("2025-01-02");
        MusicPlayer.playSong("J", "10");
        MusicPlayer.playSong("J", "10");
        MusicPlayer.playSong("J", "10");
        MusicPlayer.playSong("K", "10");
        MusicPlayer.playSong("K", "10");
        MusicPlayer.playSong("L", "10");
        MusicPlayer.playSong("M", "10");
        MusicPlayer.playSong("N", "10");

        Date.dateSetter("2025-01-03");
        MusicPlayer.playSong("A");
        MusicPlayer.playSong("A");
        MusicPlayer.playSong("A");

//        Song out1 = MusicPlayer.songsList.get(0);
//        System.out.println("Song: " + out1.name + ", Artist: " + out1.artist + ", Listens: " + out1.listens);
//
//        Song out2 = MusicPlayer.songsList.get(1);
//        System.out.println("Song: " + out2.name + ", Artist: " + out2.artist + ", Listens: " + out2.listens);
//
//        var out3 = MusicPlayer.alltimeTopSongs();
//        Arrays.stream(out3).forEach(System.out::println);
//
//        var out4 = MusicPlayer.songsByArtist("10");
//        System.out.println(out4);
//
//        var out5 = MusicPlayer.artistTopSongs("10");
//        Arrays.stream(out5).forEach(System.out::println);
//
//        var out6 = MusicPlayer.unpopularSongs(0);
//        Arrays.stream(out6).forEach(System.out::println);
//
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

        Playlist.importPlaylist("~/Desktop/Training/java/music-player/music-player/src/main/resources/p1.csv", "playlist1");
        System.out.println(Playlist.playlistArr.get("playlist1").get(1).artist);

        Playlist.exportPlaylist("playlist1", "p2", "csv");
        Playlist.exportPlaylist("playlist1", "p3", "tsv");
    }
}