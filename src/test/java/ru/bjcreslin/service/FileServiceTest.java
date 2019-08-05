package ru.bjcreslin.service;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class FileServiceTest {
    private FileService fileService;

    @BeforeEach
    void setUp() {
        fileService = new FileService();
    }

    @Test
    void readFile() {
        String txt = fileService.readBaseFile();
        Assert.assertFalse(txt.isEmpty());
    }


    @Test
    void saveFileFromTxt() {
        String txt = "test";
        String fileName = "rows1.zip";
        if (Files.exists(Paths.get(fileName))) {
            try {
                Files.delete(Paths.get(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        fileService.saveFileFromTxt(txt, fileName);
        Assert.assertTrue(Files.exists(Paths.get(fileName)));
    }
}