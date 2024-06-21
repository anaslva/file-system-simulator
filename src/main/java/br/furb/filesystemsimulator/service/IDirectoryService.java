package br.furb.filesystemsimulator.service;

import org.springframework.stereotype.Component;

// responsavel pelas operações de diretorios
@Component
public interface IDirectoryService {
    void setCurrentDir(String currentDir);
    String getCurrentDir();
    // lista conteudo do diretório atual
    void list();
    void createDir(String name);
    void backDir();
    String getSeparator();
    long diskUsage(String name);
}
