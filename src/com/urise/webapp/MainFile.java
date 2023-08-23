package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./src/ru/javawebinar/basejava");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Homework lesson 8
        String filePath2 = "D:\\Java\\basejava";
        list(filePath2);
    }

    public static void list(String filePath) {
        File dir = new File(filePath);
        String[] listDir = dir.list();
        if(listDir != null) {
            for (String s : listDir) {
                File file1 = new File(filePath + File.separator + s);
                if (file1.isDirectory()) {
                    list(filePath + File.separator + s);
                } else {
                    try {
                        System.out.println(file1.getCanonicalPath());
                    } catch (IOException e) {
                        throw new RuntimeException("Error", e);
                    }
                }
            }
        }
    }
}