package com.itgroup;

import com.itgroup.bean.Column;
import com.itgroup.bean.Mp3list;
import com.itgroup.dao.Mp3listDao;

import java.lang.reflect.Method;
import java.util.*;
import java.util.Random;

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
        Map<String, Boolean> columnInfo = mdao.getColumnInfo();

        Mp3list bean = new Mp3list();
        int cnt = -1;

        for(String col:columnInfo.keySet()){
            boolean isRequired = columnInfo.get(col);

            while (true){
                System.out.print(Column.tag(col) + " 입력: ");
                String memo = sc.nextLine().trim();

                if(isRequired && memo.isEmpty()){
                    System.out.println("필수 입력란 입니다.");
                    continue;
                }

                try {
                    String colName = "set" + col.substring(0,1) + col.substring(1).toLowerCase();
                    Method method;

                    method = Mp3list.class.getMethod(colName, String.class);
                    method.invoke(bean, memo);

                    break;
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        cnt = mdao.insertData(bean);

        if(cnt == -1){
            System.out.println("음악 추가 실패");
        } else if (cnt == 1){
            System.out.println(bean.getTitle()+" 추가 성공");
        } else {}
    }

    public void updateMusic() {
        int cnt = -1;
        Mp3list mp3list = null;

        System.out.print("수정하고자 하는 곡을 입력하세요: ");
        String findsong = sc.nextLine().trim();

        mp3list = mdao.searchMusic(findsong);

        if(mp3list != null){

            while (true){
                System.out.println("0: 종료, 1: 곡명, 2: 아티스트, 3: 작곡가, 4: 작사가, 5: 소속사, 6: 곡 발매일, 7: 언어 코드");
                System.out.print(mp3list.getTitle() + " 곡의 수정할 정보를 고르세요: ");
                int num = sc.nextInt();
                sc.nextLine();

                switch (num){
                    case 0:
                        return;
                    case 1:
                        System.out.print("곡명을 입력해주세요: ");
                        mp3list.setTitle(sc.nextLine().trim());
                        break;
                    case 2:
                        System.out.print("아티스트를 입력해주세요: ");
                        mp3list.setArtist(sc.nextLine().trim());
                        break;
                    case 3:
                        System.out.print("작곡가를 입력해주세요: ");
                        mp3list.setComposer(sc.nextLine().trim());
                        break;
                    case 4:
                        System.out.print("작사가를 입력해주세요: ");
                        mp3list.setLyrics(sc.nextLine().trim());
                        break;
                    case 5:
                        System.out.print("소속사를 입력해주세요: ");
                        mp3list.setEntertainment(sc.nextLine().trim());
                        break;
                    case 6:
                        System.out.print("곡 발매일을 입력해주세요: ");
                        mp3list.setMdate(sc.nextLine().trim());
                        break;
                    case 7:
                        System.out.print("언어 코드를 입력해주세요(KR, EN, JP): ");
                        mp3list.setLang(sc.nextLine().trim());
                        break;
                }

                cnt = mdao.updateMusic(mp3list);
                System.out.println();


            }

        } else  {
            System.out.println("곡을 찾을 수 없습니다.");
        }

        if(cnt == -1) {
            System.out.println("업데이트 실패");
        } else if (cnt == 1) {
            System.out.println("업데이트 성공");
        } else {}

    }

    public void deleteMusic() {
        int cnt = -1;

        System.out.print("삭제하고자 하는 곡명을 입력하세요: ");
        String musicName = sc.nextLine().trim();

        Mp3list mp3list = mdao.searchMusic(musicName);

        if(mp3list != null){
            cnt = mdao.deleteMusic(mp3list.getNo());
        } else {
            System.out.println("해당곡을 찾지 못했습니다.");
        }


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
        System.out.print("목록을 확인할 음악 코드를 입력하세요(KR,EN,JP): ");
        String cord = sc.nextLine().trim();

        List<Mp3list> mp3lists = mdao.selectCountry(cord);

        if(mp3lists.size() == 0){
            System.out.println("저장된 곡이 없습니다.");
            return;
        }

        for(Mp3list bean:mp3lists){
            String title = bean.getTitle();
            String artist = bean.getArtist();
            String composer = bean.getComposer();
            String lyrics = bean.getLyrics();
            String entertainment = bean.getEntertainment();
            String mdate = bean.getMdate();

            String message = title + "\t" + artist + "\t" + composer + "\t" + lyrics + "\t" + entertainment + "\t" + mdate;
            System.out.println(message);
        }

    }

    public void randomMusic() {

        System.out.print("추천받고자 하는 음악 코드를 입력하세요(KR,EN,JP): ");
        String cord = sc.nextLine().trim();

        List<Mp3list> mp3lists = mdao.selectCountry(cord);

        int siz = mp3lists.size();

        if(siz == 0){
            System.out.println("추천할 곡이 없습니다. 리스트 목록 갯수: 0 ");
        }else {
            Random random = new Random();
            int num = random.nextInt(siz);
            Mp3list bean = mp3lists.get(num);

            System.out.println();

            System.out.println("<" + bean.getTitle() + ">");
            System.out.println("아티스트/작곡가/작사가: " + bean.getArtist() + "/" + bean.getComposer() + "/" + bean.getLyrics());
            System.out.println("소속사: " + bean.getEntertainment());
            System.out.println("음원 발매일: " + bean.getMdate());
        }

        System.out.println();
    }

}
