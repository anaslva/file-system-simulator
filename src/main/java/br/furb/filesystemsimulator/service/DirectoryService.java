package br.furb.filesystemsimulator.service;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class DirectoryService implements IDirectoryService{
    private final String ROOT = "C:/";
    private String currentDir = ROOT;
    @Override
    public void setCurrentDir(String currentDir) {
        String dirToBe = null;
        if(this.currentDir.equalsIgnoreCase(ROOT)){
            dirToBe = ROOT + currentDir;
            if(isValidDir(dirToBe)){
                this.currentDir = dirToBe;
            } else {
                System.out.println("\u001B[31m" + "Directory not found");
            }
        } else {
            dirToBe = this.currentDir + currentDir;
            if(isValidDir(dirToBe)){
                this.currentDir = dirToBe;
            } else {
                System.out.println("\u001B[31m" + "Directory not found");
            }
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


    private boolean isValidDir(String dir){
        File existDir = new File(dir);
        return existDir.exists();
    }
}


//TODO - esquema de cores para logs (criar enum com as corres ex: ERRO, ALERTA, DEFAULT)
