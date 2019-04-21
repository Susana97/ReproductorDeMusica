package com.example.susanasantosmoreno.reproductordemusica;

import java.util.Comparator;

public class TitleCompare implements Comparator<Songs> {

    @Override
    public int compare(Songs o1, Songs o2) {
        return o1.getSongTitle().compareTo(o2.getSongTitle());
    }
}
