package br.furb.filesystemsimulator.service;

import org.springframework.stereotype.Component;

// responsavel pelas operações de diretorios
@Component
public interface IDirectoryService {
    void setCurrentDir(String currentDir);
    String getCurrentDir();
}
