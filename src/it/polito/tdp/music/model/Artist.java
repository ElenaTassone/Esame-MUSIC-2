package it.polito.tdp.music.model;

public class Artist implements Comparable<Artist> {
	
	private int id ;
	private String artist ;
	
	private int ascolti ;
	
	public Artist(int id, String artist) {
		super();
		this.id = id;
		this.artist = artist;
		this.ascolti = 1;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getArtist() {
		return this.artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}

	public int getAscolti() {
		return ascolti;
	}

	public void addAscolti() {
		this.ascolti ++;
	}

	@Override
	public int compareTo(Artist altro) {
		return -this.getAscolti()+altro.getAscolti();
	}

	@Override
	public String toString() {
		return artist+" : "+ascolti;
	}
	
}
