package ru.netology;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

public class Main {
    static StringBuilder log = new StringBuilder();

    public static void main(String[] args) throws IOException {
        installGame();
    }

    private static void installGame() {
        createDir("D:\\Games", "src");
        createDir("D:\\Games", "res");
        createDir("D:\\Games", "savegames");

        createDir("D:\\Games\\src", "main");
        createDir("D:\\Games\\src", "test");

        createDir("D:\\Games\\res", "drawables");
        createDir("D:\\Games\\res", "vectors");
        createDir("D:\\Games\\res", "icons");

        try {
            createFile("D:\\Games\\src\\main", "Main.java");
            createFile("D:\\Games\\src\\main", "Utils.java");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        createDir("D:\\Games", "temp");

        createLog();
    }

    private static void createDir(String path, String name) {
        File dir = new File(path, name);

        if (dir.mkdir()) {
            successLog(dir, name);
        } else {
            failLog(dir, name);
        }
    }

    private static void createFile(String path, String name) throws IOException {
        File dir = new File(path, name);

        if (dir.createNewFile()) {
            successLog(dir, name);
        } else {
            failLog(dir, name);
        }
    }

    public static void successLog(File file, String fileName) {
        if (file.isDirectory()) {
            LocalDateTime currentTime = LocalDateTime.now();
            log.append("INFO [" + currentTime + "] Директория \"" + fileName + "\" успешно создана!\n");
        } else if (file.isFile()) {
            LocalDateTime currentTime = LocalDateTime.now();
            log.append("INFO [" + currentTime + "] Файл \"" + fileName + "\" успешно создан!\n");
        }
    }

    public static void failLog(File file, String fileName) {
        if (file.isDirectory()) {
            LocalDateTime currentTime = LocalDateTime.now();
            log.append("ERROR [" + currentTime + "] Ошибка при создании директории \"" + fileName + "\"!\n");
        } else if (file.isFile()) {
            LocalDateTime currentTime = LocalDateTime.now();
            log.append("ERROR [" + currentTime + "] Ошибка при создании файла \"" + fileName + "\"!\n");
        }
    }

    public static void createLog() {
        String logString = log.toString();

        try (FileOutputStream fos = new FileOutputStream("D:\\Games\\temp\\temp.txt", false)) {
            byte[] bytes = logString.getBytes();
            fos.write(bytes, 0, bytes.length);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}