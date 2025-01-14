package com.gn.homework.controller;

import java.util.List;

import com.gn.homework.model.dao.SongDao;
import com.gn.homework.model.vo.Song;

public class SongController {
	private SongDao sd = new SongDao();
	
	public Song choseSong(int select) {
		return sd.choseSong(select);
	}
	
	public List<Song> songList() {
		return sd.songList();
	}

	public int addSong(String songTitle, String songAritst) {
		return sd.addSong(songTitle, songAritst);
	}
	
	public List<Song> topTen() {
		return sd.topTen();
	}
}
