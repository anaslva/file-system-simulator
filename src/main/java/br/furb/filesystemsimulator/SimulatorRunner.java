package br.furb.filesystemsimulator;

import br.furb.filesystemsimulator.enums.CommandEnum;
import br.furb.filesystemsimulator.service.IDirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SimulatorRunner {

    @Autowired
    private IDirectoryService directoryService;
    // inicia console
    public void run(){
        System.out.println("Welcome to simulator");

        Scanner keyboardsScanner = new Scanner(System.in);
        String input = "";

        System.out.print(">");
        input = keyboardsScanner.nextLine();

        while(!"exit".equalsIgnoreCase(input)){
            executeCommand(input);
            System.out.print(">");
            input = keyboardsScanner.nextLine();

        }

        System.out.println("Bye!");
        keyboardsScanner.close();
    }

    private void executeCommand(String input){
        String inputCommand = input;
        String inputArg = null;

        if(inputCommand.contains(" ")){
            inputCommand = input.split(" ")[0];
            inputArg = input.split(" ")[1];
        }

        CommandEnum command = CommandEnum.fromString(inputCommand);

        if(command == CommandEnum.PWD){
            System.out.println(directoryService.getCurrentDir());
        } else if(command == CommandEnum.LIST){
            directoryService.list();
        } else if(command == CommandEnum.CHANGE_DIR){
            directoryService.setCurrentDir(inputArg);
        }else {
            System.out.println("Invalid command");
        }
    }
}
