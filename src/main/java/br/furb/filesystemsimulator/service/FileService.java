package br.furb.filesystemsimulator.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

@Service
public class FileService implements IFileService{
    @Override
    public void createNewFile(String name)  {
        File newFile = new File(name);
        try{
            int fileCount = 1;

            while(newFile.exists()){
                String[] fileNameParts = name.split(Pattern.quote("."));
                newFile = new File(fileNameParts[0] + "(" + fileCount + ")" + "." + fileNameParts[1]);
            }

            newFile.createNewFile();

        } catch (IOException e){
            System.out.println("ERROR creating new file: " + e);
        }

    }

    @Override
    public void deleteFile(String name) {
        File file = new File(name);
        if(file.exists()){
            file.delete();
        } else {
            System.out.println("ERROR: File not found!");
        }
    }
}
