package br.furb.filesystemsimulator;

import br.furb.filesystemsimulator.enums.CommandEnum;
import br.furb.filesystemsimulator.service.IDirectoryService;
import br.furb.filesystemsimulator.service.IFileService;
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
        System.out.println("Welcome to simulator");

        Scanner keyboardsScanner = new Scanner(System.in);
        String input = "";

        System.out.print(directoryService.getCurrentDir() + ">");
        input = keyboardsScanner.nextLine();

        while(!"exit".equalsIgnoreCase(input)){
            executeCommand(input);
            System.out.print(directoryService.getCurrentDir() + ">");
            input = keyboardsScanner.nextLine();

        }

        System.out.println("Bye!");
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
            System.out.println(directoryService.getCurrentDir());
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
        } else {
            System.out.println("Invalid command");
        }
    }

    private boolean isArgValid(String arg){
        if(arg == null){
            return false;
        }
        return !arg.trim().equalsIgnoreCase("");
    }
}
