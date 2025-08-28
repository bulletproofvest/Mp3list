package com.itgroup.dao;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class SuperDao {
    public SuperDao() {}


    public Connection getConnection(){
        Connection conn = null;

        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String id = "sundori";
        String password = "hello1234";

        try {
            conn = DriverManager.getConnection(url, id, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

}
