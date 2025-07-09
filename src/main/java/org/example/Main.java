package org.example;

import org.example.services.ConsoleService;

public class Main {
    public static void main(String[] args) {
        ConsoleService consoleService = new ConsoleService();
        consoleService.start();
    }
}