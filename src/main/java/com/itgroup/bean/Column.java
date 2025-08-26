package com.itgroup.bean;

import java.util.Map;

public class Column {
    private static final Map<String, String> Columntag = Map.of(
            "NO", "곡 번호",
            "TITLE", "곡명",
            "ARTIST", "아티스트",
            "COMPOSER", "작곡가",
            "LYRICS", "작사가",
            "ENTERTAINMENT", "소속사",
            "MDATE", "음원 발매일",
            "LANG", "언어 코드"
    );

    public static String tag(String columName){
        return Columntag.getOrDefault(columName, columName);
    }
}
