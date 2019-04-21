package com.example.susanasantosmoreno.reproductordemusica;


public class Songs implements Comparable<Songs> {

    private long songId;
    private String songTitle;
    private String songArtist;
    private int songImage;
    private long songDuration;

    public Songs(long songId, String songTitle, String songArtist, int songImage,
                 long songDuration) {
        this.songId = songId;
        this.songTitle = songTitle;
        this.songArtist = songArtist;
        this.songImage = songImage;
        this.songDuration = songDuration;
    }

    public long getSongId() {
        return songId;
    }

    public void setSongId(long songId) {
        this.songId = songId;
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

    public long getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(long songDuration) {
        this.songDuration = songDuration;
    }

    @Override
    public int compareTo(Songs o) {
        return this.getSongTitle().compareTo(o.getSongTitle());
    }
}
