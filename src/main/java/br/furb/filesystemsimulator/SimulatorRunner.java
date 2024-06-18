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
            input = keyboardsScanner.nextLine();
            System.out.print(">");
        }

        System.out.println("Bye!");
        keyboardsScanner.close();
    }

    private void executeCommand(String input){
        CommandEnum command = CommandEnum.fromString(input);

        if(command == CommandEnum.PWD){
            System.out.print(directoryService.getCurrentDir());
        }
    }
}
