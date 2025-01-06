package com.gn.homework.model.vo;

public class Song {

	private int songNo;
	private String songTitle;
	private String songArtist;
	private int songPlayCount;
	private String songLyrics;
	
	public Song() {}

	public Song(int songNo, String songTitle, String songArtist, int songPlayCount, String songLyrics) {
		this.songNo = songNo;
		this.songTitle = songTitle;
		this.songArtist = songArtist;
		this.songPlayCount = songPlayCount;
		this.songLyrics = songLyrics;
	}
	
	public Song(int songNo, String songTitle, String songArtist) {
		super();
		this.songNo = songNo;
		this.songTitle = songTitle;
		this.songArtist = songArtist;
	}

	public String getSongLyrics() {
		return songLyrics;
	}

	public void setSongLyrics(String songLyrics) {
		this.songLyrics = songLyrics;
	}

	public int getSongNo() {
		return songNo;
	}

	

	public void setSongNo(int songNo) {
		this.songNo = songNo;
	}

	public String getSongTitle() {
		return songTitle;
	}

	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}

	public String getSongArtist() {
		return songArtist;
	}

	public void setSongArtist(String songArtist) {
		this.songArtist = songArtist;
	}

	public int getSongPlayCount() {
		return songPlayCount;
	}

	public void setSongPlayCount(int songPlayCount) {
		this.songPlayCount = songPlayCount;
	}
	
	@Override
	public String toString() {
		return "Song [songNo=" + songNo + ", songTitle=" + songTitle + ", songArtist=" + songArtist + ", songPlayCount="
				+ songPlayCount + "]";
	}

	public String printRank() {
	    return "제목 : " + songTitle + ", 아티스트 : " + songArtist + ", 재생 횟수 : " + songPlayCount;
	}

	public String printSongs() {
		return songNo + "번 제목 : " + songTitle + ", 아티스트 : " + songArtist;
	}
	
}
