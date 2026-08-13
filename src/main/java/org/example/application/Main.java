package org.example.application;

import org.example.config.AppConfig;

public class Main {

    public static void main(String[] args) {
        AppConfig config = new AppConfig();
        ConsoleMenu consoleMenu = new ConsoleMenu(config);
        consoleMenu.executar();
    }
}
