package com.ch.wchhuangya.dzah.android.enums;

/**
 * Created by wchya on 2016-12-01 20:18
 */

public enum AlbumSong {
    ALBUM_PUBLISH_TIME("ALBUM_PUBLISH_TIME"),
    ALBUM_SONGS_COUNT("ALBUM_SONGS_COUNT"),
    ALBUM_NAME("ALBUM_NAME"),
    ALBUM_PIC("ALBUM_PIC"),
    SONG_NAME("SONG_NAME"),
    SONG_LYRICS("SONG_LYRICS"),
    SONG_TUNE("SONG_TUNE"),
    SONG_ARRANGEMENT("SONG_ARRANGEMENT"),
    SONG_STATE("SONG_STATE"),
    SONG_STATE_LISTENED("SONG_STATE_LISTENED"),
    SONG_STATE_SINGED("SONG_STATE_SINGED"),
    SONG_STATE_UNSINGED("SONG_STATE_UNSINGED"),
    SONG_STATE_UNLISTENED("SONG_STATE_UNLISTENED");

    private String key;

    AlbumSong(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
