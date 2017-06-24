package it.polito.tdp.music.model;

public class Mese {
	
	private int n;
	private String nome;
	
	
	public Mese(int n, String nome) {
		super();
		this.n = n;
		this.nome = nome;
	}


	public int getN() {
		return n;
	}


	public void setN(int n) {
		this.n = n;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	@Override
	public String toString() {
		return nome;
	}
	
	

}
