package com.company;
import java.util.concurrent.TimeUnit;

public class Timer {
    long startTime;
    long elapsedTime;
    long elapsedSeconds;
    long secondsDisplay;
    long elapsedMinutes;
    long elapsedHours;

    public void start(){
        startTime = System.currentTimeMillis();
    }

    public void stop(){
        updateTimers();
        displayTimers();
    }

    public void displayTimers(){
        System.out.print(elapsedHours);
        if (elapsedMinutes < 10)
            System.out.print(":0" + elapsedMinutes);
        else System.out.print(":" + elapsedMinutes);
        if (secondsDisplay < 10)
            System.out.println(":0" + secondsDisplay);
        else System.out.println(":" + secondsDisplay);
    }

    public void coolTimer(){
        int currSecond = 0;
        if (currSecond < elapsedSeconds) {
            System.out.print("\b\b\b\b\b\b\b");
            displayTimers();
            currSecond = (int) elapsedSeconds;
        }


    }

    public void updateTimers(){
        elapsedTime = System.currentTimeMillis() - startTime;
        elapsedSeconds = elapsedTime / 1000; // THE PROBLEM IS RIGHT HERE MISSING SEMI-COLON
        secondsDisplay = elapsedSeconds % 60;
        elapsedMinutes = elapsedSeconds / 60;
        elapsedHours = elapsedMinutes / 60;
    }
}

