package com.tomcat.beans;

public class Client {
	private String nom;
	private String prenom;
	private String adresseLivraison;
	private String numTelephone;
	private String courriel;
	
	//--------- Nom ------------------
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	//--------- prenom ------------------
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	//--------- Adresse de livraison ------------------
	public String getAdresseLivraison() {
		return adresseLivraison;
	}
	public void setAdresseLivraison(String adresseLivraison) {
		this.adresseLivraison = adresseLivraison;
	}
	
	//--------- numero de telephone ------------------
	public String getNumTelephone() {
		return numTelephone;
	}
	public void setNumTelephone(String numTelephone) {
		this.numTelephone = numTelephone;
	}
	
	//--------- courriel du client ------------------
	public String getCourriel() {
		return courriel;
	}
	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}
	
	
	
}
