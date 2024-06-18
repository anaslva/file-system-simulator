package br.furb.filesystemsimulator.enums;

public enum CommandEnum {

    PWD("pwd"),
    ADD_DIR("addir");

    private String commandName;

    private CommandEnum(String commandName){
        this.commandName = commandName;
    }

    public static CommandEnum fromString(String text){
        switch (text.toLowerCase()){
            case "pwd":
                return PWD;
            case "adddir":
                return ADD_DIR;
            default:
                return null; // retorna erro na aplicação
        }
    }
}
