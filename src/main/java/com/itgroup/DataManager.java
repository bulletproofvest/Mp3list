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
    }

    public void insertMusic() {
    }

    public void updataMusic() {
    }

    public void deleteMusic() {
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

            // 한줄씩 출력할 거임 System.out.println("제목 : " + title);
        }
    }

    public void getSize() {
    }

    public void selectCountry() {
    }

    public void randomMusic() {
    }

    public void musicBookmark() {
    }
}
