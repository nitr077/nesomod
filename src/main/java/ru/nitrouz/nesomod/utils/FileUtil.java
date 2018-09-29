package ru.nitrouz.nesomod.utils;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import ru.nitrouz.nesomod.model.db.DB;
import ru.nitrouz.nesomod.model.lclassics.Lclassics;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.zip.CRC32;

/**
 * Created on 26.09.2018 by msgoryun .
 */
public class FileUtil {
    private static ObjectMapper mapper = new ObjectMapper();
    private static ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
    private static Lclassics lclassics;
    private final static File outputFile = new File("." + File.separator + "_output" + File.separator + "titles" + File.separator + "0100d870045b6000" + File.separator + "romfs" + File.separator + "titles" + File.separator + "lclassics.titlesdb");
    private final static File defaultFile = new File("." + File.separator + "bin" + File.separator + "lclassics.titlesdb");
    private final static File cacheFile = new File("." + File.separator + "cache" + File.separator + "null");
    private final static File tempFile = new File("." + File.separator + "temp" + File.separator + "null");

    public static Lclassics readLclassicsFromFile() {
        try {
            readLclassicsFromOutputFile();
        } catch (IOException e) {
            try {
                return mapper.readValue(readFromResourceFile("lclassics.titlesdb"), Lclassics.class);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return lclassics;
    }

    public static DB readDB() {
        try {
            return mapper.readValue(readFromResourceFile("db.json"), DB.class);
//            return mapper.readValue(new File(FileUtil.class.getClassLoader().getResource("db.json").getFile()), DB.class);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(new JFrame(), "При инициализации произошла ошибка!");
            e.printStackTrace();
            return null;
        }
    }

    public static void saveChanges(Lclassics lclassics) {
        try {
            writer.writeValue(outputFile, lclassics);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(new JFrame(), "При записи произошла ошибка!");
            e.printStackTrace();
        }
    }


    private static Lclassics readLclassicsFromOutputFile() throws IOException {
        lclassics = mapper.readValue(outputFile, Lclassics.class);
        return lclassics;
    }

    private static void copyDefaultFileToOutput() throws IOException {
        Files.copy(defaultFile.toPath(), outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    public static String getCRC(File file) throws IOException {
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        CRC32 crc = new CRC32();
        int cnt;
        int i = 0;
        while ((cnt = inputStream.read()) != -1) {
            if (i > 15) crc.update(cnt);
            i++;
        }
        return Long.toHexString(crc.getValue()).toUpperCase();
    }

    public static void createDefaultFolders() {
        outputFile.getParentFile().mkdirs();
        cacheFile.getParentFile().mkdirs();
        tempFile.getParentFile().mkdirs();
    }

    public static boolean addNewGames(List<String> newGames) {
        for (String game : newGames) {
            File outputNESFile = new File(outputFile.getParent() + File.separator + game + File.separator + game + ".nes");
            File inputNESFile = new File(tempFile.getParent() + File.separator + game + ".nes");
            outputNESFile.getParentFile().mkdirs();
            try {
                Files.move(inputNESFile.toPath(), outputNESFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(new JFrame(), "Не удалось скопировать " + game + ".nes!");
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static boolean removeDeleted(List<String> deletedGames) {
        for (String game : deletedGames) {
            File outputNESFile = new File(outputFile.getParent() + File.separator + game + File.separator + game + ".nes");
            try {
                deleteDirectory(outputNESFile.getParentFile());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(new JFrame(), "Не удалось удалить директорию с игрой!");
                e.printStackTrace();
            }
        }
        return true;
    }

    public static boolean changeImages(List<String> changedImages) {
        for (String game : changedImages) {
            File cachedImageFile = new File(cacheFile.getParent() + File.separator + game + ".jpg");
            File defaultCover = new File("."+File.separator+"bin"+File.separator+"nocover.jpg");
            if (!cachedImageFile.exists()) {
                try {
                    Files.copy(defaultCover.toPath(), cachedImageFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(new JFrame(), "Не удалось скопировать картинку!");
                    e.printStackTrace();
                    return false;
                }
            }
            try {
                final Process process = Runtime.getRuntime().exec("cmd /c \"start /WAIT bin" + File.separator + "convert.bat\" " + game);
                try {
                    final int exitVal = process.waitFor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(new JFrame(), "Не удалось запустить конвертатор (.\\bin\\convert.bat)!");
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }


    private static void deleteDirectory(File file) throws IOException {

        for (File childFile : file.listFiles()) {

            if (childFile.isDirectory()) {
                deleteDirectory(childFile);
            } else {
                if (!childFile.delete()) {
                    throw new IOException();
                }
            }
        }

        if (!file.delete()) {
            throw new IOException();
        }
    }

    private static String readFromResourceFile(String filename) {
        InputStream in = FileUtil.class.getClassLoader().getResourceAsStream(filename);
        StringBuilder sb = new StringBuilder();
        String line;

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "UTF-8"))) {
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
