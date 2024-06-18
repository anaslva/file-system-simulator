package br.furb.filesystemsimulator.service;

import org.springframework.stereotype.Service;

@Service
public class DirectoryService implements IDirectoryService{


    private final String ROOT = "C:/";
    private String currentDir = ROOT;
    @Override
    public void setCurrentDir(String currentDir) {
        this.currentDir = currentDir;
    }

    @Override
    public String getCurrentDir() {
        return currentDir;
    }
}
