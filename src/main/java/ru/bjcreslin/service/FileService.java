package ru.bjcreslin.service;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FileService {
    private static final String filename = "rows.zip";


    public String readBaseFile() {
        return zipToTXT(filename);
    }

    public void saveBaseFile(String txt) {
        saveFileFromTxt(txt, filename);
    }


    void saveFileFromTxt(String txt, String fileName) {
        try (FileOutputStream fout = new FileOutputStream(fileName);
             ZipOutputStream zous = new ZipOutputStream(fout)) {
            zous.putNextEntry(new ZipEntry("rows.txt"));
            zous.write(txt.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String zipToTXT(String filename) {
        StringBuilder textOut = new StringBuilder();
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(filename));
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(zin))) {
            zin.getNextEntry();
            bufferedReader.lines().forEach(textOut::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return textOut.toString();


    }
}
