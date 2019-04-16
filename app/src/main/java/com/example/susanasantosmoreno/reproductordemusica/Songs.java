package com.example.susanasantosmoreno.reproductordemusica;

public class Songs implements Comparable<Songs>{

    private String songTitle;
    private String songArtist;
    private int songImage;

    public Songs(String songTitle, String songArtist, int songImage) {
        this.songTitle = songTitle;
        this.songArtist = songArtist;
        this.songImage = songImage;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public int getSongImage() {
        return songImage;
    }

    public void setSongImage(int songImage) {
        this.songImage = songImage;
    }

    @Override
    public int compareTo(Songs o) {
        return this.getSongTitle().compareTo(o.getSongTitle());
    }
}
