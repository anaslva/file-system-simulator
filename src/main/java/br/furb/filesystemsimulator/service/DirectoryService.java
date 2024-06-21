package br.furb.filesystemsimulator.service;

import br.furb.filesystemsimulator.enums.LogColorEnum;
import br.furb.filesystemsimulator.utils.Utils;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class DirectoryService implements IDirectoryService{
    private String SEPARATOR = "/";
    private final String ROOT = "C:" + SEPARATOR;
    private String currentDir = ROOT;

    @Override
    public void setCurrentDir(String currentDir) {
        String dirToBe = null;
        if(this.currentDir.equalsIgnoreCase(ROOT)){
            dirToBe = ROOT + currentDir;
        } else if(currentDir.contains("/")){
            dirToBe = currentDir;
        } else {
            dirToBe = this.currentDir + SEPARATOR + currentDir;
        }

        if(isValidDir(dirToBe)){
            this.currentDir = dirToBe;
        } else {
            Utils.LogLn("ERROR - Directory not found!", LogColorEnum.ERROR);
        }
    }

    @Override
    public String getCurrentDir() {
        return currentDir;
    }

    @Override
    public void list() {
        File dir = new File(this.currentDir);
        String[] content = dir.list();

        for(String c : content){
            System.out.println(c);
        }
    }

    @Override
    public void createDir(String name) {
        File newDir = new File(this.getCurrentDir() + SEPARATOR + name);
        newDir.mkdir();
    }

    @Override
    public void backDir() {
        if(ROOT.equalsIgnoreCase(currentDir)){
            return;
        }

        String[] dirs = currentDir.split(SEPARATOR);
        String parentDir = "";

        if(dirs.length == 2){
            parentDir = ROOT;
        } else {
            for(int i = 0; i < dirs.length-1; i++){

                if(i == 0){
                    parentDir = dirs[i];
                } else {
                    parentDir = parentDir + SEPARATOR + dirs[i];
                }

            }
        }

        setCurrentDir(parentDir);
    }

    @Override
    public String getSeparator() {
        return SEPARATOR;
    }

    @Override
    public long diskUsage(String name) {

        if(!isValidDir(name)){
            Utils.LogLn("ERROR - Directory/File not found!", LogColorEnum.ERROR);
        }

        File file = new File(name);
        if (file.isDirectory()) {
            return calculateDirectorySize(file);
        } else {
            return file.length();
        }
    }


    private boolean isValidDir(String dir){
        File existDir = new File(dir);
        return existDir.exists();
    }


    private static long calculateDirectorySize(File directory) {
        long size = 0;
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    size += calculateDirectorySize(file);
                } else {
                    size += file.length();
                }
            }
        }
        return size;
    }
}

