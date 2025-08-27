package com.itgroup.dao;

import com.itgroup.bean.Mp3list;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class Mp3listDao extends SuperDao{
    public Mp3listDao(){
        super();
    }

    public List<Mp3list> selectAll() {
        List<Mp3list> mp3lists = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String qsl = "select * from Mp3list order by title asc ";

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(qsl);
            rs = pstmt.executeQuery();

            while(rs.next()){
                Mp3list bean = new Mp3list();

                bean.setNo(rs.getInt("no"));
                bean.setTitle(rs.getString("title"));
                bean.setArtist(rs.getString("artist"));
                bean.setComposer(rs.getString("composer"));
                bean.setLyrics(rs.getString("lyrics"));
                bean.setEntertainment(rs.getString("entertainment"));
                java.sql.Date mdate = rs.getDate("mdate");
                bean.setMdate(mdate == null ? null : new SimpleDateFormat("yyyy-MM-dd").format(mdate));
                bean.setLang(rs.getString("lang"));

                mp3lists.add(bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if(rs != null){rs.close();}
                if(pstmt != null){pstmt.close();}
                if(conn != null){conn.close();}
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }

        return mp3lists;
    }

    public Map<String, Boolean> getColumnInfo(){
        Map<String, Boolean> column = new LinkedHashMap<>();

        Connection conn = null;
        DatabaseMetaData dbmd = null;
        ResultSet rs = null;

        try{
            conn = getConnection();
            dbmd = conn.getMetaData();
            rs = dbmd.getColumns(null, null, "MP3LIST", null);

            while (rs.next()){
                String columnName = rs.getString("COLUMN_NAME"); // 현재 행에서 칼럼 이름을 가져옴

                if("NO".equalsIgnoreCase(columnName)){
                    continue;
                }
                int nullable = rs.getInt("NULLABLE"); // 현재 칼럼이 null 을 허용하는지 여부
                boolean isRequired = (nullable == DatabaseMetaData.columnNoNulls);
                column.put(columnName, isRequired);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                if(rs != null){rs.close();}
                if(conn != null){conn.close();}
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        return column;
    }

    public int insertData(Mp3list bean) {
        int cnt = -1;

        String sql = "INSERT INTO MP3LIST (no, title, artist, composer, lyrics, entertainment, mdate, lang) ";
        sql += "VALUES(Mp3list_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, bean.getTitle());
            pstmt.setString(2, bean.getArtist());
            pstmt.setString(3, bean.getComposer());
            pstmt.setString(4, bean.getLyrics());
            pstmt.setString(5, bean.getEntertainment());
            pstmt.setString(6, bean.getMdate());
            pstmt.setString(7, bean.getLang());

            cnt = pstmt.executeUpdate();
            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
            try{
                conn.rollback();
            } catch (Exception e2){
                e2.printStackTrace();
            }
        } finally {
            try {
                if(pstmt != null){pstmt.close();}
                if(conn != null){conn.close();}
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        return cnt;
    }

    public int deleteMusic(String musicName) {
        int cnt = -1;
        int num;
        Mp3list findMusic = searchMusic(musicName);

        String sql = "Delete from mp3list where no = ? ";

        Connection conn = null;
        PreparedStatement pstmt = null;

        if(findMusic != null){
            num = searchMusic(musicName).getNo();
        } else{
            System.out.println("해당 곡을 찾지 못했습니다.");
            return cnt;
        }

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, num);
            cnt = pstmt.executeUpdate();        // executeUpdate() : 실행 후 영향 받은 행의 수(int)를 반환 / 삭제되면 1, 삭제된 게 없으면 0

            conn.commit();
        } catch (Exception e){
            try{
                conn.rollback();
            }catch (Exception e2) {
                e2.printStackTrace();
            }
        } finally {
            try{
                if(pstmt != null){pstmt.close();}
                if(conn != null){conn.close();}
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return cnt;
        /*
        int cnt = -1;
        String sql = "Delete from mp3list where no = ? ";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, num);
            cnt = pstmt.executeUpdate();        // executeUpdate() : 실행 후 영향 받은 행의 수(int)를 반환 / 삭제되면 1, 삭제된 게 없으면 0

            conn.commit();
        } catch (Exception e){
            try{
                conn.rollback();
            }catch (Exception e2) {
                e2.printStackTrace();
            }
        } finally {
            try{
                if(pstmt != null){pstmt.close();}
                if(conn != null){conn.close();}
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return cnt;
        */
    }

    public int getSize() {
        int cnt = 0;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select count(*) as cnt from mp3list ";

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if(rs.next()){
                cnt = rs.getInt("cnt");
            }
        } catch (SQLException e){
            throw new RuntimeException();
        } finally{
            try{
                if(rs != null){rs.close();}
                if(pstmt != null){pstmt.close();}
                if(conn != null){conn.close();}
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return cnt;
    }

    public Mp3list searchMusic(String findTitle) {
        Mp3list song = null;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select * from mp3list where Lower(title) = Lower(?) ";

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, findTitle);
            rs = pstmt.executeQuery();

            if(rs.next()){
                song = new Mp3list();

                song.setNo(rs.getInt("no"));
                song.setTitle(rs.getString("title"));
                song.setArtist(rs.getString("artist"));
                song.setComposer(rs.getString("composer"));
                song.setLyrics(rs.getString("lyrics"));
                song.setEntertainment(rs.getString("entertainment"));
                java.sql.Date mdate = rs.getDate("mdate");
                song.setMdate(mdate == null ? null : new SimpleDateFormat("yyyy-MM-dd").format(mdate));
                song.setLang(rs.getString("lang"));
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                if(rs != null){rs.close();}
                if(pstmt != null){pstmt.close();}
                if(conn != null){conn.close();}
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return song;

    }

    public int updateMusic(Mp3list findsong) {
        int cnt = -1;

        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = "update mp3list set title = ?, artist = ?, composer = ?, lyrics = ?, entertainment = ?, mdate = ?, lang = ? ";
        sql += "where no = ? ";

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, findsong.getTitle());
            pstmt.setString(2, findsong.getArtist());
            pstmt.setString(3, findsong.getComposer());
            pstmt.setString(4, findsong.getLyrics());
            pstmt.setString(5, findsong.getEntertainment());
            pstmt.setString(6, findsong.getMdate());
            pstmt.setString(7, findsong.getLang());
            pstmt.setInt(8, findsong.getNo());

            cnt = pstmt.executeUpdate();
            conn.commit();
        } catch (Exception e){
            e.printStackTrace();
            try{
                conn.rollback();
            } catch (Exception e2){
                e2.printStackTrace();
            }
        }finally {
            try{
                if(pstmt != null){pstmt.close();}
                if(conn != null){conn.close();}
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return cnt;
    }

    public List<Mp3list> selectCountry(String cord) {
        List<Mp3list> mp3lists = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select * from Mp3list where lang = ? ";

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cord);
            rs = pstmt.executeQuery();

            while (rs.next()){
                Mp3list bean = new Mp3list();

                bean.setNo(rs.getInt("no"));
                bean.setTitle(rs.getString("title"));
                bean.setArtist(rs.getString("artist"));
                bean.setComposer(rs.getString("composer"));
                bean.setLyrics(rs.getString("lyrics"));
                bean.setEntertainment(rs.getString("entertainment"));
                bean.setMdate(rs.getString("mdate"));
                bean.setLang(rs.getString("lang"));

                mp3lists.add(bean);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(rs != null){rs.close();}
                if(pstmt != null){pstmt.close();}
                if(conn != null){conn.close();}
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return mp3lists;
    }

    public List<Mp3list> randomMusic(String cord) {
        List<Mp3list> mp3lists = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select * from Mp3list where lang = ? ";

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cord);
            rs = pstmt.executeQuery();

            if(rs.next()){
                Mp3list bean = new Mp3list();

                bean.setNo(rs.getInt("NO"));
                bean.setTitle(rs.getString("TITLE"));
                bean.setArtist(rs.getString("ARTIST"));
                bean.setComposer(rs.getString("COMPOSER"));
                bean.setLyrics(rs.getString("LYRICS"));
                bean.setEntertainment(rs.getString("ENTERTAINMENT"));
                java.sql.Date mdate = rs.getDate("MDATE");
                bean.setMdate(mdate == null ? null : new SimpleDateFormat("yyyy-MM-dd").format(mdate));
                bean.setLang(rs.getString("LANG"));

                mp3lists.add(bean);
            }

        } catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(rs != null){rs.close();}
                if(pstmt != null){pstmt.close();}
                if(conn != null){conn.close();}
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return mp3lists;
    }
}
