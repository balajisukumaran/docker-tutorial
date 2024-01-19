package com.container.one.services;

import com.container.one.dtos.ErrorInput;
import com.container.one.dtos.FileInfo;

import java.io.File;
import java.util.Scanner;

public class CleanService {
    public ErrorInput Validate(FileInfo fileInfo) {
        try {

            if (fileInfo.file()==null || fileInfo.file().trim().isEmpty() || fileInfo.file().trim().isBlank())
                return new ErrorInput(null, "Invalid JSON input.");

            File dir = new File("/usr/src/container1/");
            File[] files = dir.listFiles();
            boolean fileFound = false;
            boolean inCsv = true;
            for (File file : files) {
                String a = file.getName();
                if (file.getName().equals(fileInfo.file())) {
                    fileFound = true;
                    Scanner sc = new Scanner(file);
                    while (sc.hasNextLine()) {
                        String line = sc.nextLine();
                        if (!line.contains(",")) {
                            inCsv = false;
                            break;
                        }
                    }
                }
            }

            if (!fileFound)
                return new ErrorInput(fileInfo.file(), "File not found.");

            if (!inCsv)
                return new ErrorInput(fileInfo.file(), "Input file not in CSV format.");
        } catch (Exception e) {
            return new ErrorInput(fileInfo.file(), "Exception occurred.");
        }
        return null;
    }
}
