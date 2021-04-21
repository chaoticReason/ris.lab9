package com.bsuir.stankevich.lab8.myutils;


import java.io.File;
import java.net.URL;

public class ManagerResource {
    final private ClassLoader classLoader = getClass().getClassLoader();

    public URL createUrl(String path){
        URL url = classLoader.getResource(path);
        if(url == null) throw new NullPointerException();
        return url;
    }

    public File createFile(String path){
        URL url = classLoader.getResource(path);
        if(url == null) throw new NullPointerException();
        return new File(url.getFile());
    }
}
