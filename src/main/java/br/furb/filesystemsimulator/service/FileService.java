package br.furb.filesystemsimulator.service;

import br.furb.filesystemsimulator.enums.LogColorEnum;
import br.furb.filesystemsimulator.utils.Utils;
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
            Utils.LogLn("ERROR - Creating new file!" + e, LogColorEnum.ERROR);
        }

    }

    @Override
    public void deleteFile(String name) {
        File file = new File(name);
        if(file.exists()){
            file.delete();
        } else {
            Utils.LogLn("ERROR - File not found!", LogColorEnum.ERROR);
        }
    }
}
