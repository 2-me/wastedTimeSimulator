package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQL {
    private static String url = "jdbc:mysql://127.0.0.1:8889/WaitingGame";
    private static String username = "2me";
    private static String password = "saint123";
    private static Connection connection;
    private static Statement stmt;

    public static void main(String[] args) throws SQLException {
        prepareSQLConnections();
        createDatabase();

    }

    public static void prepareSQLConnections() throws SQLException{
        connection = DriverManager.getConnection(url, username, password);
        stmt = connection.createStatement();
    }

    public static void insertNewPlayer(){

    }

    public ArrayList<String> getAllPlayers() throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement stmt = connection.createStatement();
        String sql = "SELECT * FROM Players WHERE";
        stmt.execute(sql);
        return null;
    }

    public static void createDatabase() throws SQLException {
    //    createBackgroundTable();
        // createPlayerTable();
        createSessionTable();
    }

    public static void createBackgroundTable() throws SQLException {
        prepareSQLConnections();
        String sql = "CREATE TABLE IF NOT EXISTS `Background` (\n" +
                "  `backgroundID` INT NOT NULL,\n" +
                "  `favColor` VARCHAR(45) NULL,\n" +
                "  `darkModeOn` TINYINT NULL,\n" +
                "  PRIMARY KEY (`backgroundID`),\n" +
                "  UNIQUE INDEX `backgroundID_UNIQUE` (`backgroundID` ASC))\n" +
                "ENGINE = InnoDB";
        stmt.execute(sql);
    }

    public static void createPlayerTable() throws SQLException {
        prepareSQLConnections();
        String sql = "CREATE TABLE IF NOT EXISTS `WaitingGame`.`Player` (\n" +
                "  `playerID` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `username` VARCHAR(45) NULL,\n" +
                "  `hoursPlayed` INT NULL,\n" +
                "  `minutesPlayed` INT NULL,\n" +
                "  `SecondsPlayed` INT NULL,\n" +
                "  `FK_Background` INT NULL,\n" +
                "  PRIMARY KEY (`playerID`),\n" +
                "  UNIQUE INDEX `playerID_UNIQUE` (`playerID` ASC),\n" +
                "  CONSTRAINT `FK_Background`\n" +
                "    FOREIGN KEY (`FK_Background`)\n" +
                "    REFERENCES `WaitingGame`.`Background` (`backgroundID`)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION)\n" +
                "ENGINE = InnoDB";
        stmt.execute(sql);
    }

    public static void createSessionTable() throws SQLException {
        prepareSQLConnections();
        String sql = "CREATE TABLE IF NOT EXISTS `Sessions` (\n" +
                "  `sessionsID` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `FK_Player` INT NULL,\n" +
                "  `Date` DATETIME NULL,\n" +
                "  `SessionTime` INT NULL,\n" +
                "  PRIMARY KEY (`sessionsID`),\n" +
                "  INDEX `FK_Player_idx` (`FK_Player` ASC)\n" +
                "  CONSTRAINT `FK_Player`\n" +
                "    FOREIGN KEY (`FK_Player`)\n" +
                "    REFERENCES `WaitingGame`.`Player` (`playerID`)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION)\n" +
                "ENGINE = InnoDB";
        System.out.println(sql);
        stmt.execute(sql);
    }



}
