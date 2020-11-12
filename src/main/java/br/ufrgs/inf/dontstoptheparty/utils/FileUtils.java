package br.ufrgs.inf.dontstoptheparty.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {
    public static String readTextFromTextFile(String path) throws IOException {
        return Files.readString(Path.of(path));
    }
}
