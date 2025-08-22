package com.itgroup.bean;

public class Mp3list {
    private String id;
    private String title;
    private String artist;
    private String composer;
    private String lyrics;
    private String entertainment;
    private String mdate;
    private String lang;

    public Mp3list() {
    }

    public Mp3list(String id, String title, String artist, String composer, String lyrics, String entertainment, String mdate, String lang) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.composer = composer;
        this.lyrics = lyrics;
        this.entertainment = entertainment;
        this.mdate = mdate;
        this.lang = lang;
    }

    @Override
    public String toString() {
        return "Mp3list{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", composer='" + composer + '\'' +
                ", lyrics='" + lyrics + '\'' +
                ", entertainment='" + entertainment + '\'' +
                ", mdate='" + mdate + '\'' +
                ", lang='" + lang + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getEntertainment() {
        return entertainment;
    }

    public void setEntertainment(String entertainment) {
        this.entertainment = entertainment;
    }

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
