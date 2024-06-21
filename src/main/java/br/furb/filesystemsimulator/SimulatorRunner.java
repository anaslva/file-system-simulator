package br.furb.filesystemsimulator;

import br.furb.filesystemsimulator.enums.CommandEnum;
import br.furb.filesystemsimulator.enums.LogColorEnum;
import br.furb.filesystemsimulator.service.IDirectoryService;
import br.furb.filesystemsimulator.service.IFileService;
import br.furb.filesystemsimulator.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SimulatorRunner {

    @Autowired
    private IDirectoryService directoryService;
    @Autowired
    private IFileService fileService;
    // inicia console
    public void run(){

        Utils.LogWelcome();

        Scanner keyboardsScanner = new Scanner(System.in);
        String input = "";

        Utils.Log(directoryService.getCurrentDir() + ">", LogColorEnum.DEFAULT);

        input = keyboardsScanner.nextLine();

        while(!"exit".equalsIgnoreCase(input)){
            executeCommand(input);
            Utils.Log(directoryService.getCurrentDir() + ">", LogColorEnum.DEFAULT);
            input = keyboardsScanner.nextLine();

        }

        Utils.LogLn("Bye!!!", LogColorEnum.INFO);
        keyboardsScanner.close();
    }

    private void executeCommand(String input){
        String inputCommand = input;
        String inputArg = null;

        input = input.trim();
        if(inputCommand.contains(" ")){
            String[] parts = inputCommand.split("\\s+", 2);
            inputCommand = parts[0];
            if (parts.length > 1) {
                inputArg = parts[1];
            }
        }


        CommandEnum command = CommandEnum.fromString(inputCommand);

        if(command == CommandEnum.PWD){
            Utils.LogLn(directoryService.getCurrentDir(), LogColorEnum.DEFAULT);
        } else if(command == CommandEnum.LIST){
            directoryService.list();
        } else if(command == CommandEnum.CHANGE_DIR && isArgValid(inputArg)){
            directoryService.setCurrentDir(inputArg);
        } else if(command == CommandEnum.ADD_DIR && isArgValid(inputArg)){
            directoryService.createDir(inputArg);
        } else if(command == CommandEnum.BACK_DIR){
            directoryService.backDir();
        } else if(command == CommandEnum.ADD_FILE && isArgValid(inputArg)){
            String fileName = directoryService.getCurrentDir() + directoryService.getSeparator() + inputArg;
            fileService.createNewFile(fileName);
        } else if(command == CommandEnum.DELETE_FILE && isArgValid(inputArg)){
            String fileName = directoryService.getCurrentDir() + directoryService.getSeparator() + inputArg;
            fileService.deleteFile(fileName);
        } else if(command == CommandEnum.DISK_USAGE){
            String fileName = "";
            if(inputArg != null){
                fileName = directoryService.getCurrentDir() + directoryService.getSeparator() + inputArg;
            } else {
                fileName = directoryService.getCurrentDir();
            }

            Utils.LogLn(Utils.humanReadableByteCount(directoryService.diskUsage(fileName)), LogColorEnum.DEFAULT);
        } else {
            Utils.LogLn("ERROR - Invalid command!", LogColorEnum.ERROR);
        }
    }

    private boolean isArgValid(String arg){
        if(arg == null){
            return false;
        }
        return !arg.trim().equalsIgnoreCase("");
    }

}
