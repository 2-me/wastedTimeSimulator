package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL {

    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:8889/DataStructuresTest";
        String username = "2me";
        String password = "saint123";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "CREATE TABLE IF NOT EXISTS `Wait-Game`.`Sessions` (\n" +
                "  `sessionsID` INT NOT NULL,\n" +
                "  `FK_Player` INT NULL,\n" +
                "  `Date` DATETIME NULL,\n" +
                "  `SessionTime` INT NULL,\n" +
                "  PRIMARY KEY (`sessionsID`),\n" +
                "  INDEX `FK_Player_idx` (`FK_Player` ASC) VISIBLE,\n" +
                "  CONSTRAINT `FK_Player`\n" +
                "    FOREIGN KEY (`FK_Player`)\n" +
                "    REFERENCES `Wait-Game`.`Player` (`playerID`)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION)\n" +
                "ENGINE = InnoDB";
        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
