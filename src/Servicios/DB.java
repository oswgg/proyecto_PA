package Servicios;

import java.sql.*;

public class DB {
    private String user = "root";
    private String pass = "rootOGG040520.gg";
    private String dbURL = "jdbc:mysql://localhost:3306/proyecto_integrador?useSSL=false";
    public Connection conn ;
    public Statement stmt;
    public PreparedStatement pstmt;

    public DB() {
        try {
            this.conn = DriverManager.getConnection(dbURL, user, pass);
            this.stmt = this.conn.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


}
