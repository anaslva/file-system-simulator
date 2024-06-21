package br.furb.filesystemsimulator.enums;

public enum CommandEnum {

    PWD("pwd"),
    ADD_DIR("addir"),
    LIST("list"),
    CHANGE_DIR("changedir"),
    BACK_DIR("backdir"),
    ADD_FILE("addfile"),
    DELETE_FILE("dltfile");

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
            case "list":
                return LIST;
            case "changedir":
                return CHANGE_DIR;
            case "backdir":
                return BACK_DIR;
            case "addfile":
                return ADD_FILE;
            case "dltfile":
                return DELETE_FILE;
            default:
                return null; // retorna erro na aplicação
        }
    }
}
