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
    public int fkBackground;
    String input = "";
    Scanner scanner = new Scanner(System.in);
    String sql;
    ResultSet result;

    public void play() throws SQLException {
        Timer timer = new Timer();
        System.out.println("Hello! \nWhat is your username? (3 letters, caps only)");
        input = scanner.nextLine();
        username = input;
        System.out.println("Greetings " + username + "!"); //CHECK IF THE USERNAME IS NEW
        SQL.prepareSQLConnections();

        sql = "SELECT * FROM Player \n" +
                "WHERE username='"+username+"';";
        result = stmt.executeQuery(sql);
        System.out.println(result);
        if (result.next() == false){ //player is new
            newPlayer();
            createNewPlayer();
        } else { //ALREADY EXISTS

        }
        /*while(result.next()) {

            result.getString    ("name");
            result.getInt       ("age");
            result.getBigDecimal("coefficient");

        } */
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

    public void createNewPlayer() throws SQLException {
        prepareSQLConnections();
        sql = "INSERT INTO `Background` (`backgroundID`, `favColor`, `darkModeOn`) VALUES ('', 'BLUE', '0')";
        stmt.execute(sql);
        sql = "SELECT * FROM Background \n" +
                "WHERE favColor='"+favColor+"' AND darkModeOn='"+darkMode+"';";
        result = stmt.executeQuery(sql);
        fkBackground = result.getInt("backgroundID");

    }

    public void newPlayer(){
        System.out.println("Welcome to The Waiting Game! \nThis game all you do is wait. \n To start off I need some info about you");
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
