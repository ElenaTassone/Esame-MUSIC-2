package it.polito.tdp.music.model;

import java.util.*;

import it.polito.tdp.music.db.MusicDAO;

public class Model {
	
	private Map<Integer, Mese> mesi;
	private List<Artist> top ;
	private MusicDAO dao;
	private Map<Integer, Artist> artisti ;
	private List<Artist> artMese ;
	
	public Model(){
		this.dao = new MusicDAO ();
		this.artisti = dao.getAllArtists();
	}
	
	
	//test model
	public static void main(String[] args) {
		Model m = new Model () ;
		System.out.println(m.getMesi());
		System.out.println(m.getTop(new Mese(1,"Gennaio")));
	}

	public Collection<Mese> getMesi() {
		mesi = new TreeMap <Integer, Mese> () ;
		mesi.put(1, new Mese(1, "Gennaio")) ;
		mesi.put(2, new Mese(2,"Febbraio")) ;
		mesi.put(3, new Mese(3,"Marzo")) ;
		mesi.put(4, new Mese(4,"Aprile")) ;
		mesi.put(5, new Mese(5,"Maggio")) ;
		mesi.put(6, new Mese(6,"Giugno")) ;
		mesi.put(7, new Mese(7,"Luglio")) ;
		mesi.put(8, new Mese(8,"Agosto")) ;
		mesi.put(9, new Mese(9,"Settembre")) ;
		mesi.put(10, new Mese(10,"Ottobre")) ;
		mesi.put(11, new Mese(11,"Novembre")) ;
		mesi.put(12, new Mese(12,"Dicembre")) ;
		
//		List<String> result = new ArrayList<String> (mesi.values());
		return  mesi.values();
	}

	public List<Artist> getTop(Mese mese) {
		List<Integer> id = dao.getArtistiMese(mese) ;
		this.artMese = new ArrayList<Artist> () ;
		
		Map<Artist, Integer> prova = new TreeMap<Artist, Integer> () ;
		for(Integer i : id){
			Artist temp = this.artisti.get(i) ;
//			if(this.artMese.contains(temp)){
			if(prova.keySet().contains(temp)){
//				artMese.remove(temp);
				
//				artMese.add(temp);
				int ascolti = prova.get(temp);
				temp.addAscolti();
				ascolti = ascolti++;
				prova.put(temp, ascolti);
			}
//			if(!this.artMese.contains(temp))
//				this.artMese.add(temp);
			if(!prova.keySet().contains(temp))
				prova.put(temp, 1);
			
		}
		List<Artist> c = new ArrayList<Artist> () ;
		for(Artist a : prova.keySet()){
			if(!c.contains(a))
				c.add(a);
		}
		Collections.sort(c);
		List<Artist> top = new ArrayList<Artist> () ;
		for(int i = 0 ; i < 20 ; i++){
			top.add(((List<Artist>) c).get(i));
			
		}
		System.out.println(c);
		
		return top ;
	}

}
