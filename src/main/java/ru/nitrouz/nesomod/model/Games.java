package ru.nitrouz.nesomod.model;

import ru.nitrouz.nesomod.model.db.DB;
import ru.nitrouz.nesomod.model.db.Game;
import ru.nitrouz.nesomod.model.lclassics.Lclassics;
import ru.nitrouz.nesomod.model.lclassics.Title;
import ru.nitrouz.nesomod.utils.FileUtil;
import ru.nitrouz.nesomod.utils.ImageUtils;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Games {
    private DefaultListModel<String> model = new DefaultListModel<>();
    private Map<String, Title> map = new TreeMap<>();
    private Title selectedTitle;
    private Lclassics lclassics = FileUtil.readLclassicsFromFile();

    private List<String> addedGames = new ArrayList<>();
    private List<String> deletedGames = new ArrayList<>();
    private List<String> imageChanged = new ArrayList<>();
    private List<String> defaultGames = new ArrayList<>();

    private DB db;
    private Game selectedGame;

    //Default game values
    private String copyright = "Added by NES online modding tool by @nitr077";
    private List<Integer> overscan = new ArrayList<>();
    private Integer volume = 74;
    private String armetVersion = "v1";
    private Integer saveCount = 0;
    private boolean simultaneous = true;
    private List<Integer> fadein = new ArrayList<>();
    private Integer armetThreshold = 85;
    private String sysdate;
    private String name = "Unknown";
    private String publisher = "Unknown";
    private String code;
    private String rom;
    private String releaseDate = "1970-01-01";
    private Integer players = 1;
    private String cover;
    private boolean success = false;

    public Games() {
        createDefaultGamesList();
        setDefaultGameValues();
        for (Title t : lclassics.getTitles()) {
            map.put(t.getTitle(), t);
        }
        db = FileUtil.readDB();
        redrawList();
    }

    public DefaultListModel<String> getModel() {
        return model;
    }

    public Title getTitleByName(String name) {
        return map.get(name);
    }

    public void saveChanges() {
        lclassics.setTitles(new ArrayList<>(map.values()));
        if(!addedGames.isEmpty() || !deletedGames.isEmpty() || !imageChanged.isEmpty()) {
            if (success = FileUtil.addNewGames(addedGames)) addedGames.clear();
            if (success = FileUtil.removeDeleted(deletedGames)) deletedGames.clear();
            if (success = FileUtil.changeImages(imageChanged)) imageChanged.clear();
            FileUtil.saveChanges(lclassics);
            if (success) JOptionPane.showMessageDialog(null, "Success!");
        }
    }

    public void changeImage(File selectedImage) {
        ImageUtils.resizeImage(selectedImage, selectedTitle.getCode());
        if (!imageChanged.contains(selectedTitle.getCode())) imageChanged.add(selectedTitle.getCode());
    }

    public void deleteSelected(String game) {
        String code = map.get(game).getCode();
        map.remove(game);
        if (!deletedGames.contains(code)) deletedGames.add(code);
        redrawList();
    }

    private void redrawList() {
        model.clear();
        for (String s : map.keySet()) {
            if (!defaultGames.contains(s)) model.addElement(s);
        }
    }

    private void createDefaultGamesList() {
        defaultGames.add("Balloon Fight™");
        defaultGames.add("Baseball");
        defaultGames.add("Donkey Kong™");
        defaultGames.add("Double Dragon");
        defaultGames.add("Dr. Mario™");
        defaultGames.add("Excitebike™");
        defaultGames.add("Ghosts'n Goblins™");
        defaultGames.add("Gradius");
        defaultGames.add("Ice Climber™");
        defaultGames.add("Ice Hockey");
        defaultGames.add("Mario Bros.™");
        defaultGames.add("Pro Wrestling");
        defaultGames.add("River City Ransom");
        defaultGames.add("Soccer");
        defaultGames.add("Super Mario Bros.™");
        defaultGames.add("Super Mario Bros.™ 3");
        defaultGames.add("Tecmo Bowl");
        defaultGames.add("Tennis");
        defaultGames.add("The Legend of Zelda™");
        defaultGames.add("Yoshi™");
    }

    private void setDefaultGameValues() {
        overscan.add(0);
        overscan.add(0);
        overscan.add(9);
        overscan.add(3);
        fadein.add(3);
        fadein.add(2);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        sysdate = dateFormat.format(date);
    }

    public void addNewGame(File selectedFile) {
        try {
            String crc = FileUtil.getCRC(selectedFile);
            selectedGame = db.getGameByCRC(crc);
            if (selectedGame != null) {
                if (selectedGame.getCode() == null) selectedGame.setCode(crc);
                name = selectedGame.getName();
                publisher = selectedGame.getPublisher();
                code = selectedGame.getCode();
                releaseDate = selectedGame.getReleaseDate();
                players = selectedGame.getPlayers();
            } else {
                code = crc;
                JOptionPane.showMessageDialog(null, "Couldn't find game info!");
            }
            rom = "/titles/" + code + "/" + code + ".nes";
            cover = "/titles/" + code + "/" + code + ".xtx.z";
            selectedTitle = new Title();
            selectedTitle.setSortTitle(name);
            selectedTitle.setPublisher(publisher);
            selectedTitle.setCode(code);
            selectedTitle.setRom(rom);
            selectedTitle.setCopyright(copyright);
            selectedTitle.setTitle(name);
            selectedTitle.setVolume(volume);
            selectedTitle.setReleaseDate(releaseDate);
            selectedTitle.setPlayersCount(players);
            selectedTitle.setCover(cover);
            selectedTitle.setOverscan(overscan);
            selectedTitle.setArmetVersion(armetVersion);
            selectedTitle.setLcla6ReleaseDate(sysdate);
            selectedTitle.setSaveCount(saveCount);
            selectedTitle.setSimultaneous(simultaneous);
            selectedTitle.setFadein(fadein);
            selectedTitle.setDetailsScreen("");
            selectedTitle.setArmetThreshold(armetThreshold);
            selectedTitle.setSortPublisher(publisher);
            map.put(name, selectedTitle);
            if (!addedGames.contains(selectedTitle.getCode())) addedGames.add(selectedTitle.getCode());
            if (!imageChanged.contains(selectedTitle.getCode())) imageChanged.add(selectedTitle.getCode());
            File outputNes = new File("." + File.separator + "temp" + File.separator + selectedTitle.getCode() + ".nes");
            Files.copy(selectedFile.toPath(), outputNes.toPath(), StandardCopyOption.REPLACE_EXISTING);
            redrawList();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "There was an error while opening file!");
            e.printStackTrace();
        }
    }

    public void setSelectedTitle(String selectedValue) {
        this.selectedTitle = getTitleByName(selectedValue);
    }

    public Integer getPlayersCount() {
        return selectedTitle.getPlayersCount();
    }

    public String getName() {
        return selectedTitle.getTitle();
    }

    public String getReleaseDate() {
        return selectedTitle.getReleaseDate();
    }

    public String getPublisher() {
        return selectedTitle.getPublisher();
    }

    public String getCode() {
        return selectedTitle.getCode();
    }

    public String commit(String selected, String name, String publisher, String releaseDate, Integer players) {
        try {
            Title temp = map.get(selected);
            if (!name.equals(temp.getTitle()) || !publisher.equals(temp.getPublisher()) || !releaseDate.equals(temp.getReleaseDate()) || !players.equals(temp.getPlayersCount())) {
                temp.setPublisher(publisher);
                temp.setReleaseDate(releaseDate);
                temp.setPlayersCount(players);
                map.remove(temp.getTitle());
                temp.setTitle(name);
                map.put(name, temp);
                redrawList();
            }
            return temp.getTitle();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Couldn't commit changes!");
            e.printStackTrace();
        }
        return null;
    }
}
