package br.furb.filesystemsimulator.utils;

import br.furb.filesystemsimulator.enums.LogColorEnum;

public class Utils {

    public static void Log(String message, LogColorEnum color){
        System.out.print(color.getValue() + message + color.getValue());
    }

    public static void LogLn(String message, LogColorEnum color){
        System.out.println(color.getValue() + message + color.getValue());
    }

    public static void LogWelcome(){
        System.out.println(LogColorEnum.INFO.getValue() + " _       __     __                             " + LogColorEnum.INFO.getValue());
        System.out.println(LogColorEnum.INFO.getValue() + "| |     / /__  / /________  ____ ___  ___  ____ " + LogColorEnum.INFO.getValue());
        System.out.println(LogColorEnum.INFO.getValue() + "| | /| / / _ \\/ / ___/ __ \\/ __ `__ \\/ _ \\/ __ \\" + LogColorEnum.INFO.getValue());
        System.out.println(LogColorEnum.INFO.getValue() + "| |/ |/ /  __/ / /__/ /_/ / / / / / /  __/ / / /" + LogColorEnum.INFO.getValue());
        System.out.println(LogColorEnum.INFO.getValue() + "|__/|__/\\___/_/\\___/\\____/_/ /_/ /_/\\___/_/ /_/ " + LogColorEnum.INFO.getValue());

        System.out.println(" ");

    }

    public static String humanReadableByteCount(long bytes) {
        int unit = 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = "KMGTPE".charAt(exp - 1) + "i";
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
}
