package ru.bjcreslin.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


@Service
public class FileService {
    private static final String FILENAME = "rows.zip";

    /**
     * Считывает сохраненный файл
     * @return текст разархивированного файла
     */
    public String readBaseFile() {
        return zipToTXT(FILENAME);
    }

    /**
     * Сохраняет данные в Файл
     * @param txt -Содержание файла
     */
    public void saveBaseFile(String txt) {
        saveFileFromTxt(txt, FILENAME);
    }

    /**
     * Сохраняет ZIP файл с данными в txt
     * @param txt данные для записи
     * @param fileName  название ZIP архивного файла
     */
    void saveFileFromTxt(String txt, String fileName) {
        try (FileOutputStream fout = new FileOutputStream(fileName);
             ZipOutputStream zous = new ZipOutputStream(fout)) {
            zous.putNextEntry(new ZipEntry("rows.txt"));
            zous.write(txt.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Считывает zip файл с разархивироваием в String
     * @return результат разархивирования
     * @param fileName название архива
     */
    private String zipToTXT(String fileName) {
        StringBuilder textOut = new StringBuilder();
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(fileName));
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(zin))) {
            zin.getNextEntry();
            bufferedReader.lines().forEach(textOut::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return textOut.toString();
    }


}
