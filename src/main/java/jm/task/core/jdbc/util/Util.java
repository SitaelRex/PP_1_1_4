package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "sroot";
    private static final String PASSWORD = "root";

    public static Connection connectDatabase() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);

    }
}
