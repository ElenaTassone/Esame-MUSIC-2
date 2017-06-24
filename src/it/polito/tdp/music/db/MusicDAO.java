package it.polito.tdp.music.db;

import it.polito.tdp.music.model.Artist;
import it.polito.tdp.music.model.City;
import it.polito.tdp.music.model.Country;
import it.polito.tdp.music.model.Listening;
import it.polito.tdp.music.model.Mese;
import it.polito.tdp.music.model.Track;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MusicDAO {
	
	public List<Country> getAllCountries() {
		final String sql = "SELECT id, country FROM country" ;
		
		List<Country> countries = new LinkedList<Country>() ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet res = st.executeQuery() ;
			
			while( res.next() ) {
				countries.add( new Country(res.getInt("id"), res.getString("country"))) ;
			}
			
			conn.close() ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
		
		return countries ;
		
	}
	
	public List<City> getAllCities() {
		final String sql = "SELECT id, city FROM city" ;
		
		List<City> cities = new LinkedList<City>() ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet res = st.executeQuery() ;
			
			while( res.next() ) {
				cities.add( new City(res.getInt("id"), res.getString("city"))) ;
			}
			
			conn.close() ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
		
		return cities ;
		
	}

	
	public Map<Integer, Artist> getAllArtists() {
		
		final String sql = "SELECT id, artist FROM artist" ;
		Map<Integer, Artist> result = new TreeMap<Integer, Artist>  ();
//		List<Artist> artists = new LinkedList<Artist>() ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet res = st.executeQuery() ;
			
			while( res.next() ) {
				Artist a = new Artist(res.getInt("id"), res.getString("artist")) ;
				result.put(a.getId(), a ) ;
			}
			
			conn.close() ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
		
		return result ;
		
	}

	public List<Track> getAllTracks() {
		final String sql = "SELECT id, track FROM track" ;
		
		List<Track> tracks = new LinkedList<Track>() ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet res = st.executeQuery() ;
			
			while( res.next() ) {
				tracks.add( new Track(res.getInt("id"), res.getString("track"))) ;
			}
			
			conn.close() ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
		
		return tracks ;
		
	}
	
	public List<Listening> getAllListenings() {
		final String sql = "SELECT id, userid, month, weekday, longitude, latitude, countryid, cityid, artistid, trackid FROM listening" ;
		
		List<Listening> listenings = new LinkedList<Listening>() ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet res = st.executeQuery() ;
			
			while( res.next() ) {
				listenings.add( new Listening(res.getLong("id"), res.getLong("userid"), res.getInt("month"), res.getInt("weekday"),
						res.getDouble("longitude"), res.getDouble("latitude"), res.getInt("countryid"), res.getInt("cityid"),
						res.getInt("artistid"), res.getInt("trackid"))) ;
			}
			
			conn.close() ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
		
		return listenings ;
		
	}


	
	
	public static void main(String[] args) {
		MusicDAO dao = new MusicDAO() ;
		
		List<Country> countries = dao.getAllCountries() ;
		//System.out.println(countries) ;
		
		List<City> cities = dao.getAllCities() ;
		//System.out.println(cities) ;
		
		Map<Integer, Artist> artists = dao.getAllArtists() ;
		
		List<Track> tracks = dao.getAllTracks() ;
		
		List<Listening> listenings = dao.getAllListenings() ;



		System.out.format("Loaded %d countries, %d cities, %d artists, %d tracks, %d listenings\n", 
				countries.size(), cities.size(), artists.size(), tracks.size(), listenings.size()) ;
	}

	public List<Integer> getArtistiMese(Mese mese) {

		final String sql = "SELECT artistid FROM listening WHERE month = ? " ;

		List<Integer> result = new LinkedList<Integer>() ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			int n = mese.getN() ;
			st.setInt(1, n);
			
			ResultSet res = st.executeQuery() ;
			
			while( res.next() ) {
				
				result.add(res.getInt("artistid")) ;
			}
			
			conn.close() ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
		
		return result ;
	}

}
