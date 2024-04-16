package org.example.util;

/**
 *
 * Класс взаимодейстующий с именем файла
 *
 */
public class NameUtil {
    private static NameUtil instance;
    private String fileName;

    private NameUtil() {
    }

    public static NameUtil getInstance() {
        if (instance == null) {
            instance = new NameUtil();
        }

        return instance;
    }

    public String getName() {
        return fileName;
    }

    public void setName(String fileName) {
        this.fileName = fileName;
    }
}
