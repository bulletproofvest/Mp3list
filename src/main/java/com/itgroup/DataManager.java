package com.itgroup;

import com.itgroup.bean.Mp3list;
import com.itgroup.dao.Mp3listDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DataManager {
    private Mp3listDao mdao = null;
    private Scanner sc = null;

    public DataManager(){
        this.mdao = new Mp3listDao();
        this.sc = new Scanner(System.in);
    }

    public void searchMusic() {
        System.out.print("찾고자 하는 곡명을 적어주세요: ");
        String findTitle = sc.nextLine().trim();

        Mp3list song = mdao.searchMusic(findTitle);

        if(song == null){
            System.out.println("해당 곡을 찾지 못했습니다.");
        } else {
            System.out.println("<" + song.getTitle() + ">");
            System.out.println("아티스트/작곡가/작사가: " + song.getArtist() + "/" + song.getComposer() + "/" + song.getLyrics());
            System.out.println("소속사: " + song.getEntertainment());
            System.out.println("음원 발매일: " + song.getMdate());
        }
    }

    public void insertMusic() {
        Mp3list bean = new Mp3list();
        int cnt = -1;

        System.out.print("곡 번호 입력: ");
        bean.setNo(sc.nextInt());
        sc.nextLine();

        System.out.print("곡명 입력: ");
        bean.setTitle(sc.nextLine());

        System.out.print("아티스트 입력: ");
        bean.setArtist(sc.nextLine());

        System.out.print("작곡가 입력: ");
        bean.setComposer(sc.nextLine());

        System.out.print("작사가 입력: ");
        bean.setLyrics(sc.nextLine());

        System.out.print("소속사 입력: ");
        bean.setEntertainment(sc.nextLine());

        System.out.print("음원 발매일 입력: ");
        bean.setMdate(sc.nextLine());

        System.out.print("언어 코드 입력(KR, EN, JP): ");
        bean.setLang(sc.nextLine());

        cnt = mdao.insertData(bean);

        if(cnt == -1){
            System.out.println("음악 추가 실패");
        } else if (cnt == 1){
            System.out.println(bean.getTitle()+" 추가 성공");
        } else {}
    }

    public void updataMusic() {
        int cnt = -1;
        Mp3list mp3list = null;

        System.out.print("수정하고자 하는 곡을 입력하세요: ");
        String findsong = sc.nextLine().trim();

        mp3list = mdao.searchMusic(findsong);

        if(mp3list != null){

            while (true){
                System.out.println("1: 종료, 2: 아티스트, 3: 작곡가, 4: 작사가, 5: 소속사, 6: 곡 발매일, 7: 언어 코드");
                System.out.print(mp3list.getTitle() + " 곡의 수정할 정보를 고르세요: ");
                int num = sc.nextInt();

                switch (num){
                    case 1:
                        break;
                    case 2:
                        System.out.println("아티스트를 입력해주세요: ");
                        mp3list.setArtist(sc.nextLine());
                        break;
                    case 3:
                        System.out.println("작곡가를 입력해주세요: ");
                        mp3list.setComposer(sc.nextLine());
                        break;
                    case 4:
                        System.out.println("작사가를 입력해주세요: ");
                        mp3list.setLyrics(sc.nextLine());
                        break;
                    case 5:
                        System.out.println("소속사를 입력해주세요: ");
                        mp3list.setEntertainment(sc.nextLine());
                        break;
                    case 6:
                        System.out.println("곡 발매일을 입력해주세요: ");
                        mp3list.setMdate(sc.nextLine());
                        break;
                    case 7:
                        System.out.println("언어 코드를 입력해주세요(KR, EN, JP): ");
                        mp3list.setLang(sc.nextLine());
                        break;
                }

            }

        } else  {
            System.out.println("곡을 찾을 수 없습니다.");
        }

        cnt = mdao.updataMusic(mp3list);

        if(cnt == -1) {
            System.out.println("업데이트 실패");
        } else if (cnt == 1) {
            System.out.println("업데이트 성공");
        } else {}
    }

    public void deleteMusic() {
        int cnt = -1;

        System.out.print("삭제하고자 하는 곡 번호를 입력하세요: ");
        int num = sc.nextInt();

        cnt = mdao.deleteMusic(num);

        if(cnt == -1){
            System.out.println("삭제 실패");
        } else if (cnt == 0) {
            System.out.println("삭제한 곡이 존재하지 않습니다.");
        } else if (cnt == 1) {
            System.out.println("곡이 삭제되었습니다.");
        } else {}
    }

    public void selectAll() {
        List<Mp3list> mp3lists = mdao.selectAll();

        for(Mp3list bean:mp3lists){
            String title = bean.getTitle();
            String artist = bean.getArtist();
            String composer = bean.getComposer();
            String lyrics = bean.getLyrics();
            String entertainment = bean.getEntertainment();
            String mdate = bean.getMdate();
            String lang = bean.getLang();

            String message = title + "\t" + artist + "\t" + composer + "\t" + lyrics + "\t" + entertainment + "\t" + mdate + "\t" + lang;
            System.out.println(message);
        }
    }

    public void getSize() {
        int cnt = mdao.getSize();

        String message = "";

        if(cnt == 0){
            System.out.println("저장된 곡이 없습니다.");
        } else {
            System.out.println("총 " + cnt + "곡이 저장되어 있습니다.");
        }
    }

    public void selectCountry() {
    }

    public void randomMusic() {
    }

    public void musicBookmark() {
    }
}
