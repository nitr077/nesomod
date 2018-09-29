package ru.nitrouz.nesomod.model.db;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created on 27.09.2018 by msgoryun .
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Game {
    private String crc;
    private String code;
    private String name;
    private String publisher;
    private String releaseDate;
    private int players;

    public String getCrc() {
        return crc;
    }

    public void setCrc(String crc) {
        this.crc = crc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
        this.players = players;
    }
}
