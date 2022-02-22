package com.company;

import java.util.Scanner;

public class game {

    public void play(){
        String input = "";
        Timer timer = new Timer();
        Scanner scanner = new Scanner(System.in);
        timer.start();
        System.out.println("What is your username? ()");
        input = scanner.nextLine();
        while(true) {
            if (!input.equals("")) {
                timer.stop();
                break;
            }
    }
    }
}
