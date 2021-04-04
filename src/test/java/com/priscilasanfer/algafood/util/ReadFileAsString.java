package com.priscilasanfer.algafood.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFileAsString {

    public static String readFileAsString(String file) {
        try {
            return new String(Files.readAllBytes(Paths.get(file)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
