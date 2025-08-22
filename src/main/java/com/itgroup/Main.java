package com.itgroup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

// CREATE USER sundori IDENTIFIED BY hello1234 DEFAULT TABLESPACE users TEMPORARY TABLESPACE temp;
//GRANT connect, resource TO sundori;
//ALTER USER sundori ACCOUNT UNLOCK;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DataManager manager = new DataManager();

        while(true){
            System.out.println("0: 종료, 1: 곡 검색, 2: 곡 추가, 3: 곡 수정, 4: 곡 삭제, 5: 곡 리스트, 6: 저장된 곡 수, 7: 나라별 곡 리스트, 8: 나라별 랜덤 추천곡 9: 북마크 리스트 ");
            System.out.print("번호 입력: ");

            int num = sc.nextInt();

            switch (num){
                case 0: // 종료
                    System.out.println("종료합니다.");
                    System.exit(0);
                    break;
                case 1: // 검색
                    manager.searchMusic();
                    break;
                case 2: // 추가
                    manager.insertMusic();
                    break;
                case 3: // 수정
                    manager.updataMusic();
                    break;
                case 4: // 삭제
                    manager.deleteMusic();
                    break;
                case 5: // 리스트
                    manager.selectAll();
                    break;
                case 6: // 곡 수 체크
                    manager.getSize();
                    break;
                case 7: // 나라별 리스트
                    manager.selectCountry();
                    break;
                case 8: // 나라별 랜덤 추천곡
                    manager.randomMusic();
                    break;
                case 9: // 북마크 리스트
                    manager.musicBookmark();
                    break;
            }

            System.out.println();
        }

    }
}