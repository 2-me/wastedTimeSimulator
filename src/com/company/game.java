package com.company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;

import static com.company.SQL.prepareSQLConnections;
import static com.company.SQL.stmt;

public class game {
    Color color = new Color();
    public String username;
    public String favColor;
    public int darkMode;
    public int backgroundID;
    public int playerID;
    public int timerSeconds;
    String input = "";
    Scanner scanner = new Scanner(System.in);
    String sql;
    ResultSet result;

    public void play() throws SQLException {
        System.out.println("Hello! \nWhat is your username? (3 letters, caps only)");
        while (true){
            input = scanner.nextLine();
            username = input;
            if (isValid(username)) {
                System.out.println("Greetings " + username + "!"); //CHECK IF THE USERNAME IS NEW
                SQL.prepareSQLConnections();
                sql = "SELECT * FROM Player \n" +
                        "WHERE username='"+username+"';";
                result = stmt.executeQuery(sql);
                if (!result.next()){ //player is new
                    newPlayer();
                    createNewPlayer();
                    break;
                } else { //ALREADY EXISTS
                    getData();
                    if (impenetrableDefense()){ //player is real move on else stuck
                        break;
                    }
                }
            }
        }



        System.out.println("Would you like to start your waiting? (y or n)");
        theGame();
        System.out.print("Congrats ");
        favColorPrint(favColor,username);
        System.out.println("!");
        System.out.println("Your Total Seconds Wasted (TSW) has been updated");
        updatePlayerSeconds();
        documentSession();
        System.out.print("See you next time ");
        favColorPrint(favColor,":)");
    }

    public void documentSession() throws SQLException {
        prepareSQLConnections();
        sql = "INSERT INTO `sessions` (`sessionsID`, `FK_Player`, `Date`, `SessionTime`) VALUES (NULL, '"+playerID+"', CURRENT_TIMESTAMP, '"+timerSeconds+"')";
        stmt.execute(sql);
    }

    public void favColorPrint(String favColor, String print){
        if (favColor.equals("WHITE"))
            System.out.print(Color.WHITE_BOLD + print + Color.RESET);
        if (favColor.equals("RED"))
            System.out.print(Color.RED_BOLD + print + Color.RESET);
        if (favColor.equals("YELLOW"))
            System.out.print(Color.YELLOW_BOLD + print + Color.RESET);
        if (favColor.equals("GREEN"))
            System.out.print(Color.GREEN_BOLD + print + Color.RESET);
        if (favColor.equals("CYAN"))
            System.out.print(Color.CYAN_BOLD + print + Color.RESET);
        if (favColor.equals("BLUE"))
            System.out.print(Color.BLUE_BOLD + print + Color.RESET);
        if (favColor.equals("PURPLE"))
            System.out.print(Color.PURPLE_BOLD + print + Color.RESET);
        if (favColor.equals("BLACK"))
            System.out.print(Color.BLACK_BOLD + print + Color.RESET);
    }

    public boolean isValid(String username){
        for (int i=0; i<username.length();i++){
            if ( Character.isUpperCase(username.charAt(i))) {
            } else {
                System.out.println("Lets try again. What is your username? (3 letters, caps only)");
                return false;
            }
        }
        if (username.length() !=3) {
            System.out.println("Lets try again. What is your username? (3 letters, caps only)");
            return false;
        }
        return true;
    }

    public void getData() throws SQLException {
        playerID = result.getInt("playerID");
        backgroundID = result.getInt("FK_Background");
        sql = "SELECT * FROM background \n" +
                "WHERE backgroundID='"+backgroundID+"';";
        result = stmt.executeQuery(sql);
        if(result.next()){
            favColor = result.getString("favColor");
            darkMode = result.getInt("darkModeOn");
        }
    }

    public boolean impenetrableDefense() throws SQLException {
        while (true) {
            System.out.println(Color.RED_BOLD + username + Color.RESET +" is this really you? (y or n)");
            input = scanner.nextLine();
            if (input.equals("y")) {
                darkMode = 0;
                return true;
            } else if (input.equals("n")) {
                darkMode = 1;
                System.out.println("Lets try again. What is your username? (3 letters, caps only)");
                return false;
            }
        }
    }

