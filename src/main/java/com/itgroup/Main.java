package com.itgroup;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DataManager manager = new DataManager();

        while(true){
            System.out.println("0: 종료, 1: 곡 검색, 2: 곡 추가, 3: 곡 수정, 4: 곡 삭제, 5: 곡 리스트, 6: 저장된 곡 수, 7: 나라별 곡 리스트, 8: 나라별 랜덤 추천곡 ");
            System.out.print("번호 입력: ");

            int num = sc.nextInt();
            sc.nextLine();

            switch (num){
                case 0:
                    System.out.println("종료합니다.");
                    System.exit(0);
                    break;
                case 1:
                    manager.searchMusic();
                    break;
                case 2:
                    manager.insertMusic();
                    break;
                case 3:
                    manager.updateMusic();
                    break;
                case 4:
                    manager.deleteMusic();
                    break;
                case 5:
                    manager.selectAll();
                    break;
                case 6:
                    manager.getSize();
                    break;
                case 7:
                    manager.selectCountry();
                    break;
                case 8:
                    manager.randomMusic();
                    break;
            }

            System.out.println();
        }

    }
}