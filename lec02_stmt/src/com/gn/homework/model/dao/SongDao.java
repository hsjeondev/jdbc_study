package com.gn.homework.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.gn.homework.model.vo.Song;
import com.gn.study.model.vo.Member;

public class SongDao {
	private static final String className = "org.mariadb.jdbc.Driver";
	private static final String url = "jdbc:mariadb://127.0.0.1:3306/watermelon_music";
	private static final String id = "scott";
	private static final String pwd = "tiger";
	
	public List<Song> topTen() {
		List<Song> list = new ArrayList<Song>();
		
		String sql = "SELECT * "
				+ " FROM wm_song "
				+ " ORDER BY s_play_count "
				+ " LIMIT 10";
		
		try(Connection conn = DriverManager.getConnection(url, id, pwd);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)) {
			
			Class.forName(className);
			
			while(rs.next()) {
				int songNo = rs.getInt("s_no");
				String songTitle = rs.getString("s_title");
				String songArtist = rs.getString("s_artist");
				int songPlayCount = rs.getInt("s_play_count");
				Song song = new Song(songNo, songTitle, songArtist, songPlayCount);
				list.add(song);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public int addSong(String songTitle, String songArtist) {
		int result = 0;
		
		try(Connection conn = DriverManager.getConnection(url, id, pwd);
			Statement stmt = conn.createStatement();) {
			
			Class.forName(className);
			
			String sql = "INSERT INTO wm_song(s_title ,s_artist) "
						+ " VALUES ('"+songTitle+"' ,'"+songArtist+"')";
			
			result = stmt.executeUpdate(sql);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
}