    public void updatePlayerSeconds() throws SQLException {
        int oldTimerHours = 0;
        int oldTimerMinutes = 0;
        int oldTimerSeconds = 0;
        int newTimerHours = timerSeconds / 3600;
        int newTimerMinutes = (timerSeconds % 3600) / 60;
        int newTimerSeconds = (timerSeconds % 3600) % 60;
        sql = "SELECT * FROM player \n" +
                "WHERE playerID='"+playerID+"';";
        result = stmt.executeQuery(sql);
        if(result.next()){
            oldTimerHours = result.getInt("hoursPlayed");
            oldTimerMinutes = result.getInt("minutesPlayed");
            oldTimerSeconds = result.getInt("SecondsPlayed");
        }
        newTimerHours += oldTimerHours;
        newTimerMinutes += oldTimerMinutes;
        if (newTimerMinutes>60){
            newTimerMinutes %= 60;
            newTimerHours++;
        }
        newTimerSeconds += oldTimerSeconds;
        if (newTimerSeconds>60){
            newTimerSeconds %= 60;
            newTimerMinutes++;
        }
        sql = "UPDATE player\n" +
                "SET hoursPlayed = '"+ newTimerHours +"', minutesPlayed= '"+ newTimerMinutes +"', SecondsPlayed= '"+ newTimerSeconds +"' \n" +
                "WHERE playerID = "+playerID+";";
        stmt.execute(sql);
    }

    public void theGame(){
        Timer timer = new Timer();
        input = scanner.nextLine();
        input.toLowerCase(Locale.ROOT);
        if (input.equals("yes") || input.equals("y")) {
            timer.start();
            input = scanner.nextLine();
            while (true) {
                if (!input.equals("")) {
                    timer.stop();
                    timerSeconds = timer.getSeconds();
                    break;
                }
            }
        }
    }

    public void createNewPlayer() throws SQLException {
        prepareSQLConnections();
        sql = "INSERT INTO `background` (`backgroundID`, `favColor`, `darkModeOn`) VALUES (NULL, '"+favColor+"', '"+darkMode+"')";
        stmt.execute(sql);
        sql = "SELECT * FROM background \n" +
                "WHERE favColor='"+favColor+"' AND darkModeOn='"+darkMode+"';";
        result = stmt.executeQuery(sql);
        //System.out.println(result);
        if(result.next()){
            backgroundID = result.getInt(1);
        }
        sql = "INSERT INTO `player` (`playerID`, `username`, `hoursPlayed`, `minutesPlayed`, `SecondsPlayed`, `FK_Background`) VALUES (NULL, '"+username+"', '0', '0', '0', '"+backgroundID+"')";
        stmt.execute(sql);
        sql = "SELECT * FROM player \n" +
                "WHERE username='"+username+"' AND FK_Background='"+backgroundID+"';";
        result = stmt.executeQuery(sql);
        if(result.next()){
            playerID = result.getInt(1);
        }
    }

    public void newPlayer(){
        System.out.println("Welcome to The Waiting Game! \nThis game all you do is wait. But there are things that both of us need to know. \nTo start off I need some info about you");
        while (true){
            System.out.println("What is your favorite Color? ("+ Color.WHITE +"WHITE, "+ Color.RED + "RED, " + Color.YELLOW + "YELLOW, " + Color.GREEN + "GREEN, " + Color.CYAN + "CYAN, " + Color.BLUE + "BLUE, " + Color.PURPLE + "PURPLE, " + Color.BLACK +"BLACK " + Color.RESET + ")");
            input = scanner.nextLine();
            if (input.equals("WHITE") || input.equals("RED") || input.equals("YELLOW") || input.equals("GREEN") || input.equals("CYAN") || input.equals("BLUE") || input.equals("PURPLE") || input.equals("BLACK")){
                favColor = input;
                break;
            }
        }
        while (true) {
            System.out.println("Do you like dark mode? (y or n)");
            input = scanner.nextLine();
            if (input.equals("y")) {
                darkMode = 0;
                break;
            } else if (input.equals("n")) {
                darkMode = 1;
                break;
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
