package com.itgroup;

import com.itgroup.dao.Mp3listDao;

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
