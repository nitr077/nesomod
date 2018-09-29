/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nitrouz.nesomod;

import ru.nitrouz.nesomod.utils.FileUtil;

import java.awt.BorderLayout;
import java.io.File;

/**
 * @author msgoryun
 */
public class Main {
    public static void main(String[] args) {
        FileUtil.createDefaultFolders();
        MainFrame f = new MainFrame();
        f.setLayout(new BorderLayout());
        final MainPanel p = new MainPanel();
        f.add(p, BorderLayout.CENTER);
        f.pack();
        f.setVisible(true);
    }

}
