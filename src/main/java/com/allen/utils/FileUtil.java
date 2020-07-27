package com.allen.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {

    public static void ifDirectoryNotExistThenCreated(String path){
        try{
            Files.createDirectories(Paths.get(path));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
