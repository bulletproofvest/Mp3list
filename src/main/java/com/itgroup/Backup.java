package com.itgroup;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Backup {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
    }
}

/*

package com.itgroup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// CREATE USER sundori IDENTIFIED BY hello1234 DEFAULT TABLESPACE users TEMPORARY TABLESPACE temp;
//GRANT connect, resource TO sundori;
//ALTER USER sundori ACCOUNT UNLOCK;

public class Main {
    public static void main(String[] args) {
        //아래 부분 내껄로 바꾸면 댐
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        // JDBC 접속 정보
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String driver = "oracle.jdbc.driver.OracleDriver";
        String id = "sundori" ;
        String password = "hello1234" ;
        try {
            // 1. 드라이버 로드
            Class.forName(driver);

            // 2. 연결
            conn = DriverManager.getConnection(url, id, password);

            // 3. Statement 생성
            stmt = conn.createStatement();

            // 4. 현재 시간 조회
            // String sql = "SELECT 5 + 3 * 4 FROM dual";
            String sql = "SELECT upper('hello') as upp, power(2,3) as pow FROM dual";

            rs = stmt.executeQuery(sql);

            // 5. 결과 출력
            if (rs.next()) {
                // System.out.println("계산 결과 : " + rs.getString(1));
                System.out.println("대문자: " + rs.getString(1));
                System.out.println("2의 3제곱: " + rs.getString(2));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6. 리소스 정리
            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
            try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
        }
    }
}
*/