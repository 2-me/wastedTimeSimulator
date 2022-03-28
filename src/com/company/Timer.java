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
        displayTimers();
    }

    public void displayTimers(){
        updateTimers();
        System.out.print(elapsedHours);
        if (elapsedMinutes < 10)
            System.out.print(":0" + elapsedMinutes);
        else System.out.print(":" + elapsedMinutes);
        if (secondsDisplay < 10)
            System.out.println(":0" + secondsDisplay);
        else System.out.println(":" + secondsDisplay);
    }

    long currSecond = 0;
    public void coolTimer(){
        if (currSecond < elapsedSeconds) {
            displayTimers();
            //System.out.print("\b\b\b\b\b\b\b");
            currSecond = elapsedSeconds;
        }


    }

    public int getSeconds(){
        updateTimers();
        return (int) elapsedSeconds;
    }

    public void updateTimers(){
        elapsedTime = System.currentTimeMillis() - startTime;
        elapsedSeconds = elapsedTime / 1000;
        secondsDisplay = elapsedSeconds % 60;
        elapsedMinutes = elapsedSeconds / 60;
        elapsedHours = elapsedMinutes / 60;
    }
}

