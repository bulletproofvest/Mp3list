package com.itgroup.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SuperDao {
    public SuperDao() {}


    public Connection getConnection(){  // connection : 데이터베이스 연결 객체
        Connection conn = null;

        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String id = "sundori";
        String password = "hello1234";

        try {
            conn = DriverManager.getConnection(url, id, password); // 자바가 제공하는 DriverManager 클래스 안에 있는 getConnection() 메소드를 이용하여 url, id, password 정보로 데이터베이스 연결
        } catch (SQLException e) {// JDBC 작업 중 발생하는 예외로, 주로 데이터베이스와의 통신 오류가 났을 때 발생
            throw new RuntimeException(e); // 걍 던져서 내보냄 > 예외는 꼭 처리해야 하는데 그냥 가볍게 만들어서 던지거 ㅇㅇ
        }
        return conn;
    }
}
