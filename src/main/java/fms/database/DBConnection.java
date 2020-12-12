package fms.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private Connection db;
    private String url = "jdbc:postgresql://127.0.0.1:5432/fms?user=postgres&password=postgres";

    public DBConnection() {

        try {
            Class.forName("org.postgresql.Driver");
            db = DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getDbConnection() {
        return this.db;
    }
}
