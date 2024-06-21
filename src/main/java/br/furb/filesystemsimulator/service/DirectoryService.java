package br.furb.filesystemsimulator.service;

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
            System.out.println("\u001B[31m" + "Directory not found");
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


    private boolean isValidDir(String dir){
        File existDir = new File(dir);
        return existDir.exists();
    }
}


//TODO - esquema de cores para logs (criar enum com as corres ex: ERRO, ALERTA, DEFAULT)
