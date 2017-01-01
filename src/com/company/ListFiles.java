package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class ListFiles {
    public static void main(String[] args) {
        final String dir = Global.path;
        ListFiles listFiles = new ListFiles();

        List<String> fileNames = new ArrayList<>();
        fileNames = listFiles.getFiles(dir, fileNames);

        for(String file : fileNames) {
            System.out.println(file);
        }
    }

    private List<String> getFiles(String dirString, List<String> fileNames) {
        final File dir = new File(dirString);
        File[] files = dir.listFiles((dir1, name) -> !name.startsWith("."));
        if(files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    fileNames.add(file.getName());
                } else if (file.isDirectory()) {
                    getFiles(file.getAbsolutePath(), fileNames);
                }
            }
        }
        fileNames.sort(new SortIgnoreCase());
        return fileNames;
    }

    public class SortIgnoreCase implements Comparator<String> {
        public int compare(String first, String second) {
            return first.toLowerCase().compareTo(second.toLowerCase());
        }
    }
}
