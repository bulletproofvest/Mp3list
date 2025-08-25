package com.itgroup.dao;

import com.itgroup.bean.Mp3list;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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
                bean.setMdate(rs.getString("mdate"));
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

    public int insertData(Mp3list bean) {
        int cnt = -1;

        String sql = "INSERT INTO MP3LIST ";
        sql += "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, bean.getNo());
            pstmt.setString(2, bean.getTitle());
            pstmt.setString(3, bean.getArtist());
            pstmt.setString(4, bean.getComposer());
            pstmt.setString(5, bean.getLyrics());
            pstmt.setString(6, bean.getEntertainment());
            pstmt.setString(7, bean.getMdate());
            pstmt.setString(8, bean.getLang());

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

    public int deleteMusic(int num) {
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
                song.setMdate(rs.getString("mdate"));
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

    public int updataMusic(Mp3list findsong) {
        int cnt = -1;

        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = "update mp3list set artist = ?, composer = ?, lyrics = ?, entertainment = ?, mdate = ?, lang = ? ";
        sql += "where title = ? ";

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(7, findsong.getTitle());
            pstmt.setString(1, findsong.getArtist());
            pstmt.setString(2, findsong.getComposer());
            pstmt.setString(3, findsong.getLyrics());
            pstmt.setString(4, findsong.getEntertainment());
            pstmt.setString(5, findsong.getMdate());
            pstmt.setString(6, findsong.getLang());

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
}
