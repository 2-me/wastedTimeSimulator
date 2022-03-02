package com.company;

import java.util.Locale;
import java.util.Scanner;

public class game {
    public String username;

    public void play() {
        String input = "";
        Timer timer = new Timer();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! \nWhat is your username? (3 letters caps only)");
        input = scanner.nextLine();
        username = input;
        System.out.println("Greetings " + username + "!"); //CHECK IF THE USERNAME IS NEW
        System.out.println("Would you like to start your waiting? (y or n)");
        input = scanner.nextLine();
        input.toLowerCase(Locale.ROOT);
        if (input.equals("yes") || input.equals("y")) {
            timer.start();
            input = scanner.nextLine();
            while (true) {

                if (!input.equals("")) {
                    timer.stop();
                    break;
                }
            }
        }
    }

    public void playBasic() {
        String input = "";
        Timer timer = new Timer();
        Scanner scanner = new Scanner(System.in);
        timer.start();
        input = scanner.nextLine();
        while (true) {
            timer.coolTimer();
            if (!input.equals("")) {
                timer.stop();
                break;
            }
        }
    }
}
