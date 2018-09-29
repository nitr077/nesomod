package ru.nitrouz.nesomod.model.db;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Created on 27.09.2018 by msgoryun .
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DB {
    private List<Game> games;

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public Game getGameByCRC(String crc) {
        for (Game game : games) {
            if (game.getCrc().equals(crc)) return game;
        }
        return null;
    }
}
