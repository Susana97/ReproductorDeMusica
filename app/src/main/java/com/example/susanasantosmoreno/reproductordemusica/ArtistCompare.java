package com.example.susanasantosmoreno.reproductordemusica;

import java.util.Comparator;

public class ArtistCompare implements Comparator<Songs> {

    @Override
    public int compare(Songs o1, Songs o2) {
        return o1.getSongArtist().compareTo(o2.getSongArtist());
    }
}
